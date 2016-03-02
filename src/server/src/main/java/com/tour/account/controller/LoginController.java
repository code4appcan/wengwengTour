package com.tour.account.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.account.entity.Account;
import com.tour.account.service.AccountService;
import com.tour.frame.filter.WebContext;
import com.tour.frame.shiro.ShiroSessionUtils;
import com.tour.frame.shiro.ShiroStatelessUtils;
import com.tour.frame.utils.CaptchaUtils;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.ContextHolderUtils;
import com.tour.frame.utils.FileBean;
import com.tour.frame.utils.FileUploadUtil;
import com.tour.frame.utils.FileUtil;
import com.tour.frame.utils.Md5;
import com.tour.frame.utils.ResponseUtils;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private AccountService accountService;
	private static final String user_img_tmp_path="img/tmp/";
	private static final String user_img_path="img/";

	/**
	 * 登陆页的映射
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/")
	public String login(Model model) throws Exception {
		return "/login";
	}

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) throws Exception {
		/*try {
			Account a = ShiroSessionUtils.getLoginAccount();
			model.addAttribute("user", a);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		HttpSession session = ContextHolderUtils.getSession();
		if(session.getAttribute("user") == null){
			return "redirect:/";
		}
		return "/index";
	}
	/**
	 * 公共下载方法
	 * 
	 * @param response
	 * @param file
	 *            下载的文件
	 * @param fileName
	 *            下载时显示的文件名
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/downImage")
	@ResponseBody
	public HttpServletResponse downFile(@Context HttpServletRequest request,@Context HttpServletResponse response,
			String uri) throws Exception {
		response.setContentType("application/x-download");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		OutputStream out = null;
		InputStream in = null;
		// 下面一步不可少
		String realPath = request.getServletContext().getRealPath("/");
		String filePath =  realPath + uri;
		response.addHeader("Content-disposition", "attachment;filename="
				+ uri);// 设定输出文件头

		try {
			out = response.getOutputStream();
			in = new FileInputStream(new File(filePath));
			int len = in.available();
			byte[] b = new byte[len];
			in.read(b);
			out.write(b);
			out.flush();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("下载失败!");
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/login")
	public Object loginPost(Account account, Model model) throws Exception {
		// type 类型用来标注会员类型,0表示普通会员
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断验证码
		/*
		 * String code = (String)
		 * ShiroSessionUtils.getAttribute(Const.DEFAULT_CAPTCHA_PARAM); if
		 * (StringUtils.isNotBlank(code)) { code =
		 * code.toLowerCase().toString(); } String submitCode =
		 * WebUtils.getCleanParam(request, Const.DEFAULT_CAPTCHA_PARAM); if
		 * (StringUtils.isNotBlank(submitCode)) { submitCode =
		 * submitCode.toLowerCase().toString(); } if
		 * (StringUtils.isBlank(submitCode) || StringUtils.isBlank(code) ||
		 * !code.equals(submitCode)) { model.addAttribute("message", "验证码错误!");
		 * return "/login"; }
		 */

		HttpServletRequest request = WebContext.currentRequest();
		String rememberme = request.getParameter("rememberme");
		String loginProof = request.getParameter("loginProof");
		
		UsernamePasswordToken token = new UsernamePasswordToken(
				loginProof, account.getPassword());
		if (StringUtils.isNotBlank(rememberme)) {
			token.setRememberMe(true);
		} else {
			token.setRememberMe(false);
		}

		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if (subject.isAuthenticated()) {
				Account a = ShiroStatelessUtils.getLoginAccount();
				String s_token = Md5.GetMD5Code(System.currentTimeMillis()+account.getPassword());
				a.setToken(s_token);
				accountService.update(a);
				map.put("body", a);
				map.put("status", 200);
				return map;
			}else{
				map.put("error", "账号密码不正确!");
			}
		} catch (UnknownAccountException uae) {
			map.put("error", "账号不存在!");
		} catch (IncorrectCredentialsException ice) {
			// 密码不正确
			int num = (Integer) ShiroSessionUtils.getAttribute("loginNum");
			token.clear();
			map.put("error", "用户名或密码错误,你还可以输入" + (5 - num) + "次");
		} catch (ExcessiveAttemptsException eae) {
			// 输入用户名或密码错误次数过多
			ShiroSessionUtils.setAsLogout();
			token.clear();
			map.put("error", "输入用户名密码或次数过多,账户已被锁定,半小时后解锁");
		} catch (LockedAccountException lae) {
			map.put("error", "账号被锁定!");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", "未知错误,请联系管理员.");
		}
		return map;
	}
	/**
	 * 没有权限
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unauth")
	public String unauth(Model model) throws Exception {
		if (SecurityUtils.getSubject().isAuthenticated() == false) {
			return "redirect:/";
		}
		return "/unauth";

	}

	/**
	 * 退出
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		 return "redirect:/";
	}

	/**
	 * 生成验证码
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getCaptcha")
	public void getCaptcha(HttpServletResponse response) throws IOException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		CaptchaUtils tool = new CaptchaUtils();
		StringBuffer code = new StringBuffer();
		BufferedImage image = tool.genRandomCodeImage(code);
		ShiroSessionUtils.removeAttribute(Const.DEFAULT_CAPTCHA_PARAM);
		ShiroSessionUtils.setAttribute(Const.DEFAULT_CAPTCHA_PARAM,
				code.toString());

		// 将内存中的图片通过流动形式输出到客户端
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return;
	}

	/**
	 * 公共下载方法
	 * 
	 * @param response
	 * @param file
	 *            下载的文件
	 * @param fileName
	 *            下载时显示的文件名
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/downFile")
	@ResponseBody
	public HttpServletResponse downFile(@Context HttpServletRequest request,@Context HttpServletResponse response,
			@QueryParam("userID") Long userID) throws Exception {
		response.setContentType("application/x-download");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		OutputStream out = null;
		InputStream in = null;
		// 下面一步不可少
		Account user = accountService.findById(userID);
		String imgName = user.getPhotoURL();
		String realPath = request.getServletContext().getRealPath("/");
		String filePath =  realPath + imgName;
		response.addHeader("Content-disposition", "attachment;filename="
				+ imgName);// 设定输出文件头

		try {
			out = response.getOutputStream();
			in = new FileInputStream(new File(filePath));
			int len = in.available();
			byte[] b = new byte[len];
			in.read(b);
			out.write(b);
			out.flush();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("下载失败!");
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

		return response;
	}
	
	/**
	 * 下载图片
	 * @param userID
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadPhoto")
	public Object uploadImage(@QueryParam("userID") Long userID,
	@Context HttpServletRequest request){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String realPath = request.getServletContext().getRealPath("/");
		FileUploadUtil fu=new FileUploadUtil(realPath+user_img_tmp_path, realPath+user_img_path,request);
		FileBean image=fu.Upload();
		Account user = accountService.findById(userID);
		
		if(null!=user.getPhotoURL()&&!"".equals(user.getPhotoURL()))
		{
			FileUtil.delFile(realPath+File.separator+user.getPhotoURL());
		}

		user.setPhotoURL(user_img_path+image.getPhotoId());
		accountService.update(user);

		return ResponseUtils.buildSuccessRes(null);
	}
	
	/**
	 * 查看图片
	 * @param request
	 * @param response
	 * @param userID
	 * @return
	 */
	@RequestMapping("/viewPhoto")
	public String viewImg(@Context HttpServletRequest request,
			@Context HttpServletResponse response,@QueryParam("userID") Long userID){
		
		OutputStream output = null;
		InputStream imageIn = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String realPath = request.getServletContext().getRealPath("/");
		try{
			Account user = accountService.findById(userID);
			String imgName = user.getPhotoURL();
			File image = null;
			if(StringUtils.isNotBlank(imgName)){
				String filePath =  realPath + imgName;
				image = new File(filePath);
				if(!image.exists()){
					image = null;
				}
			}
			response.setContentType("image/png");
			output = response.getOutputStream();// 得到输出流 
			imageIn = new FileInputStream(image);// 文件流  
            bis = new BufferedInputStream(imageIn);// 输入缓冲流  
            bos = new BufferedOutputStream(output);// 输出缓冲流  
            byte data[] = new byte[1024];// 缓冲字节数  
            int size = 0;  
            size = bis.read(data);  
            while (size != -1) {  
                bos.write(data, 0, size);  
                size = bis.read(data);  
            }  
            bis.close();  
            bos.flush();// 清空输出缓冲流  
            bos.close();  
		}catch (IOException e){
			PrintWriter toClient;
			try {
				toClient = response.getWriter();
				response.setContentType("text/html;charset=UTF-8");
				toClient.write("Can't open this image");
				toClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}finally{
			 try {
				 if(bos!=null){
					 bos.flush();// 清空输出缓冲流
					 bos.close();
				 }
				 if(bis!=null){
					 bis.close();
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		return null;
	}
}

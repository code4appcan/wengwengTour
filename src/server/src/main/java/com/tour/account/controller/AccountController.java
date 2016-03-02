package com.tour.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.account.entity.Account;
import com.tour.account.service.AccountService;
import com.tour.frame.shiro.ShiroStatelessUtils;
import com.tour.frame.sms.SMSUtil;
import com.tour.frame.upload.FileUploadDownloadUtil;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.Md5;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.StringUtil;
import com.tour.frame.utils.ValidatorUtils;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.web.Account
 */
@Controller
public class AccountController {
	@Resource
	private AccountService accountService;
	
	
	@RequestMapping(value = "/user/add")
	public String add(Model model) throws Exception {
		return "/user/add";
	}
	@RequestMapping(value = "/user/update")
	public String update(Model model,@QueryParam("id") Long id) {
		List<Account> account = accountService.findBy(id);
		model.addAttribute("user", account);
		return "/user/update";
	}

	/**
	 * save landscape info
	 * @param landscape
	 * @return
	 */
	@RequestMapping(value="/save_user")
	@ResponseBody
	public Object save(Account user) {
		try {
			String message = ValidatorUtils.validate(user);
			if (StringUtils.isBlank(message)) {
				accountService.save(user);
			} 
			return ResponseUtils.buildSuccessRes(user);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/update_user")
	@ResponseBody
	public Object update(Account user) {
		try {
			String message = ValidatorUtils.validate(user);
			if (StringUtils.isBlank(message)) {
				String pwd = user.getPassword();
				if(StringUtil.isNotEmpty(pwd)){
					user.setPassword(Md5.GetMD5Code(pwd));
				}
				accountService.update(user);
			}
			return ResponseUtils.buildSuccessRes(user);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/delete_user")
	public Object delete(@QueryParam("id") Long id,Account account, Pagination pagination,Model model) {
		try {
			Integer deleteuser = accountService.deleteById(id);
			List<Account> list = accountService.listPage(account, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/user/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/delete_users")
	@ResponseBody
	public Object delete(@QueryParam("id") Long id) {
		try {
			Integer user = accountService.deleteById(id);
			return ResponseUtils.buildSuccessRes(user);
		}catch(Exception e){
			e.printStackTrace(); 
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	/**
	 * 查看账号信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get_user")
	@ResponseBody
	public Object get(@QueryParam("id") Long id) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			Account account = accountService.findById(id);
			map.put("account", account);
			return ResponseUtils.buildSuccessRes(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 修改密码之前验证密码是否正确
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/verify_pwd")
	@ResponseBody
	public Object verifyPwd(@QueryParam("userid") Long userid, @QueryParam("password") String password) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			Account account = accountService.findById(userid);
			if(account != null){
				if(Md5.GetMD5Code(password).equals(account.getPassword())){
					map.put("msg", "该用户密码验证通过");
					return ResponseUtils.buildSuccessRes(map);
				}
			}
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, "该用户密码验证不通过");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 获取验证码
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/resetpwd_code",method=RequestMethod.GET)
	@ResponseBody
	public Object resetPwdVerifyCode(@QueryParam("phone") String phone){
		//验证手机号格式是否正确
		boolean isPhone = ValidatorUtils.isMobileNO(phone);
		if(!isPhone){
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, "请输入正确的手机号码");
		}
		Account a = null;
		try {
			// 通过手机号码查询用户
			a = accountService.findByPhone(phone);
			if(a == null){
				return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, "该手机未注册");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, "该手机未注册");
		}
		String authCode = new java.util.Random().nextInt(999999) + "";
		String content = "您的验证码是：" + authCode + ",【秘境巴东】";
		boolean result = SMSUtil.sendSMS(phone, content);//发送短信
		if(result){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", authCode);
			map.put("userid", a.getId());
			return ResponseUtils.buildSuccessRes(map);
		}
		return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, "发送验证码失败");
	}
	
	@RequestMapping(value="/search_user")
	public Object get(@QueryParam("keyWords") String keyWords,Account account, Pagination pagination,Model model) {
		try{
			String name=new String(keyWords.getBytes("ISO-8859-1"),"UTF-8");
			account.setUserName(name);
			List<Account> list = accountService.listPage(account, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/user/list";
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping("/account/list")
	public String list(Account account, Pagination pagination,Model model) {
		List<Account> list = accountService.listPage(account, pagination);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/user/list";
	}
	@RequestMapping("/uploadPhotoAccount")
	@ResponseBody
	public String uploadPhotoAccount(@Context HttpServletRequest request){
		Account a = ShiroStatelessUtils.getLoginAccount();
		String savePaht = "";
		try {
			savePaht = FileUploadDownloadUtil.uploadHeadShow(request, "jpg", "beae");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePaht;
	}
	
	@RequestMapping("/downPhotoAccount")
	@ResponseBody
	public String downPhotoAccount(@Context HttpServletRequest request){
		Account a = ShiroStatelessUtils.getLoginAccount();
		if(StringUtils.isBlank(request.getParameter("length"))){
			
		}
		if(StringUtils.isBlank(request.getParameter("wide"))){
			
		}
		String savePaht = "";
		try {
//			savePaht = FileUploadDownloadUtil.downloadThumbnailatorImage(servicePath, uri, x, y)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePaht;
	}
}
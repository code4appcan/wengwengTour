package com.tour.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.account.entity.Account;
import com.tour.account.service.AccountService;
import com.tour.frame.filter.WebContext;
import com.tour.frame.sms.SMSUtil;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.ValidatorUtils;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.web.Account
 */
@Controller
public class RegisterController {
	@Resource
	private AccountService accountService;
	
	/**
	 * 完善个人信息
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/register/update",method=RequestMethod.GET)
	@ResponseBody
	public Object perfectAccount(@RequestBody Account account) {
		try {
			String message = ValidatorUtils.validate(account);
			if (StringUtils.isBlank(message)) {
				accountService.update(account);
			} 
			return ResponseUtils.buildSuccessRes(null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	public Object activeAccount(){
		return null;
		
	}
	
	
	/**
	 * 邮箱注册
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/register_email",method=RequestMethod.POST)
	@ResponseBody
	public Object registerEmail() {
		try{
			HttpServletRequest req = WebContext.currentRequest();
			Account account = new Account();
			account.setEmail((String)req.getParameter("email"));
			account.setUserName((String)req.getParameter("userName"));
			account.setPassword((String)req.getParameter("password"));
			Long id = accountService.saveAccount(account);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
		return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	/**
	 * 手机注册
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/register_phone",method=RequestMethod.POST)
	@ResponseBody
	public Object registerPhone() {
		try{
			HttpServletRequest req = WebContext.currentRequest();
			Account account = new Account();
			account.setMobile((String)req.getParameter("phone"));
			account.setUserName((String)req.getParameter("userName"));
			account.setPassword((String)req.getParameter("password"));
			Long id = accountService.saveAccount(account);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 获取验证码
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/verify_code",method=RequestMethod.GET)
	@ResponseBody
	public Object getVerifyCode(@QueryParam("phone") String phone){
		boolean isPhone = ValidatorUtils.isMobileNO(phone);
		if(!isPhone){
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, "请输入正确的手机号码");
		}
		String authCode = new java.util.Random().nextInt(999999) + "";
		String content = "您的注册验证码是：" + authCode + ",【秘境巴东】";
		boolean result = SMSUtil.sendSMS(phone, content);
		if(result){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", authCode);
			return ResponseUtils.buildSuccessRes(map);
		}
		return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, "发送验证码失败");
	}
	
}

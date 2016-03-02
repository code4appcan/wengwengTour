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
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.account.entity.Advertisement;
import com.tour.account.service.AdvertisementService;
import com.tour.frame.shiro.ShiroStatelessUtils;
import com.tour.frame.upload.FileUploadDownloadUtil;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.ValidatorUtils;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.web.Account
 */
@Controller
public class AdvertisementController {
	@Resource
	private AdvertisementService advertisementService;
	
	/**
	 * go to landscape edit page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/advertisement/add")
	public String add(Model model) throws Exception {
		return "/advertisement/add";
	}
	@RequestMapping(value = "/advertisement/update")
	public String update(Model model) throws Exception {
		return "/advertisement/update";
	}

	/**
	 * save landscape info
	 * @param landscape
	 * @return
	 */
	@RequestMapping(value="/save_advertisement")
	@ResponseBody
	public Object save(Advertisement advertisement) {
		try {
			String message = ValidatorUtils.validate(advertisement);
			if (StringUtils.isBlank(message)) {
				advertisementService.save(advertisement);
			} 
			return ResponseUtils.buildSuccessRes(null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/update_advertisement")
	@ResponseBody
	public Object update(Advertisement advertisement) {
		try {
			String message = ValidatorUtils.validate(advertisement);
			if (StringUtils.isBlank(message)) {
				advertisementService.update(advertisement);
			}
			return ResponseUtils.buildSuccessRes(null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/delete_advertisement")
	@ResponseBody
	public Object delete(@QueryParam("id") Long id) {
		try {
			Integer advertisement = advertisementService.deleteById(id);
			return ResponseUtils.buildSuccessRes(advertisement);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/get_advertisement")
	@ResponseBody
	public Object get(@QueryParam("userid") Long userid) {
		try {
			Advertisement advertisement = advertisementService.findById(userid);
			return ResponseUtils.buildSuccessRes(advertisement);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/get_advertisement_list")
	@ResponseBody
	public Object get(Advertisement advertisement, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Advertisement> list = advertisementService.listPage(advertisement, pagination);
			map.put("advertisement", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/advertisement/list")
	public String list(Advertisement advertisement, Pagination pagination,Model model) {
		List<Advertisement> list = advertisementService.listPage(advertisement, pagination);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/advertisement/list";
	}
	
	
}
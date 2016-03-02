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

import com.tour.account.entity.Collection;
import com.tour.account.entity.Landscape;
import com.tour.account.service.CollectionService;
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
public class CollectionController {
	@Resource
	private CollectionService collectionService;
	
	/**
	 * go to landscape edit page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/collection/add")
	public String add(Model model) throws Exception {
		return "/collection/add";
	}
	@RequestMapping(value = "/collection/update")
	public String update(Model model) throws Exception {
		return "/collection/update";
	}

	/**
	 * save landscape info
	 * @param landscape
	 * @return
	 */
	@RequestMapping(value="/save_collection")
	@ResponseBody
	public Object save(Collection collection,@QueryParam("userid") Long userid,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type) {
		try {
			String message = ValidatorUtils.validate(collection);
			if (StringUtils.isBlank(message)) {
				collectionService.save(collection);
			}
			return ResponseUtils.buildSuccessRes(collection);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/update_collection")
	@ResponseBody
	public Object update(Collection collection) {
		try {
			String message = ValidatorUtils.validate(collection);
			if (StringUtils.isBlank(message)) {
				collectionService.update(collection);
			}
			return ResponseUtils.buildSuccessRes(collection);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete_collection")
	@ResponseBody
	public Object delete(@QueryParam("id") Long id,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type) {
		try {
			Integer collection = collectionService.deleteById(id,typeid,type);
			return ResponseUtils.buildSuccessRes(collection);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/is_collection")
	@ResponseBody
	public Object isCollection(@QueryParam("userid") Long userid,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Collection> collection = collectionService.findByC(userid,typeid,type);
			map.put("isCollection", collection);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/get_collection")
	@ResponseBody
	public Object get(@QueryParam("userid") Long userid,Collection collection, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Collection> collections = collectionService.findBy(userid,collection, pagination);
			map.put("collection", collections);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/get_collection_list")
	@ResponseBody
	public Object get(Collection collection, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Collection> list = collectionService.listPage(collection, pagination);
			map.put("collection", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/collection/list")
	public String list(Collection collection, Pagination pagination,Model model) {
		List<Collection> list = collectionService.listPage(collection, pagination);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/collection/list";
	}
	
	
}
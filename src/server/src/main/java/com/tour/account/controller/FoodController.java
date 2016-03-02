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

import com.tour.account.entity.Account;
import com.tour.account.entity.Collection;
import com.tour.account.entity.Food;
import com.tour.account.service.CollectionService;
import com.tour.account.service.FoodService;
import com.tour.comments.entity.Comments;
import com.tour.comments.service.CommentsService;
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
public class FoodController {
	@Resource
	private FoodService foodService;
	@Resource
	private CommentsService commentsService;
	@Resource
	private CollectionService collectionService;
	/**
	 * go to landscape edit page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/food/add")
	public String add(Model model) throws Exception {
		return "/food/add";
	}
	@RequestMapping(value = "/food/update")
	public String update(Model model,@QueryParam("id") Long id) {
		List<Food> food = foodService.findBy(id);
		model.addAttribute("food", food);
		return "/food/update";
	}

	/**
	 * save landscape info
	 * @param landscape
	 * @return
	 */
	@RequestMapping(value="/save_food")
	@ResponseBody
	public Object save(Food food) {
		try {
			String message = ValidatorUtils.validate(food);
			if (StringUtils.isBlank(message)) {
				foodService.save(food);
			} 
			return ResponseUtils.buildSuccessRes(food);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/update_food")
	@ResponseBody
	public Object update(Food food) {
		try {
			String message = ValidatorUtils.validate(food);
			if (StringUtils.isBlank(message)) {
				foodService.update(food);
			}
			return ResponseUtils.buildSuccessRes(food);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/update/type")
	@ResponseBody
	public Object updateType(Long id, Long val, String type) {
		try {
			Integer i = foodService.updateType(id, val, type);
			return ResponseUtils.buildSuccessRes(i);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete_food")
	public Object delete(Food food,@QueryParam("id") Long id,@QueryParam("is_collection")Long is_collection ,@QueryParam("is_order")Long is_order, Pagination pagination,Model model) {
		try {
			Integer deletefood = foodService.deleteById(id,is_collection ,is_order);
			List<Food> list = foodService.listPage(food, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/food/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/delete_foods")
	@ResponseBody
	public Object deletes(@QueryParam("id") Long id,@QueryParam("is_collection")Long is_collection ,@QueryParam("is_order")Long is_order) {
		try {
			Integer food = foodService.deleteById(id,is_collection ,is_order);
			return ResponseUtils.buildSuccessRes(food);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/get_food")
	@ResponseBody
	public Object get(Comments comments,@QueryParam("userid") Long userid,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type, @QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			Food food = foodService.findById(typeid);
			map.put("food", food);
			List<Comments> listC = commentsService.findBy(comments,typeid,type,pageNo,pageSize);
			map.put("comments", listC);
			if(userid !=null){
				List<Collection> collection = collectionService.findByC(userid,typeid,type);
				map.put("isCollection", collection);
			}
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/search_food")
	public Object get(@QueryParam("keyWords") String keyWords,Food food, Pagination pagination,Model model) {
		try {
			String name=new String(keyWords.getBytes("ISO-8859-1"),"UTF-8");
			food.setFoodName(name);
			List<Food> list = foodService.listPage(food, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/food/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/get_food_comments")
	@ResponseBody
	public Object get(Comments comments,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type,@QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize) {
		try {				
			Map<String,Object> map = new HashMap<String,Object>();
			Food food = foodService.findById(typeid);
			List<Comments> listC = commentsService.findBy(comments,typeid,type,pageNo,pageSize);
			map.put("food", food);
			map.put("comments", listC);
			return ResponseUtils.buildSuccessRes(map); 
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/get_food_list")
	@ResponseBody
	public Object get(Food food, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			food.setIsFood("Y");
			List<Food> list = foodService.listPage(food, pagination);
			map.put("food", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/get_food_recommend")
	@ResponseBody
	public Object getRecommend() {
		try {
			List<Food> food = foodService.findByHot();
			return ResponseUtils.buildSuccessRes(food);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/food/list")
	public String list(Food food, Pagination pagination,Model model) {
		List<Food> list = foodService.listPage(food, pagination);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/food/list";
	}
	
	
	@RequestMapping(value="food_must")
	@ResponseBody
	public Object getHot(Food food, @QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			food.setHot(1L);
			List<Food> list = foodService.findByHot(food,pageNo,pageSize);
			map.put("must", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 必逛
	 * @param food
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/found_stroll")
	@ResponseBody
	public Object getStroll(Food food, @QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			food.setStroll(1L);
			List<Food> list = foodService.findByHot(food,pageNo,pageSize);
			map.put("stroll", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update_food_hot")
	public Object updateHot(@QueryParam("id") Long id,@QueryParam("hot") Long hot,Food food, Pagination pagination,Model model) {
		if(id!=null && hot!=null){
			foodService.updateHot(id,hot);
		}
		List<Food> list = foodService.findByHot();
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/food/update_hot";
	}
	
	@RequestMapping("/uploadPhotoFood")
	@ResponseBody
	public String uploadPhotoFood(@Context HttpServletRequest request){
		Account a = ShiroStatelessUtils.getLoginAccount();
		String savePaht = "";
		try {
			savePaht = FileUploadDownloadUtil.uploadHeadShow(request, "jpg", "beae");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePaht;
	}
	
	@RequestMapping("/downPhotoFood")
	@ResponseBody
	public String downPhotoFood(@Context HttpServletRequest request){
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
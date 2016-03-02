package com.tour.account.controller;

import java.util.ArrayList;
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
import com.tour.account.entity.Wineshop;
import com.tour.account.entity.WineshopRoom;
import com.tour.account.service.CollectionService;
import com.tour.account.service.WineshopService;
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
public class WineshopController {
	@Resource
	private WineshopService wineshopService;
	@Resource
	private CollectionService collectionService;
	
	/**
	 * go to landscape edit page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wineshop/add")
	public String add(Model model) throws Exception {
		return "/wineshop/add";
	}
	@RequestMapping(value = "/wineshop/update")
	public String updte(Model model,@QueryParam("id") Long id) {
		List<Wineshop> wineshop = wineshopService.findBy(id);
		model.addAttribute("wineshop", wineshop);
		return "/wineshop/update";
	}

	/**
	 * save landscape info
	 * @param landscape
	 * @return
	 */
	@RequestMapping(value="/save_wineshop")
	@ResponseBody
	public Object save(Wineshop wineshop) {
		try {
			String message = ValidatorUtils.validate(wineshop);
			if (StringUtils.isBlank(message)) {
				wineshopService.save(wineshop);
			} 
			return ResponseUtils.buildSuccessRes(wineshop);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/update_wineshop")
	@ResponseBody
	public Object update(Wineshop wineshop) {
		try {
			String message = ValidatorUtils.validate(wineshop);
			if (StringUtils.isBlank(message)) {
				wineshopService.update(wineshop);
			}
			return ResponseUtils.buildSuccessRes(wineshop);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/delete_wineshop")
	public Object delete(@QueryParam("id") Long id,@QueryParam("is_collection")Long is_collection ,@QueryParam("is_order")Long is_order,Wineshop wineshop, Pagination pagination,Model model) {
		try {
			Integer delwineshop = wineshopService.deleteById(id,is_collection ,is_order);
			List<Wineshop> list = wineshopService.listPage(wineshop, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/wineshop/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/delete_wineshops")
	@ResponseBody
	public Object deletes(@QueryParam("id") Long id,@QueryParam("is_collection")Long is_collection ,@QueryParam("is_order")Long is_order) {
		try {
			Integer wineshop = wineshopService.deleteById(id,is_collection ,is_order);
			return ResponseUtils.buildSuccessRes(wineshop);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/get_wineshop")
	@ResponseBody
	public Object get(@QueryParam("userid") Long userid,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			Wineshop wineshop = wineshopService.findById(typeid);
			if(userid !=null){
				List<Collection> collection = collectionService.findByC(userid,typeid,type);
				map.put("isCollection", collection);
			}
			
			map.put("wineshop", wineshop);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/search_wineshop")
	public Object get(@QueryParam("keyWords") String keyWords,Wineshop wineshop, Pagination pagination,Model model) {
		try {
			String name=new String(keyWords.getBytes("ISO-8859-1"),"UTF-8");
			wineshop.setName(name);
			List<Wineshop> list = wineshopService.listPage(wineshop, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/wineshop/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/get_wineshop_list")
	@ResponseBody
	public Object get(Wineshop wineshop, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Wineshop> list = wineshopService.listPage(wineshop, pagination);
			map.put("wineshops", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/get_wineshop_recommend")
	@ResponseBody
	public Object getRecommend() {
		try {
			List<Wineshop> wineshop = wineshopService.findByHot();
			return ResponseUtils.buildSuccessRes(wineshop);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/wineshop/list")
	public String list(Wineshop wineshop, Pagination pagination,Model model) {
		List<Wineshop> list = wineshopService.listPage(wineshop, pagination);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/wineshop/list";
	}

	@RequestMapping(value="wineshop_must")
	@ResponseBody
	public Object getHot(Wineshop wineshop,@QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			wineshop.setHot(1L);
			List<Wineshop> list = wineshopService.findByHot(wineshop,pageNo,pageSize);
			map.put("must", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/update_wineshop_hot")
	public Object updateHot(@QueryParam("id") Long id,@QueryParam("hot") Long hot,Wineshop wineshop, Pagination pagination,Model model) {
		if(id!=null && hot!=null){
			wineshopService.updateHot(id,hot);
		}
		List<Wineshop> list = wineshopService.findByHot();
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/wineshop/update_hot";
	}
	
	@RequestMapping("/uploadPhotoWineshop")
	@ResponseBody
	public String uploadPhoto1(@Context HttpServletRequest request){
		Account a = ShiroStatelessUtils.getLoginAccount();
		String savePaht = "";
		try {
			savePaht = FileUploadDownloadUtil.uploadHeadShow(request, "jpg", "beae");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePaht;
	}
	
	@RequestMapping("/downPhotoWineshop")
	@ResponseBody
	public String downPhoto1(@Context HttpServletRequest request){
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
	
	
	/***************************酒店房型管理**************************/

	@RequestMapping("/wineshop/room/list")
	public Object roomlist(WineshopRoom room, Pagination pagination, Model model) {
		Long hotelId = room.getHotelId();
		String hotelName = "";
		List<WineshopRoom> list = new ArrayList<WineshopRoom>();
		if(hotelId != null && hotelId != 0){
			list = wineshopService.roomlistPage(room, pagination);
			if(list != null && !list.isEmpty()){
				hotelName = list.get(0).getHotelName();
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		model.addAttribute("hotelId", hotelId);
		model.addAttribute("hotelName", hotelName);
		return "/wineshop/room/list";
	}
	
	@RequestMapping(value="/wineshop/room/delete")
	@ResponseBody
	public Object roomdelete(@QueryParam("id") Long id) {
		try {
			Integer i = wineshopService.deleteRoomById(id);
			return ResponseUtils.buildSuccessRes(i);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/wineshop/room/add")
	public Object roomadd(WineshopRoom room, Model model) {
		model.addAttribute("hotelId", room.getHotelId());
		return "/wineshop/room/add";
	}
	
	@RequestMapping("/wineshop/room/update")
	public Object roomupdate(WineshopRoom room, Model model) {
		room = wineshopService.findRoomByid(room.getId());
		model.addAttribute("room", room);
		return "/wineshop/room/update";
	}
	
	@RequestMapping(value="/wineshop/room/save")
	@ResponseBody
	public Object roomsave(WineshopRoom room) {
		try {
			String message = ValidatorUtils.validate(room);
			if (StringUtils.isBlank(message)) {
				wineshopService.saveRoom(room);
			} 
			return ResponseUtils.buildSuccessRes(room);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
}
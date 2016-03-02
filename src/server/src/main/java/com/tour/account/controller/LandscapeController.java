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
import com.tour.account.entity.Food;
import com.tour.account.entity.Landscape;
import com.tour.account.entity.Travels;
import com.tour.account.entity.Wineshop;
import com.tour.account.service.CollectionService;
import com.tour.account.service.FoodService;
import com.tour.account.service.LandscapeService;
import com.tour.account.service.TravelsService;
import com.tour.account.service.WineshopService;
import com.tour.frame.shiro.ShiroStatelessUtils;
import com.tour.frame.upload.FileUploadDownloadUtil;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.ValidatorUtils;
import com.tour.frame.utils.page.Pagination;
import com.tour.photos.entity.Photos;
import com.tour.photos.service.PhotosService;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.web.Account
 */
@Controller
public class LandscapeController {
	@Resource
	private LandscapeService landscapeService;
	@Resource
	private PhotosService photosService;
	@Resource
	private WineshopService wineshopService;
	@Resource
	private FoodService foodService;
	@Resource
	private CollectionService collectionService;
	@Resource
	private TravelsService travelsService;
	 
	/**
	 * go to landscape edit page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/landscape/add")
	public String add(Model model) throws Exception {
		return "/landscape/add";
	}
	@RequestMapping(value = "/landscape/update")
	public Object update(Model model,@QueryParam("id") Long id) {
		try{	
			List<Landscape> landscape = landscapeService.findBy(id);
			model.addAttribute("landscape", landscape);
			return "/landscape/update";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	/**
	 * save landscape info
	 * @param landscape
	 * @return
	 */
	@RequestMapping(value="/save_landscape")
	@ResponseBody
	public Object save(Landscape landscape) {
		try {
			String message = ValidatorUtils.validate(landscape);
			if (StringUtils.isBlank(message)) {
				landscapeService.save(landscape);
			} 
			
			return ResponseUtils.buildSuccessRes(landscape);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/update_landscape")
	@ResponseBody
	public Object update(Landscape landscape) {
		try {
			String message = ValidatorUtils.validate(landscape);
			if (StringUtils.isBlank(message)) {
				landscapeService.update(landscape);
			}
			return ResponseUtils.buildSuccessRes(landscape);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete_landscape")
	public Object delete(@QueryParam("id") Long id,@QueryParam("is_collection")Long is_collection ,@QueryParam("is_order")Long is_order,Landscape landscape, Pagination pagination,Model model) {
		try {
			Integer deleteLandscape = landscapeService.deleteById(id,is_collection ,is_order);
			List<Landscape> list = landscapeService.listPage(landscape, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/landscape/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/delete_landscapes")
	@ResponseBody
	public Object deletes(@QueryParam("id") Long id,@QueryParam("is_collection")Long is_collection ,@QueryParam("is_order")Long is_order) {
		try {
			Integer deleteLandscape = landscapeService.deleteById(id,is_collection ,is_order);
			return ResponseUtils.buildSuccessRes(deleteLandscape);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/get_landscape")
	@ResponseBody
	public Object get(@QueryParam("userid") Long userid,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			Landscape landscape = landscapeService.findById(typeid);
			map.put("landscape", landscape);
			if(userid !=null){
				List<Collection> collection = collectionService.findByC(userid,typeid,type);
				map.put("isCollection", collection);
			}
			
			/*
			 * 增加攻略信息
			 */
			Travels travels = new Travels();
			travels.setLandscapeId(typeid);
			List<Travels> list = travelsService.listPage(travels, new Pagination(5));
			if(list != null && !list.isEmpty()){
				for(Travels t : list){
					// 内容可能太长，加快传输速度
					t.setContent("");
				}
			}
			map.put("travels", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/search_landscape")
	public Object search(@QueryParam("keyWords") String keyWords,Landscape landscape, Pagination pagination,Model model) {
		try{
			String name=new String(keyWords.getBytes("ISO-8859-1"),"UTF-8");
			landscape.setName(name);
			List<Landscape> list = landscapeService.listPage(landscape, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/landscape/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/search_recommend")
	public Object get(@QueryParam("keyWords") String keyWords,Landscape landscape, Pagination pagination,Model model) {
		try{
			String name=new String(keyWords.getBytes("ISO-8859-1"),"UTF-8");
			landscape.setName(name);
			List<Landscape> list = landscapeService.listPage(landscape, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/recommend/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/get_landscape_list")
	@ResponseBody
	public Object get(Landscape landscape, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Landscape> list = landscapeService.listPage(landscape, pagination);
			map.put("landscaps", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/landscape/list")
	public Object list(Landscape landscape, Pagination pagination,Model model) {
		try{
			List<Landscape> list = landscapeService.listPage(landscape, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/landscape/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping("/recommend/list")
	public Object recommend(Landscape landscape, Pagination pagination,Model model) {
		try{
			//List<Landscape> list1 = landscapeService.findByRecommend();
			//model.addAttribute("list1", list1);
			List<Landscape> list = landscapeService.findByBanner();
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/recommend/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping("/recommend/add")
	public Object landscapelist(@QueryParam("id") Long id,@QueryParam("banner") Long banner,Landscape landscape, Pagination pagination,Model model) {
		try{
			if(id!=null && banner!=null){
				landscapeService.updateBanner(id,banner);
			}
			List<Landscape> list = landscapeService.listPage(landscape, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/recommend/add";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/update_landscape_banner")
	public Object updateBanner(@QueryParam("id") Long id,@QueryParam("banner") Long banner,Landscape landscape, Pagination pagination,Model model) {
		try{
			if(id!=null && banner!=null){
				landscapeService.updateBanner(id,banner);
			}
			List<Landscape> list = landscapeService.findByBanner();
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/recommend/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/update_landscape_hot")
	public Object updateHot(@QueryParam("id") Long id,@QueryParam("hot") Long hot,Landscape landscape, Pagination pagination,Model model) {
		try{	
			if(id!=null && hot!=null){
				landscapeService.updateHot(id,hot);
			}
			List<Landscape> list = landscapeService.findByHot();
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/landscape/update_hot";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/update_recommend")
	public Object getRecommends(@QueryParam("id") Long id,@QueryParam("recommend") Long recommend,@QueryParam("type") Long type, Model model) {
		try{
			if(id!=null && recommend!=null){
				if(type==1){
					wineshopService.updateRecommend(id,recommend);
				}
				if(type==3){
					landscapeService.updateRecommend(id,recommend);
				}
				if(type==4){
					foodService.updateRecommend(id,recommend);
				}
			}
			List<Landscape> landscapes = landscapeService.findByRecommends();
			List<Wineshop> wineshops = wineshopService.findByRecommends();
			List<Food> foods = foodService.findByRecommends();
			model.addAttribute("landscapes", landscapes);
			model.addAttribute("wineshops", wineshops);
			model.addAttribute("foods", foods);
			return  "/found/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/get_home")
	@ResponseBody
	public Object getBanner(Landscape landscape, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Landscape> banner = landscapeService.findByBanner(landscape, pagination);
			//List<Landscape> recommend = landscapeService.findByRecommend();
			//map.put("recommend", recommend);
			map.put("banner", banner);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="landscape_must")
	@ResponseBody
	public Object getHot(Landscape landscape,@QueryParam("pageNo") int pageNo,@QueryParam("pageSize") int pageSize) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			landscape.setHot(1L);
			List<Landscape> list = landscapeService.findByHot(landscape,pageNo,pageSize);
			map.put("must", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/get_recommends")
	@ResponseBody
	public Object getRecommends(Landscape landscape, Pagination pagination,Wineshop wineshop ,Food food) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Landscape> landscapes = landscapeService.findByRecommends(landscape, pagination);
			List<Wineshop> wineshops = wineshopService.findByRecommends(wineshop, pagination);
			List<Food> foods = foodService.findByRecommends(food, pagination);
//			List list = new ArrayList<Object>();
//			list.add(landscapes);
//			list.add(wineshops);
//			list.add(foods);
			map.put("landscape", landscapes);
			map.put("wineshop", wineshops);
			map.put("food", foods);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/destination_first")
	@ResponseBody
	public Object getDestinationFirst(@QueryParam("id") long id,@QueryParam("type") long type,Landscape landscape, Pagination pagination,Model model,Photos photo) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Landscape> list = landscapeService.listPage(landscape, pagination);
			Landscape first = landscapeService.findById(id);
			List<Photos> photos = photosService.findByTypeid(id,3);
			map.put("destination", list);
			map.put("photos", photos);
			map.put("first", first);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/destination_detail")
	@ResponseBody
	public Object getDestinationDetail(@QueryParam("id") long id) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			Landscape landscape = landscapeService.findById(id);// 目的地景点对象
			
			List<Wineshop> hostWineshopTemp = wineshopService.findByHot();// 热门酒店
			
			List<Food> hotFoodTemp = foodService.findByHot();// 热门美食
			
			List<Wineshop> hostWineshop = new ArrayList<Wineshop>();
			if(hostWineshopTemp != null){
				// 酒店最多取3个
				int index = hostWineshopTemp.size() > 3 ? 3 : hostWineshopTemp.size();
				for(int i = 0; i < index; i++){
					hostWineshop.add(i, hostWineshopTemp.get(i));
				}
			}
			List<Food> hotFood = new ArrayList<Food>();
			if(hotFoodTemp != null){
				// 美食最多取8个
				int index = hotFoodTemp.size() > 8 ? 8 : hotFoodTemp.size();
				for(int i = 0; i < index; i++){
					hotFood.add(i, hotFoodTemp.get(i));
				}
			}
			map.put("landscape", landscape);
			map.put("hostWineshop", hostWineshop);
			map.put("hotFood", hotFood);
			map.put("hotGuide", "");
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/get_destination_id")
	@ResponseBody
	public Object getDestination(@QueryParam("id") long id,@QueryParam("type") long type, Pagination pagination,Model model,Photos photo) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			Landscape destination = landscapeService.findById(id);
			List<Photos> photos = photosService.findByTypeid(id,3);
			map.put("destination", destination);
			map.put("photos", photos);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping("/get_destination")
	@ResponseBody
	public Object getDestination(@QueryParam("name") String name, Model model,Photos photo) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			name = name.trim();
			String names=new String(name.getBytes("ISO-8859-1"),"UTF-8");
			List<Photos> photos = photosService.findByName(names,3);
			Landscape destination = landscapeService.findByName(names);// 封面图片
			if(destination != null){
				Photos p = new Photos();
				p.setUrl(destination.getPhoto());
				p.setName(destination.getName());
				p.setTypeid(destination.getId());
				photos.add(0, p);
			}
			map.put("photos", photos);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping("/get_photos")
	@ResponseBody
	public Object getphotos(@QueryParam("id") long id,@QueryParam("type") long type,Model model,Photos photo) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Photos> photos = photosService.findByTypeid(id,3);
			map.put("photos", photos);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	
	
	@RequestMapping("/uploadPhotoLandscape")
	@ResponseBody
	public String uploadPhotoLandscape(@Context HttpServletRequest request){
		Account a = ShiroStatelessUtils.getLoginAccount();
		String savePaht = "";
		try {
			savePaht = FileUploadDownloadUtil.uploadHeadShow(request, "jpg", "beae");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePaht;
	}
	
	@RequestMapping("/downPhotoLandscape")
	@ResponseBody
	public String downPhotoLandscape(@Context HttpServletRequest request){
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
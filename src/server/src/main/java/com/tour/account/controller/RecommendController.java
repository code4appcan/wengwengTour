package com.tour.account.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.account.entity.Food;
import com.tour.account.entity.Landscape;
import com.tour.account.entity.Recommend;
import com.tour.account.entity.Wineshop;
import com.tour.account.service.FoodService;
import com.tour.account.service.LandscapeService;
import com.tour.account.service.RecommendService;
import com.tour.account.service.WineshopService;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.web.Account
 */
@Controller
@RequestMapping(value = "/recommend")
public class RecommendController {

	@Resource
	private RecommendService recommendService;
	@Resource
	private LandscapeService landscapeService;
	@Resource
	private WineshopService wineshopService;
	@Resource
	private FoodService foodService;

	@RequestMapping(value = "/save")
	@ResponseBody
	public Object getRecommends(@QueryParam("id") Long id, @QueryParam("type") Long type,
			@QueryParam("recommend") Long recommend) {
		try {
			Recommend item = new Recommend();
			item.setType(type);
			item.setTypeid(id);
			item.setLevel(recommend.intValue());
			item.setStatus("1");
			
			/*
			 * 由于历史原因，目前系统中维护了两套推荐数据，
			 * 1、原表中的recommend字段
			 * 2、recommend表中记录
			 */
			if (type == 1) {
				Wineshop wineshop = wineshopService.findById(id);
				wineshop.setRecommend(recommend);
				wineshopService.update(wineshop);
				item.setName(wineshop.getName());
				item.setPhoto(wineshop.getPhoto());
				item.setSort(id.intValue());
			} else if (type == 3) {
				Landscape landscape = landscapeService.findById(id);
				landscape.setRecommend(recommend);
				landscapeService.update(landscape);
				item.setName(landscape.getName());
				item.setPhoto(landscape.getPhoto());
				item.setSort(id.intValue());
			} else if (type == 4) {
				Food food = foodService.findById(id);
				food.setRecommend(recommend);
				foodService.update(food);
				item.setName(food.getFoodName());
				item.setPhoto(food.getPhotos());
				item.setSort(id.intValue());
			}
			// 插入推荐表
			recommendService.save(item);
			return ResponseUtils.buildSuccessRes(item);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(@QueryParam("id") Long id, Model model) {
		try {
			Recommend recommend = recommendService.findById(id);
			Long type = recommend.getType();
			Long typeid = recommend.getTypeid();
			
			/*
			 * 更新原表中的推荐标记
			 */
			if (type == 1) {
				Wineshop wineshop = wineshopService.findById(typeid);
				wineshop.setRecommend(0L);
				wineshopService.update(wineshop);
			} else if (type == 3) {
				Landscape landscape = landscapeService.findById(typeid);
				landscape.setRecommend(0L);
				landscapeService.update(landscape);
			} else if (type == 4) {
				Food food = foodService.findById(typeid);
				food.setRecommend(0L);
				foodService.update(food);
			}
			
			/*
			 * 删除推荐表数据
			 */
			Integer i = recommendService.deleteById(id);
			return ResponseUtils.buildSuccessRes(i);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping("/page")
	public Object list(Recommend recommend, Model model) {
		try {
			List<Recommend> list = recommendService.findListByVo(recommend);
			model.addAttribute("list", list);
			return "/found/recommend";
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/found")
	@ResponseBody
	public Object fund(Recommend recommend, Pagination pagination) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			recommend.setStatus("1");
			List<Recommend> recommends = recommendService.listPage(recommend, pagination);
			map.put("recommends", recommends);
			return ResponseUtils.buildSuccessRes(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/json")
	@ResponseBody
	public Object getjson(Recommend recommend, Pagination pagination) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			recommend.setStatus("1");
			List<Recommend> recommends = recommendService.listPage(recommend, pagination);
			List<Recommend> res = new ArrayList<Recommend>();
			if(recommends != null){
				for(Recommend r : recommends){
					if(r.getType() != 4){
						res.add(r);
					}
				}
			}
			map.put("recommends", res);
			return ResponseUtils.buildSuccessRes(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
}
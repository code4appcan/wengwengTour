package com.tour.locations.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;
import com.tour.locations.entity.Locations;
import com.tour.locations.service.LocationsService;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.web.Account
 */
@Controller
public class LocationsController {
	@Resource
	private LocationsService locationsService;
	
	@RequestMapping(value="/save_location")
	@ResponseBody
	public Object save(Locations locations) {
		try {
			int rows = locationsService.save(locations);
			return ResponseUtils.buildSuccessRes(null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 查找附近的 酒店 景点 美食，需要提供经纬度
	 * @param locations
	 * @return
	 */
	@RequestMapping(value="/get_nearby_xy")
	@ResponseBody
	public Object getNearBy(Locations locations) {
		try {
			
			locationsService.searchNearBy(locations);
			return ResponseUtils.buildSuccessRes(null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 查找附近的 酒店 景点 美食， 根据当前酒店或者景点
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get_nearby")
	@ResponseBody
	public Object getNearBy(Long id, int type) {
		try {
			
			return ResponseUtils.buildSuccessRes(null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
}
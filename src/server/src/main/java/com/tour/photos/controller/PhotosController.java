package com.tour.photos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;
import com.tour.photos.entity.Photos;
import com.tour.photos.service.PhotosService;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.web.Account
 */
@Controller
@RequestMapping(value = "/photos")
public class PhotosController {

	@Resource
	private PhotosService photosService;

	@RequestMapping(value="/json")
	@ResponseBody
	public Object json(Long type, Long typeid) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Photos> list = new ArrayList<Photos>();
			if(type != null && typeid != null){
				list = photosService.findByTypeid(typeid, type);
			}
			map.put("photos", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
}
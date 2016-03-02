package com.tour.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.account.entity.Landscape;
import com.tour.account.entity.Travels;
import com.tour.account.service.LandscapeService;
import com.tour.account.service.TravelsService;
import com.tour.frame.upload.FileUploadDownloadUtil;
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
public class TravelsController {
	@Resource
	private TravelsService travelsService;
	@Resource
	private LandscapeService landscapeService;

	@RequestMapping("/travels/list")
	public String list(Travels travels, Pagination pagination, Model model) {
		List<Travels> list = travelsService.listPage(travels, pagination);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/travels/list";
	}

	/**
	 * go to landscape edit page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/travels/add")
	public String add(Model model) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Landscape landscape = new Landscape();
		Pagination pagination = new Pagination();
		pagination.setPageSize(1000);
		List<Landscape> list = landscapeService.listPage(landscape, pagination);
		map.put("landscaps", list);
		return "/travels/add";
	}

	@RequestMapping(value = "/travels/update")
	public String update(Model model, @QueryParam("id") Long id) {
		List<Travels> travels = travelsService.findBy(id);
		model.addAttribute("travels", travels);
		return "/travels/update";
	}

	/**
	 * save landscape info
	 * 
	 * @param landscape
	 * @return
	 */
	@RequestMapping(value = "/travels/save")
	@ResponseBody
	public Object save(Travels travels) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Integer i = 0;
			if (travels.getId() != null && travels.getId() != 0) {
				i = travelsService.update(travels);
			} else {
				i = travelsService.save(travels);
			}
			if (i > 0) {
				map.put("status", 200);
				map.put("data", "保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequestMapping("/uploadPhotoTravels")
	@ResponseBody
	public String uploadPhotoTravels(@Context HttpServletRequest request) {
		String savePaht = "";
		try {
			savePaht = FileUploadDownloadUtil.uploadHeadShow(request, "jpg", "beae");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePaht;
	}

	@RequestMapping(value = "/travel/delete")
	public Object delete(@QueryParam("id") Long id, Travels travels, Pagination pagination, Model model) {
		try {
			Integer deletetravel = travelsService.deleteById(id);
			List<Travels> list = travelsService.listPage(travels, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/travels/list";
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/get_travel")
	@ResponseBody
	public Object getTravel(@QueryParam("id") Long id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Travels travels = travelsService.findById(id);
			map.put("travels", travels);
			return ResponseUtils.buildSuccessRes(travels);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/get_travel_list")
	@ResponseBody
	public Object getTravelList(Travels travels) {
		try {
			Pagination pagination = new Pagination();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Travels> list = travelsService.listPage(travels, pagination);
			if(list != null && !list.isEmpty()){
				for(Travels t : list){
					t.setContent("");
				}
			}
			map.put("travels", list);
			return ResponseUtils.buildSuccessRes(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
}
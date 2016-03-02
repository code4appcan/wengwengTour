package com.tour.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.account.entity.Raiders;
import com.tour.account.service.RaidersService;
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
@RequestMapping(value = "/raiders")
public class RaidersController {

	@Resource
	private RaidersService raidersService;

	/**
	 * go to landscape edit page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		return "/raiders/add";
	}

	@RequestMapping(value = "/update")
	public String update(Model model,@QueryParam("id") Long id) {
		Raiders raiders = raidersService.findById(id);
		model.addAttribute("raiders", raiders);
		return "/raiders/update";
	}
	
	@RequestMapping("/list")
	public String list(Raiders raiders, Pagination pagination, Model model) {
		List<Raiders> list = raidersService.listPage(raiders, pagination);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		return "/raiders/list";
	}

	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(Raiders raiders) {
		try {
			if(raiders.getId() == null || raiders.getId() == 0){
				raidersService.save(raiders);
			}else{
				raidersService.update(raiders);
			}
			return ResponseUtils.buildSuccessRes(raiders);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/get")
	@ResponseBody
	public Object get(@QueryParam("id") Long id) {
		try {
			Raiders raiders = raidersService.findById(id);
			return ResponseUtils.buildSuccessRes(raiders);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(@QueryParam("id") Long id) {
		try {
			Integer i = raidersService.deleteById(id);
			return ResponseUtils.buildSuccessRes(i);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/json")
	@ResponseBody
	public Object json(Raiders raiders, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			if("1".equals(raiders.getTag())){
				raiders.setTag("精品");
			}else{
				raiders.setTag(null);
			}
			List<Raiders> list = raidersService.listPage(raiders, pagination);
			map.put("raiders", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
}
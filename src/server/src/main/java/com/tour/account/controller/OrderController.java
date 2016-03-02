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

import com.tour.account.entity.Order;
import com.tour.account.service.OrderService;
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
public class OrderController {
	@Resource
	private OrderService orderService;
	
	/**
	 * go to landscape edit page
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/order/add")
	public String add(Model model) throws Exception {
		return "/order/add";
	}

	/**
	 * save landscape info
	 * @param landscape
	 * @return
	 */
	@RequestMapping(value="/save_order")
	@ResponseBody
	public Object save(Order orders) {
		try {
			String message = ValidatorUtils.validate(orders);
			if (StringUtils.isBlank(message)) {
				orderService.save(orders);
			}
			return ResponseUtils.buildSuccessRes(orders);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/update_order")
	@ResponseBody
	public Object update(Order orders) {
		try {
			String message = ValidatorUtils.validate(orders);
			if (StringUtils.isBlank(message)) {
				orderService.update(orders);
			}
			return ResponseUtils.buildSuccessRes(orders);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/delete_order")
	@ResponseBody
	public Object delete(@QueryParam("id") Long id,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type) {
		try {
			Integer order = orderService.deleteById(id,typeid,type);
			return ResponseUtils.buildSuccessRes(order);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/get_order")
	@ResponseBody
	public Object get(@QueryParam("userid") Long userid,Order order, Pagination pagination) {
		try {
			List<Order> orders = orderService.findBy(userid,order, pagination);
			return ResponseUtils.buildSuccessRes(orders);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/get_order_list")
	@ResponseBody
	public Object get(Order order, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Order> list = orderService.listPage(order, pagination);
			map.put("order", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping("/order/list")
	public Object list(@QueryParam("keyWords") String keyWords,Order order, Pagination pagination,Model model) {
		try{
			if(keyWords !="" && keyWords !=null){
				String name=new String(keyWords.getBytes("ISO-8859-1"),"UTF-8");
				order.setName(name);
				order.setUserName(name);
				List<Order> list = orderService.listPage(order, pagination);
				model.addAttribute("list", list);
				model.addAttribute("pagination", pagination);
				return "/order/list";
			}
			else{
				List<Order> list = orderService.listPage(order, pagination);
				model.addAttribute("list", list);
				model.addAttribute("pagination", pagination);
				return "/order/list";
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping("/order/update")
	public Object updateOrder(@QueryParam("id") Long id,Order order, Pagination pagination,Model model) {
		try{
			order.setId(id);
			List<Order> list = orderService.listPage(order, pagination);
			model.addAttribute("list", list);
			return "/order/update";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping("/order/delete")
	public Object deletes(@QueryParam("id") Long id,@QueryParam("tyeid") Long typeid,@QueryParam("type") Long type,Order order, Pagination pagination,Model model) {
		try{
			Integer orders = orderService.deleteById(id,typeid,type);
			List<Order> list = orderService.listPage(order, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/order/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	
}
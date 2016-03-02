package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.FoodDao;
import com.tour.account.dao.LandscapeDao;
import com.tour.account.dao.OrderDao;
import com.tour.account.dao.WineshopDao;
import com.tour.account.entity.Food;
import com.tour.account.entity.Landscape;
import com.tour.account.entity.Order;
import com.tour.account.entity.Wineshop;
import com.tour.account.service.OrderService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("orderService")
public  class OrderServiceImpl extends MybatisServiceImpl<Order,Long> implements OrderService {
	@Resource
	private OrderDao orderDao;
	@Resource
	private LandscapeDao landscapeDao;
	@Resource
	private FoodDao foodDao;
	@Resource
	private WineshopDao wineshopDao;
	

	@Override
	protected IMybatisDao<Order, Long> getBaseDao() {
		return orderDao;
	}
	
	@Override
	public List<Order> listPage(Order order, Pagination pagination) {
		return orderDao.listPage(order, pagination);
	}
	@Override
	public List<Order> findBy(Long userid,Order order, Pagination pagination) {
		
		return orderDao.listPage(order, pagination);
	}
	
	public Integer save(Order order){
		orderDao.save(order);
		
		if(order.getType() ==1){
			Wineshop wineshop= new Wineshop();
			wineshop.setId(order.getTypeid());
			
			wineshopDao.orderAdd(wineshop);
		}
		if(order.getType() ==3){
			Landscape landscape= new Landscape();
			landscape.setId(order.getTypeid());
			
			landscapeDao.orderAdd(landscape);
		}
		if(order.getType() ==4){
			Food food= new Food();
			food.setId(order.getTypeid());
			
			foodDao.orderAdd(food);
		}
		return null;
	}
	public Integer deleteById(Long id, Long typeid, Long type){
		orderDao.deleteById(id);
		
		if(type ==1){
			Wineshop wineshop= new Wineshop();
			wineshop.setId(typeid);
			
			wineshopDao.orderSubtract(wineshop);
		}
		if(type ==3){
			Landscape landscape= new Landscape();
			landscape.setId(typeid);
			
			landscapeDao.orderSubtract(landscape);
		}
		if(type ==4){
			Food food= new Food();
			food.setId(typeid);
			
			foodDao.orderSubtract(food);
		}
		return null;
	}
}

package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Order;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface OrderService  extends IMybatisService<Order,Long> {

	List<Order> listPage(Order order, Pagination pagination);


	List<Order> findBy(Long userid, Order order, Pagination pagination);


	
}

package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Food;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface FoodService  extends IMybatisService<Food,Long> {

	List<Food> listPage(Food food, Pagination pagination);

	List<Food> findByHot(Food food, int pageNo, int pageSize);
	
	Integer updateHot(Long id,Long hot);

	Integer updateRecommend(Long id, Long recommend);

	List<Food> findByRecommends(Food food, Pagination pagination);

	public Integer updateType(Long id, Long val, String type);
}

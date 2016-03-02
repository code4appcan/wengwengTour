package com.tour.travelset.service;

import java.util.List;

import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;
import com.tour.travelset.entity.TravelHead;

public interface TravelHeadService extends IMybatisService<TravelHead, Long> {

	/**
	 * 通过ID删除游记对象
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteTravelSetById(Long id);

	/**
	 * 查找单个对象
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	TravelHead selectTravelHeadByEntity(TravelHead entity) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param entity
	 * @param pagination
	 * @return
	 */
	List<TravelHead> listPage(TravelHead entity, Pagination pagination);
}

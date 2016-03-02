package com.tour.travelset.service;

import com.tour.common.service.IMybatisService;
import com.tour.travelset.entity.TravelItem;

public interface TravelItemService extends IMybatisService<TravelItem, Long> {

	/**
	 * 查找单个对象
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	TravelItem selectTravelItemByEntity(TravelItem entity) throws Exception;
}

package com.tour.travelset.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.common.dao.IMybatisDao;
import com.tour.travelset.entity.TravelItem;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.dao.TravelItem
 */
@Repository
public interface TravelItemDao extends IMybatisDao<TravelItem, Long> {

	Integer deleteByHeadId(@Param("headId") Long headId);

	/**
	 * 获得单个对象
	 */
	TravelItem selectTravelItemByEntity(@Param("travelItem") TravelItem entity) throws Exception;

}

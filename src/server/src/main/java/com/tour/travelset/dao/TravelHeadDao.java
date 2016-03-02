package com.tour.travelset.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;
import com.tour.travelset.entity.TravelHead;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.dao.TravelHead
 */
@Repository
public interface TravelHeadDao extends IMybatisDao<TravelHead, Long> {

	/**
	 * 获得单个对象
	 */
	TravelHead selectTravelHeadByEntity(@Param("travel") TravelHead travel) throws Exception;

	/**
	 * 分页查找
	 */
	List<TravelHead> listPage(@Param("travel") TravelHead travel, @Param("pagination") Pagination pagination);
}

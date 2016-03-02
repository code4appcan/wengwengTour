package com.tour.photos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.common.dao.IMybatisDao;
import com.tour.photos.entity.Photos;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.dao.Account
 */
@Repository
public interface PhotosDao extends IMybatisDao<Photos,Long>{

	List<Photos> findBy(@Param("typeid") long typeid, @Param("type") long type);

	List<Photos> findByName(@Param("name")String name, @Param("type")long type);
}

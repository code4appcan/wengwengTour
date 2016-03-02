package com.tour.locations.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.common.dao.IMybatisDao;
import com.tour.locations.entity.Locations;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.dao.Account
 */
@Repository
public interface LocationsDao extends IMybatisDao<Locations,Long>{

Map<String,Object> searchNearBy(@Param("longitude")float longitude,@Param("latitude")float latitude,@Param("distance")Long long1);

}

package com.tour.locations.service;

import java.util.Map;

import com.tour.common.service.IMybatisService;
import com.tour.locations.entity.Locations;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface LocationsService  extends IMybatisService<Locations,Long> {

	Map<String, Object> searchNearBy(float longitude, float latitude,
			Long distance);

	Map<String, Object> searchNearBy(Locations locations);


	
}

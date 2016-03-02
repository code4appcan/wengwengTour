package com.tour.locations.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.locations.dao.LocationsDao;
import com.tour.locations.entity.Locations;
import com.tour.locations.service.LocationsService;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("locationsService")
public  class LocationsServiceImpl extends MybatisServiceImpl<Locations,Long> implements LocationsService {
	@Resource
	private LocationsDao locationspDao;

	@Override
	protected IMybatisDao<Locations, Long> getBaseDao() {
		return locationspDao;
	}
	
	@Override
	public Map<String,Object> searchNearBy(float longitude,float latitude,Long distance){
		return locationspDao.searchNearBy(longitude, latitude, distance);
	}
	@Override
	public Map<String,Object> searchNearBy(Locations locations){
		
		return locationspDao.searchNearBy(locations.getLongitude(), locations.getLatitude(), locations.getDistance());
	}

}

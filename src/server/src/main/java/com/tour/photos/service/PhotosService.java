package com.tour.photos.service;

import java.util.List;

import com.tour.common.service.IMybatisService;
import com.tour.photos.entity.Photos;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface PhotosService  extends IMybatisService<Photos,Long> {

	List<Photos> findByTypeid(long id, long type);

	List<Photos> findByName(String name, long type);

	
}

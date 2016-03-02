package com.tour.photos.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.photos.dao.PhotosDao;
import com.tour.photos.entity.Photos;
import com.tour.photos.service.PhotosService;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("photosService")
public  class PhotosServiceImpl extends MybatisServiceImpl<Photos,Long> implements PhotosService {
	@Resource
	private PhotosDao photosDao;

	@Override
	protected IMybatisDao<Photos, Long> getBaseDao() {
		return photosDao;
	}


	@Override
	public List<Photos> findByTypeid(long id, long type) {
		Photos photos = new Photos();
		photos.setType(type);
		photos.setTypeid(id);
		return 	photosDao.findBy(id,type);
	}


	@Override
	public List<Photos> findByName(String name, long type) {
		
		return photosDao.findByName(name,type);
	}

}

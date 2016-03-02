package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.TravelsDao;
import com.tour.account.entity.Travels;
import com.tour.account.service.TravelsService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;
import com.tour.locations.dao.LocationsDao;
import com.tour.photos.dao.PhotosDao;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("travelsService")
public class TravelsServiceImpl extends MybatisServiceImpl<Travels, Long>implements TravelsService {
	@Resource
	private TravelsDao travelsDao;
	@Resource
	private LocationsDao locationsDao;
	@Resource
	private PhotosDao photosDao;

	@Override
	protected IMybatisDao<Travels, Long> getBaseDao() {
		return travelsDao;
	}

	@Override
	public List<Travels> listPage(Travels travels, Pagination pagination) {
		return travelsDao.listPage(travels, pagination);
	}

	@Override
	public List<Travels> findByUser(Long userid, Travels travel, Pagination pagination) {
		return travelsDao.listPage(travel, pagination);
	}

	public Integer save(Travels travels) {
		return travelsDao.save(travels);
	}

	public Integer update(Travels travels) {
		return travelsDao.update(travels);
	}
}

package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.AdvertisementDao;
import com.tour.account.entity.Advertisement;
import com.tour.account.service.AdvertisementService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("advertisementService")
public  class AdvertisementServiceImpl extends MybatisServiceImpl<Advertisement,Long> implements AdvertisementService {
	@Resource
	private AdvertisementDao advertisementDao;

	@Override
	protected IMybatisDao<Advertisement, Long> getBaseDao() {
		return advertisementDao;
	}
	
	@Override
	public List<Advertisement> listPage(Advertisement advertisement, Pagination pagination) {
		return advertisementDao.listPage(advertisement, pagination);
	}
}

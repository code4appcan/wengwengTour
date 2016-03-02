package com.tour.travelset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;
import com.tour.travelset.dao.TravelHeadDao;
import com.tour.travelset.dao.TravelItemDao;
import com.tour.travelset.entity.TravelHead;
import com.tour.travelset.service.TravelHeadService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 */
@Service("travelHeadService")
public class TravelHeadServiceImpl extends MybatisServiceImpl<TravelHead, Long>implements TravelHeadService {
	@Autowired
	private TravelHeadDao travelHeadDao;
	@Autowired
	private TravelItemDao travelItemDao;

	@Override
	public TravelHead selectTravelHeadByEntity(TravelHead entity) throws Exception {
		return travelHeadDao.selectTravelHeadByEntity(entity);
	}

	@Override
	public List<TravelHead> listPage(TravelHead travel, Pagination pagination) {
		if (pagination == null) {
			pagination = new Pagination();
		}
		return travelHeadDao.listPage(travel, pagination);
	}

	@Override
	protected IMybatisDao<TravelHead, Long> getBaseDao() {
		return this.travelHeadDao;
	}

	@Override
	public Integer deleteTravelSetById(Long id) {
		// 删除主表
		Integer n = travelHeadDao.deleteById(id);
		if (n > 0) {
			// 删除子表
			travelItemDao.deleteByHeadId(id);
		}
		return n;
	}
}

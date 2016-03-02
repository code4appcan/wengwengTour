package com.tour.travelset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.travelset.dao.TravelItemDao;
import com.tour.travelset.entity.TravelItem;
import com.tour.travelset.service.TravelItemService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 */
@Service("travelItemService")
public class TravelItemServiceImpl extends MybatisServiceImpl<TravelItem, Long>implements TravelItemService {
	@Autowired
	private TravelItemDao travelItemDao;

	@Override
	public TravelItem selectTravelItemByEntity(TravelItem entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IMybatisDao<TravelItem, Long> getBaseDao() {
		return this.travelItemDao;
	}

}

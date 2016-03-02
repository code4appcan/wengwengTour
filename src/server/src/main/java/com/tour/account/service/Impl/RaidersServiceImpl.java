package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.RaidersDao;
import com.tour.account.entity.Raiders;
import com.tour.account.service.RaidersService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;

@Service("raidersService")
public class RaidersServiceImpl extends MybatisServiceImpl<Raiders, Long>implements RaidersService {

	@Resource
	private RaidersDao raidersDao;

	@Override
	protected IMybatisDao<Raiders, Long> getBaseDao() {
		return raidersDao;
	}

	@Override
	public List<Raiders> listPage(Raiders raiders, Pagination pagination) {
		return raidersDao.listPage(raiders, pagination);
	}

}

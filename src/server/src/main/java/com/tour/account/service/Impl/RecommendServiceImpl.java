package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.RecommendDao;
import com.tour.account.entity.Recommend;
import com.tour.account.service.RecommendService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("recommenService")
public class RecommendServiceImpl extends MybatisServiceImpl<Recommend, Long>implements RecommendService {
	@Resource
	private RecommendDao recommendDao;

	@Override
	protected IMybatisDao<Recommend, Long> getBaseDao() {
		return this.recommendDao;
	}
	
	@Override
	public List<Recommend> findListByVo(Recommend recommend) {
		return recommendDao.findListByVo(recommend);
	}

	@Override
	public List<Recommend> listPage(Recommend recommend, Pagination pagination) {
		return recommendDao.listPage(recommend, pagination);
	}

}

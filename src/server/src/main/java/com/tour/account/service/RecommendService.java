package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Recommend;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface RecommendService  extends IMybatisService<Recommend,Long> {
	List<Recommend> findListByVo(Recommend recommend);
	
	List<Recommend> listPage(Recommend recommend, Pagination pagination);
}

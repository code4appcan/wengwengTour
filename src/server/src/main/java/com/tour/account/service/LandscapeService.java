package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Landscape;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface LandscapeService  extends IMybatisService<Landscape,Long> {
	List<Landscape> listPage(Landscape landscape, Pagination pagination);
	Integer updateHot(Long id,Long hot);
	Integer updateBanner(Long id,Long banner);
	List<Landscape> findByHot(Landscape landscape,int pageNo, int pageSize);
	Integer updateRecommend(Long id, Long recommend);
	List<Landscape> findByBanner(Landscape landscape, Pagination pagination);
	List<Landscape> findByRecommends(Landscape landscape, Pagination pagination);
	Landscape findByName(String name);
	
	List<Landscape> findListByVo(Landscape landscape);
}

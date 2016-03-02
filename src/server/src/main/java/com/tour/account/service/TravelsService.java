package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Travels;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface TravelsService  extends IMybatisService<Travels,Long> {

	List<Travels> listPage(Travels travels, Pagination pagination);

	List<Travels> findByUser(Long userid, Travels travel, Pagination pagination);
	
	public Integer save(Travels travels);

	public Integer update(Travels travels);
}

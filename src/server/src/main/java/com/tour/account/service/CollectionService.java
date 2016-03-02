package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Collection;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface CollectionService  extends IMybatisService<Collection,Long> {

	List<Collection> listPage(Collection collection, Pagination pagination);

	List<Collection> findByC(long userid,long typeid, long type);

	List<Collection> findBy(Long userid, Collection collection, Pagination pagination);
	
}

package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Advertisement;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface AdvertisementService  extends IMybatisService<Advertisement,Long> {

	List<Advertisement> listPage(Advertisement collection, Pagination pagination);


	
}

package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Raiders;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

public interface RaidersService  extends IMybatisService<Raiders,Long>{

	List<Raiders> listPage(Raiders raiders, Pagination pagination);
}

package com.tour.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.account.entity.Recommend;
import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.dao.Account
 */
@Repository
public interface RecommendDao extends IMybatisDao<Recommend, Long> {

	List<Recommend> findListByVo(@Param("recommend") Recommend recommend);

	List<Recommend> listPage(@Param("recommend") Recommend recommend, @Param("pagination") Pagination pagination);
}

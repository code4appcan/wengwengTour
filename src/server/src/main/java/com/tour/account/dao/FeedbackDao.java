package com.tour.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tour.account.entity.Feedback;
import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;

public interface FeedbackDao extends IMybatisDao<Feedback,Long>{

	List<Feedback> listPage(@Param("comments") Feedback feedback,
			@Param("pagination") Pagination pagination);
}

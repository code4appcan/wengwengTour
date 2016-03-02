package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.FeedbackDao;
import com.tour.account.entity.Feedback;
import com.tour.account.service.FeedbackService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;

@Service("feedbackService")
public class FeedbackServiceImpl extends MybatisServiceImpl<Feedback,Long> implements FeedbackService{

	@Resource
	private FeedbackDao feedbackDao;

	@Override
	protected IMybatisDao<Feedback, Long> getBaseDao() {
		return feedbackDao;
	}

	@Override
	public List<Feedback> listPage(Feedback feedback, Pagination pagination) {
		return feedbackDao.listPage(feedback, pagination);
	}

}

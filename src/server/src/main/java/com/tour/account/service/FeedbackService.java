package com.tour.account.service;

import java.util.List;

import com.tour.account.entity.Feedback;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

public interface FeedbackService extends IMybatisService<Feedback,Long> {
	List<Feedback> listPage(Feedback feedback, Pagination pagination);
}

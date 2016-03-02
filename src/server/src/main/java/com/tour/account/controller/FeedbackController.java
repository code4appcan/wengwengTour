package com.tour.account.controller;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.account.entity.Feedback;
import com.tour.account.service.FeedbackService;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;

/**
 * 
 * @see com.tour.Feedback.web.Feedback
 */
@Controller
public class FeedbackController {
	@Resource
	private FeedbackService feedbackService;
	
	@RequestMapping(value="/save_feedback")
	@ResponseBody
	public Object save(Feedback feedback) {
		try {
			feedback.setStatus(Feedback.NOT_VIEWED);
			feedbackService.save(feedback);
			return ResponseUtils.buildSuccessRes(feedback);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
}
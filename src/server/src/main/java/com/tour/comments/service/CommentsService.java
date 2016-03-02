package com.tour.comments.service;

import java.util.List;
import java.util.Map;

import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;
import com.tour.comments.entity.Comments;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface CommentsService  extends IMybatisService<Comments,Long> {

	List<Comments> listPage(Comments comments, Pagination pagination);

	List<Comments> findBy(Comments comments,Long typeid, Long type, int pageNo, int pageSize);
}

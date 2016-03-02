package com.tour.comments.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Service;

import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;
import com.tour.photos.entity.Photos;
import com.tour.comments.dao.CommentsDao;
import com.tour.comments.entity.Comments;
import com.tour.comments.service.CommentsService;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.comments.service.impl.Comments
 */
@Service("commentsService")
public  class CommentsServiceImpl extends MybatisServiceImpl<Comments,Long> implements CommentsService {
	@Resource
	private CommentsDao commentsDao;

	@Override
	protected IMybatisDao<Comments, Long> getBaseDao() {
		return commentsDao;
	}

	@Override
	public List<Comments> listPage(Comments comments, Pagination pagination) {
		return commentsDao.listPage(comments, pagination);
	}
	
	@Override
	public List<Comments> findBy(Comments comments, Long typeid, Long type, int pageNo, int pageSize) {
		Comments comment = new Comments();
		comment.setType(type);
		comment.setTypeid(typeid);
		
		Pagination page =new  Pagination ();
		if(pageNo==0 ||pageSize==0 ){
			pageNo=1;
			pageSize=10;
		}
		else{
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
		}
		return 	commentsDao.listPage(comments,page);
	}
}



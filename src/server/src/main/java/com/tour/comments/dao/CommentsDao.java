package com.tour.comments.dao;

import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;
import com.tour.comments.entity.Comments;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.comments.dao.Comments
 */
@Repository
public interface CommentsDao extends IMybatisDao<Comments,Long>{
	List<Comments> listPage(@Param("comments") Comments comments,
			@Param("pagination") Pagination pagination);
}

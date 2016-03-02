package com.tour.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.account.entity.Collection;
import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.dao.Account
 */
@Repository
public interface CollectionDao extends IMybatisDao<Collection,Long>{

	/**
	 * 分页查找
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	List<Collection> listPage(@Param("collection") Collection collection,
			@Param("pagination") Pagination pagination);
	
	List<Collection> findByC(@Param("userid")long userid,@Param("typeid")long typeid, @Param("type")long type);

}

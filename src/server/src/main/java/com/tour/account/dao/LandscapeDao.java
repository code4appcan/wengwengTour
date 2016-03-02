package com.tour.account.dao;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.account.entity.Landscape;
import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.dao.Account
 */
@Repository
public interface LandscapeDao extends IMybatisDao<Landscape,Long>{

	/**
	 * 分页查找
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	List<Landscape> listPage(@Param("landscape") Landscape landscape,
			@Param("pagination") Pagination pagination);
	
	List<Landscape> findListByVo(@Param("landscape") Landscape landscape);
	
	Integer updateHot(@Param("id") Long id,@Param("hot") Long hot);
	Integer updateBanner(@Param("id") Long id,@Param("banner") Long banner);
	Integer updateRecommend(@Param("id")Long id, @Param("recommend")Long recommend);

	void orderAdd(Landscape landscape);

	void loveNumAdd(Landscape landscape);

	void loveNumSubtract(Landscape landscape);

	void orderSubtract(Landscape landscape);

	Landscape findByName(@QueryParam("name") String name);

}

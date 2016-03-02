package com.tour.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.account.entity.Food;
import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.dao.Account
 */
@Repository
public interface FoodDao extends IMybatisDao<Food,Long>{

	/**
	 * 分页查找
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	List<Food> listPage(@Param("food") Food food,
			@Param("pagination") Pagination pagination);
	Integer updateHot(@Param("id") Long id,@Param("hot") Long hot);
	Integer updateRecommend(@Param("id")Long id, @Param("recommend")Long recommend);
	Integer updateStroll(@Param("id")Long id, @Param("stroll")Long stroll);

	void loveNumAdd(Food food);

	void orderAdd(Food food);

	void loveNumSubtract(Food food);

	void orderSubtract(Food food);

}

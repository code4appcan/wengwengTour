package com.tour.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.account.entity.Wineshop;
import com.tour.account.entity.WineshopRoom;
import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.dao.Account
 */
@Repository
public interface WineshopDao extends IMybatisDao<Wineshop, Long> {

	/**
	 * 分页查找
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	List<Wineshop> listPage(@Param("wineshop") Wineshop wineshop, @Param("pagination") Pagination pagination);

	Integer updateHot(@Param("id") Long id, @Param("hot") Long hot);

	Integer updateRecommend(@Param("id") Long id, @Param("recommend") Long recommend);

	void orderAdd(Wineshop wineshop);

	void loveNumAdd(Wineshop wineshop);

	void loveNumSubtract(Wineshop wineshop);

	void orderSubtract(Wineshop wineshop);

	List<WineshopRoom> roomlistPage(@Param("room") WineshopRoom room, @Param("pagination") Pagination pagination);
	
	Integer deleteRoomById(@Param("id") Long id);
	
	void saveRoom(WineshopRoom room);
	
	void updateRoom(WineshopRoom room);
	
	WineshopRoom findRoomByid(Long id);
	
}

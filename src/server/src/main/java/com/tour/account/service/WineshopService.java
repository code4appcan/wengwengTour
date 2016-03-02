package com.tour.account.service;

import java.util.List;

import javax.ws.rs.QueryParam;

import com.tour.account.entity.Wineshop;
import com.tour.account.entity.WineshopRoom;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.service.Account
 */
public interface WineshopService  extends IMybatisService<Wineshop,Long> {

	List<Wineshop> listPage(Wineshop wineshop, Pagination pagination);

	List<Wineshop> findByHot(Wineshop wineshop, int pageNo, int pageSize);
	
	Integer updateHot(Long id,Long hot);

	Integer updateRecommend(Long id, Long recommend);

	List<Wineshop> findByRecommends(Wineshop wineshop, Pagination pagination);
	
	Integer deleteById(@QueryParam("id") Long id,@QueryParam("is_collection")Long is_collection ,@QueryParam("is_order")Long is_order);
	
	List<WineshopRoom> roomlistPage(WineshopRoom room, Pagination pagination);
	
	Integer deleteRoomById(@QueryParam("id") Long id);
	
	void saveRoom(WineshopRoom room);
	
	WineshopRoom findRoomByid(Long id);
}

package com.tour.account.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.CollectionDao;
import com.tour.account.dao.OrderDao;
import com.tour.account.dao.WineshopDao;
import com.tour.account.entity.Collection;
import com.tour.account.entity.Order;
import com.tour.account.entity.Wineshop;
import com.tour.account.entity.WineshopRoom;
import com.tour.account.service.WineshopService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;
import com.tour.locations.dao.LocationsDao;
import com.tour.locations.entity.Locations;
import com.tour.photos.dao.PhotosDao;
import com.tour.photos.entity.Photos;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("wineshopService")
public class WineshopServiceImpl extends MybatisServiceImpl<Wineshop, Long>implements WineshopService {
	@Resource
	private WineshopDao wineshopDao;
	@Resource
	private LocationsDao locationsDao;
	@Resource
	private PhotosDao photosDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private CollectionDao collectionDao;

	@Override
	protected IMybatisDao<Wineshop, Long> getBaseDao() {
		return wineshopDao;
	}

	@Override
	public List<Wineshop> listPage(Wineshop wineshop, Pagination pagination) {
		return wineshopDao.listPage(wineshop, pagination);
	}

	@Override
	public List<Wineshop> findByRecommends(Wineshop wineshop, Pagination pagination) {
		wineshop.setRecommend(1L);
		return wineshopDao.listPage(wineshop, pagination);
	}

	@Override
	public List<Wineshop> findByHot(Wineshop wineshop, int pageNo, int pageSize) {
		Pagination page = new Pagination();
		if (pageNo == 0 || pageSize == 0) {
			pageNo = 1;
			pageSize = 10;
		} else {
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
		}
		return wineshopDao.listPage(wineshop, page);
	}

	public Integer save(Wineshop wineshop) {
		wineshopDao.save(wineshop);
		/*
		 * Locations item = new Locations();
		 * item.setLatitude(wineshop.getLatitude());
		 * item.setLongitude(wineshop.getLongitude());
		 * item.setName(wineshop.getName());
		 * item.setDescription(wineshop.getComments());
		 * item.setTypeid(wineshop.getId()); item.setType((long) 1);
		 * locationsDao.save(item);
		 */

		/*
		 * Photos photos = new Photos(); photos.setUrl(wineshop.getPhoto());
		 * photos.setTypeid(wineshop.getId()); photos.setType((long) 1);
		 * photos.setName(wineshop.getName()); photosDao.save(photos);
		 */
		return null;
	}

	public Integer update(Wineshop wineshop) {
		wineshopDao.update(wineshop);

		/*
		 * Locations item = new Locations();
		 * item.setLatitude(wineshop.getLatitude());
		 * item.setLongitude(wineshop.getLongitude());
		 * item.setName(wineshop.getAddress());
		 * item.setDescription(wineshop.getFeature());
		 * item.setTypeid(wineshop.getId()); item.setType((long) 1);
		 * locationsDao.update(item);
		 */

		/*
		 * Photos photos = new Photos(); photos.setUrl(wineshop.getPhoto());
		 * photos.setTypeid(wineshop.getId()); photos.setType((long) 1);
		 * photos.setName(wineshop.getAddress()); photosDao.update(photos);
		 * 
		 * Order order = new Order(); order.setPhoto(wineshop.getPhoto());
		 * order.setTypeid(wineshop.getId()); order.setType((long) 1);
		 * order.setName(wineshop.getAddress()); orderDao.update(order);
		 */

		Collection collection = new Collection();
		collection.setPhoto(wineshop.getPhoto());
		collection.setTypeid(wineshop.getId());
		collection.setType((long) 1);
		collection.setName(wineshop.getName());
		collectionDao.update(collection);
		return null;
	}

	public Integer deleteById(Long id, Long is_collection, Long is_order) {
		wineshopDao.deleteById(id);

		Locations item = new Locations();
		item.setTypeid(id);
		item.setType((long) 1);
		locationsDao.deleteByTypeid(item);

		Photos photos = new Photos();
		photos.setTypeid(id);
		photos.setType((long) 1);
		photosDao.deleteByTypeid(photos);

		if (is_order != 0 && is_order != null) {
			Order order = new Order();
			order.setTypeid(id);
			order.setType((long) 1);
			orderDao.deleteByTypeid(order);
		}

		if (is_collection != 0 && is_collection != null) {
			Collection collection = new Collection();
			collection.setTypeid(id);
			collection.setType((long) 1);
			collectionDao.deleteByTypeid(collection);
		}

		return null;
	}

	public Integer updateHot(Long id, Long hot) {
		Wineshop wineshop = new Wineshop();
		wineshop.setId(id);
		wineshop.setHot(hot);
		wineshopDao.updateHot(id, hot);
		return null;
	}

	public Integer updateRecommend(Long id, Long recommend) {
		wineshopDao.updateRecommend(id, recommend);
		return null;
	}

	@Override
	public List<WineshopRoom> roomlistPage(WineshopRoom room, Pagination pagination) {
		return wineshopDao.roomlistPage(room, pagination);
	}

	@Override
	public Integer deleteRoomById(Long id) {
		return wineshopDao.deleteRoomById(id);
	}

	@Override
	public void saveRoom(WineshopRoom room) {
		if(room.getId() != null){
			wineshopDao.updateRoom(room);
		}else{
			room.setCdate(new Date());
			wineshopDao.saveRoom(room);
		}
	}

	@Override
	public WineshopRoom findRoomByid(Long id) {
		return wineshopDao.findRoomByid(id);
	}
}

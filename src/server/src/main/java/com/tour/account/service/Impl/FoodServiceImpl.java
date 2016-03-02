package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.CollectionDao;
import com.tour.account.dao.FoodDao;
import com.tour.account.dao.OrderDao;
import com.tour.account.entity.Collection;
import com.tour.account.entity.Food;
import com.tour.account.entity.Order;
import com.tour.account.service.FoodService;
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
@Service("foodService")
public  class FoodServiceImpl extends MybatisServiceImpl<Food,Long> implements FoodService {
	@Resource
	private FoodDao foodDao;
	@Resource
	private LocationsDao locationsDao;
	@Resource
	private PhotosDao photosDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private CollectionDao collectionDao;

	@Override
	protected IMybatisDao<Food, Long> getBaseDao() {
		return foodDao;
	}
	
	@Override
	public List<Food> listPage(Food food, Pagination pagination) {
		return foodDao.listPage(food, pagination);
	}
	@Override
	public List<Food> findByRecommends(Food food, Pagination pagination) {
		food.setRecommend(1L);
		return foodDao.listPage(food, pagination);
	}
	@Override
	public List<Food> findByHot(Food food,int pageNo, int pageSize) {
		Pagination page =new  Pagination ();
		if(pageNo==0 ||pageSize==0 ){
			pageNo=1;
			pageSize=10;
		}
		else{
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
		}
		return foodDao.listPage(food, page);
	}
	
	public Integer save(Food food){
		foodDao.save(food);
		Locations item = new Locations();
		item.setLatitude(food.getLatitude());
		item.setLongitude(food.getLongitude());
		item.setName(food.getFoodName());
		item.setDescription(food.getTags());
		item.setTypeid(food.getId());
		item.setType((long) 4);
		return locationsDao.save(item);
		
		/*Photos photos = new Photos();
		photos.setUrl(food.getPhotos());
		photos.setTypeid(food.getId());
		photos.setType((long) 4);
		photos.setName(food.getFoodName());
		photosDao.save(photos);
		return null;*/
	}
	public Integer update(Food food){
		foodDao.update(food);
		Locations item = new Locations();
		item.setLatitude(food.getLatitude());
		item.setLongitude(food.getLongitude());
		item.setName(food.getAddress());
		item.setDescription(food.getIntroduction());
		item.setTypeid(food.getId());
		item.setType((long) 4);
		locationsDao.update(item);

		/*Photos photos = new Photos();
		photos.setUrl(food.getPhotos());
		photos.setTypeid(food.getId());
		photos.setType((long) 4);
		photos.setName(food.getAddress());
		photosDao.update(photos);

		Order order = new Order();
		order.setPhoto(food.getPhotos());
		order.setTypeid(food.getId());
		order.setType((long) 4);
		order.setName(food.getAddress());
		orderDao.update(order);*/
		
		/*Collection collection = new Collection();
		collection.setPhoto(food.getPhotos());
		collection.setTypeid(food.getId());
		collection.setType((long) 4);
		collection.setName(food.getFoodName());
		collectionDao.update(collection);*/
		return null;
	}
	public Integer deleteById(Long id,Long is_collection ,Long is_order){
		foodDao.deleteById(id);
		
		Locations item = new Locations();
		item.setTypeid(id);
		item.setType((long) 4);
		locationsDao.deleteByTypeid(item);

		Photos photos = new Photos();
		photos.setTypeid(id);
		photos.setType((long) 4);
		photosDao.deleteByTypeid(photos);

		if(is_order !=0 &&is_order !=null ){
			Order order = new Order();
			order.setTypeid(id);
			order.setType((long) 4);
			orderDao.deleteByTypeid(order);
		}

		if(is_collection !=0 &&is_collection !=null ){
			Collection collection = new Collection();
			collection.setTypeid(id);
			collection.setType((long) 4);
			collectionDao.deleteByTypeid(collection);
		}
		return null;
	}
	public Integer updateHot(Long id,Long hot){
		Food food =new Food();
		food.setId(id);
		food.setHot(hot);
		foodDao.updateHot(id,hot);
		return null;
	}
	public Integer updateRecommend(Long id,Long recommend){
		foodDao.updateRecommend(id,recommend);
		return null;
	}

	public Integer updateType(Long id, Long val, String type) {
		Integer flag = 0;
		if ("hot".equals(type)) {
			flag = foodDao.updateHot(id, val);
		} else if ("recommend".equals(type)) {
			flag = foodDao.updateRecommend(id, val);
		} else if ("stroll".equals(type)) {
			flag = foodDao.updateStroll(id, val);
		}
		return flag;
	} 
}

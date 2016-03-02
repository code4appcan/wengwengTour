package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.CollectionDao;
import com.tour.account.dao.LandscapeDao;
import com.tour.account.dao.OrderDao;
import com.tour.account.entity.Collection;
import com.tour.account.entity.Landscape;
import com.tour.account.entity.Order;
import com.tour.account.service.LandscapeService;
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
@Service("landscapeService")
public  class LandscapeServiceImpl extends MybatisServiceImpl<Landscape,Long> implements LandscapeService {
	@Resource
	private LandscapeDao landscapeDao;
	@Resource
	private LocationsDao locationsDao;
	@Resource
	private PhotosDao photosDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private CollectionDao collectionDao;

	@Override
	protected IMybatisDao<Landscape, Long> getBaseDao() {
		return landscapeDao;
	}
	@Override
	public List<Landscape> listPage(Landscape landscape, Pagination pagination) {
		return landscapeDao.listPage(landscape, pagination);
	}
	@Override
	public List<Landscape> findByBanner(Landscape landscape, Pagination pagination) {
		return landscapeDao.listPage(landscape, pagination);
	}
	@Override
	public List<Landscape> findByRecommends(Landscape landscape, Pagination pagination) {
		landscape.setRecommend(1L);
		return landscapeDao.listPage(landscape, pagination);
	}
	@Override
	public List<Landscape> findByHot(Landscape landscape,int pageNo, int pageSize) {
		Pagination page =new  Pagination ();
		if(pageNo==0 ||pageSize==0 ){
			pageNo=1;
			pageSize=10;
		}
		else{
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
		}
		return landscapeDao.listPage(landscape, page);
	}
	
	public Integer save(Landscape landscape){
		landscapeDao.save(landscape);
		/*Locations item = new Locations();
		item.setLatitude(landscape.getLatitude());
		item.setLongitude(landscape.getLongitude());
		item.setName(landscape.getName());
		item.setDescription(landscape.getFeature());
		item.setTypeid(landscape.getId());
		item.setType((long) 3);
		locationsDao.save(item);*/
		
		/*Photos photos = new Photos();
		photos.setUrl(landscape.getPhoto());
		photos.setTypeid(landscape.getId());
		photos.setType((long) 3);
		photos.setName(landscape.getName());
		photosDao.save(photos);
		if(landscape.getPhoto1() !=null && landscape.getPhoto1() !=""){
			photos.setUrl(landscape.getPhoto1());
			photosDao.save(photos);
		}
		if(landscape.getPhoto2() !=null && landscape.getPhoto2() !=""){
			photos.setUrl(landscape.getPhoto2());
			photosDao.save(photos);
		}
		if(landscape.getPhoto3() !=null && landscape.getPhoto3() !=""){
			photos.setUrl(landscape.getPhoto3());
			photosDao.save(photos);
		}
		if(landscape.getPhoto4() !=null && landscape.getPhoto4() !=""){
			photos.setUrl(landscape.getPhoto4());
			photosDao.save(photos);
		}*/
		return null;
	}
	public Integer update(Landscape landscape){
		landscapeDao.update(landscape);
		/*Locations item = new Locations();
		item.setLatitude(landscape.getLatitude());
		item.setLongitude(landscape.getLongitude());
		item.setName(landscape.getName());
		item.setDescription(landscape.getFeature());
		item.setTypeid(landscape.getId());
		item.setType((long) 3);
		locationsDao.update(item);*/
		
		/*Photos photos = new Photos();
		photos.setUrl(landscape.getPhoto());
		photos.setTypeid(landscape.getId());
		photos.setType((long) 3);
		photos.setName(landscape.getName());
		photosDao.save(photos);
		
		if((landscape.getPhoto() !=null && landscape.getPhoto() !="")||(landscape.getName() !=null && landscape.getName() !="")){
			Order order = new Order();
			if(landscape.getPhoto() !=null && landscape.getPhoto() !=""){
				order.setPhoto(landscape.getPhoto());
			}
			if(landscape.getName() !=null && landscape.getName() !=""){
				order.setName(landscape.getName());
			}
			order.setTypeid(landscape.getId());
			order.setType((long) 3);
			orderDao.update(order);
		
			Collection collection = new Collection();
			if(landscape.getPhoto() !=null && landscape.getPhoto() !=""){
				collection.setPhoto(landscape.getPhoto());
			}
			if(landscape.getName() !=null && landscape.getName() !=""){
				collection.setName(landscape.getName());
			}
			collection.setTypeid(landscape.getId());
			collection.setType((long) 3);
			collectionDao.update(collection);
		}*/
		return null;
	}
	public Integer deleteById(Long id,Long is_collection ,Long is_order){
		landscapeDao.deleteById(id);
		
		Locations item = new Locations();
		item.setTypeid(id);
		item.setType((long) 3);
		locationsDao.deleteByTypeid(item);

		Photos photos = new Photos();
		photos.setTypeid(id);
		photos.setType((long) 3);
		photosDao.deleteByTypeid(photos);

		if(is_order !=0 &&is_order !=null ){
			Order order = new Order();
			order.setTypeid(id);
			order.setType((long) 3);
			orderDao.deleteByTypeid(order);
		}

		if(is_collection !=0 &&is_collection !=null ){
			Collection collection = new Collection();
			collection.setTypeid(id);
			collection.setType((long) 3);
			collectionDao.deleteByTypeid(collection);
		}
		return null;
	}
	public Integer updateHot(Long id,Long hot){
		Landscape landscape =new Landscape();
		landscape.setId(id);
		landscape.setHot(hot);
		landscapeDao.updateHot(id,hot);
		return null;
	}
	public Integer updateBanner(Long id,Long banner){
		Landscape landscape =new Landscape();
		landscape.setId(id);
		landscape.setBanner(banner);
		landscapeDao.updateBanner(id,banner);
		return null;
	}
	public Integer updateRecommend(Long id,Long recommend){
		landscapeDao.updateRecommend(id,recommend);
		return null;
	}
	@Override
	public Landscape findByName(String name) {
		
		return landscapeDao.findByName(name);
	}
	@Override
	public List<Landscape> findListByVo(Landscape landscape) {
		return landscapeDao.findListByVo(landscape);
	}

}

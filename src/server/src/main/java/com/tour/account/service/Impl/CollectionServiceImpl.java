package com.tour.account.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tour.account.dao.CollectionDao;
import com.tour.account.dao.FoodDao;
import com.tour.account.dao.LandscapeDao;
import com.tour.account.dao.WineshopDao;
import com.tour.account.entity.Collection;
import com.tour.account.entity.Food;
import com.tour.account.entity.Landscape;
import com.tour.account.entity.Wineshop;
import com.tour.account.service.CollectionService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("collectionService")
public  class CollectionServiceImpl extends MybatisServiceImpl<Collection,Long> implements CollectionService {
	@Resource
	private CollectionDao collectionDao;
	@Resource
	private LandscapeDao landscapeDao;
	@Resource
	private FoodDao foodDao;
	@Resource
	private WineshopDao wineshopDao;
	
	@Override
	protected IMybatisDao<Collection, Long> getBaseDao() {
		return collectionDao;
	}
	
	@Override
	public List<Collection> listPage(Collection collection, Pagination pagination) {
		return collectionDao.listPage(collection, pagination);
	}
	@Override
	public List<Collection> findBy(Long userid, Collection collection, Pagination pagination) {
		return collectionDao.listPage(collection, pagination);
	}
	@Override
	public List<Collection> findByC(long userid,long typeid, long type) {
		Collection collection = new Collection();
		collection.setUserid(userid);
		collection.setType(type);
		collection.setTypeid(typeid);
		return 	collectionDao.findByC(userid,typeid,type);
	}
	public Integer save(Collection collection){
		collectionDao.save(collection);
		
		if(collection.getType() ==1){
			Wineshop wineshop= new Wineshop();
			wineshop.setId(collection.getTypeid());
			wineshopDao.loveNumAdd(wineshop);
		}else if(collection.getType() ==3){
			Landscape landscape= new Landscape();
			landscape.setId(collection.getTypeid());
			
			landscapeDao.loveNumAdd(landscape);
		}else if(collection.getType() ==4){
			Food food= new Food();
			food.setId(collection.getTypeid());
			
			foodDao.loveNumAdd(food);
		}
		return null;
	}
	public Integer deleteById(Long id, Long typeid, Long type){
		collectionDao.deleteById(id);
		
		if(type ==1){
			Wineshop wineshop= new Wineshop();
			wineshop.setId(typeid);
			
			wineshopDao.loveNumSubtract(wineshop);
		}
		if(type ==3){
			Landscape landscape= new Landscape();
			landscape.setId(typeid);
			
			landscapeDao.loveNumSubtract(landscape);
		}
		if(type ==4){
			Food food= new Food();
			food.setId(typeid);
			
			foodDao.loveNumSubtract(food);
		}
		return null;
	}
}

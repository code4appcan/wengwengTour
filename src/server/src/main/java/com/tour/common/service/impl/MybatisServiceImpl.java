package com.tour.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tour.account.entity.Landscape;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.IMybatisService;
import com.tour.frame.utils.page.Pagination;

public  abstract class MybatisServiceImpl<T,ID extends Serializable> implements
		IMybatisService<T,ID> {
	
	protected abstract IMybatisDao<T,ID> getBaseDao();
	@Override
	public Integer save(T item) {
		return getBaseDao().save(item);
	}

	@Override
	public Integer update(T item) {
		return getBaseDao().update(item);
	}
	@Override
	public Integer updateHot(ID id,Long hot) {
		return getBaseDao().updateHot(id ,hot);
	}
	@Override
	public Integer updateBanner(ID id,Long banner) {
		return getBaseDao().updateBanner(id ,banner);
	}
	
	@Override
	public T findById(ID id) {
		return getBaseDao().findById(id);
	}
	@Override
	public List<T> findByIds(ID id){
		return getBaseDao().findByIds(id);
	}
	@Override
	public List<T> findBy(ID id) {
		return getBaseDao().findBy(id);
	}
	@Override
	public List<T> findBy(ID id, Long type) {
		return getBaseDao().findBy(id,  type);
	}
//	@Override
//	public List<T> findByC(ID userid, Long typeid, Long type) {
//		return getBaseDao().findByC(userid, typeid, type);
//	}
	@Override
	public List<T> findByHot() {
		return getBaseDao().findByHot();
	}
	@Override
	public List<T> findByBanner() {
		return getBaseDao().findByBanner();
	}
	@Override
	public List<T> findByRecommend() {
		return getBaseDao().findByRecommend();
	}
	@Override
	public List<T> findByRecommends() {
		return getBaseDao().findByRecommends();
	}

	@Override
	public Integer deleteById(ID id) {
		return getBaseDao().deleteById(id);
	}
	@Override
	public Integer deleteById(ID id,Long is_collection ,Long is_order) {
		return getBaseDao().deleteById( id, is_collection , is_order);
	}

	@Override
	public Integer deleteByIds(ID[] ids) {
		return getBaseDao().deleteByIds(ids);
	}

	@Override
	public Integer deleteByTypeid(T item) {
		return getBaseDao().deleteByTypeid(item);
	}

	@Override
	public Integer findCountBy(T item) {
		return getBaseDao().findCountBy(item);
	}

	@Override
	public List<T> findListBy(T item, String sortColumn, String des) {
		return getBaseDao().findListBy(item, sortColumn, des);
	}

	@Override
	public List<T> findListBy(T item) {
		return getBaseDao().findListBy(item);
	}
	@Override
	public List<T> findListByMap(Map<String,Object> map){
		return getBaseDao().findListByMap(map);
	}
	@Override
	public List<T> findList(){
		return getBaseDao().findList();
	}
	@Override
	public Integer save(String keyId, Object parameter) {
		return getBaseDao().save(keyId, parameter);
	}
	@Override
	public Integer update(String keyId, Object parameter) {
		return getBaseDao().update(keyId, parameter);
	}
	@Override
	public T findBy(String keyId, Object parameter) {
		return getBaseDao().findBy(keyId, parameter);
	}
	@Override
	public int deleteBy(String keyId, Object parameter) {
		return getBaseDao().deleteBy(keyId, parameter);
	}
	@Override
	public List<T> selectList(String keyId, Object parameter) {
		return getBaseDao().selectList(keyId, parameter);
	}
	@Override
	public List<Map<String, Object>> selectListMap(String keyId,
			Object parameter) {
		return getBaseDao().selectListMap(keyId, parameter);
	}
	
}

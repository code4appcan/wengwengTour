package com.tour.account.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.tour.account.dao.AccountDao;
import com.tour.account.dao.FriendDao;
import com.tour.account.entity.Account;
import com.tour.account.entity.Friend;
import com.tour.account.service.AccountService;
import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.account.service.impl.Account
 */
@Service("accountService")
public class AccountServiceImpl extends MybatisServiceImpl<Account,Long> implements AccountService {
	@Resource
	private AccountDao accountDao;
	@Resource
	private FriendDao friendDao;

	@Override
	protected IMybatisDao<Account, Long> getBaseDao() {
		return accountDao;
	}
	@Override
	public Account selectByUsername(String username) {
		return accountDao.selectByUsername(username);
	}
	@Override
	public Account selectByLoginProof(String loginProof) {
		return accountDao.selectByLoginProof(loginProof);
	}
	@Override
	public boolean checkSameEmail(String mail) {
		Integer i = accountDao.checkSameEmail(mail);
		return i > 0;
	}

	@Override
	public boolean checkSameMobile(String mobile) {
		Integer i = accountDao.checkSameMoblie(mobile);
		return i > 0;
	}
	
	@Override
	public boolean checkSameUserName(String userName) {
		Integer i = accountDao.checkSameUserName(userName);
		return i > 0;
	}
	
	
	
	public Long getIdbyEmail(String email){
		return accountDao.getIdbyEmail(email);
	}
	
	@Override
	public Set<String> findPermissions(String username) {
		return accountDao.findPermissions(username);
	}
	@Override
	public List<Map<String,Object>> getFriends(Long id,String status) {
		Preconditions.checkNotNull(id, "id不能为空");
		return friendDao.friendlist(id,status);
	}

	
	@Override
	public Integer designMakeFriend(Long userID,Long friendID,String status1,String status2){
		Preconditions.checkNotNull(userID, "userID不能为空");
		Preconditions.checkNotNull(friendID, "friendID不能为空");
		try {
			Friend entity = new Friend();
			entity.setUserID(userID);
			entity.setFriendID(friendID);
			entity.setStatus(status1);//申请好友
			Long id = friendDao.selectByUserFriend(userID, friendID);
			if(id != null){
				entity.setId(id);
				friendDao.update(entity);
			}else{
				friendDao.save(entity);
			}
			
			Friend entity1 = new Friend();
			entity1.setUserID(friendID);
			entity1.setFriendID(userID);
			entity1.setStatus(status2);//被申请好友
			Long id1 = friendDao.selectByUserFriend(friendID, userID);
			if(id1 != null){
				entity1.setId(id1);
				friendDao.update(entity1);
			}else{
				friendDao.save(entity1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	

	@Override
	public List<Account> listPage(Account account, Pagination pagination) {
		return accountDao.listPage(account, pagination);
	}
	
	@Override
	public List<Map<String,Object>> getFriends(Long id,Pagination pagination,String status) {
		Preconditions.checkNotNull(id, "id参数不能为空");
		Preconditions.checkNotNull(pagination.getPageSize(), "pageSize参数不能为空");
		Preconditions.checkNotNull(pagination.getPageNo(), "pageNo参数不能为空");
		return friendDao.friendlistPage(id,status, pagination);
	}
	
	@Override
	public List<Map<String,Object>> getFriends(Long id,Pagination pagination,Map<String, Object> map) {
		return friendDao.friendListPage(pagination, map);
	}
	
	@Override
	public Long saveAccount(Account entity) {
		Preconditions.checkNotNull(entity.getPassword(), "密码不能为空");
		
		if(StringUtils.isNotBlank(entity.getEmail())&&checkSameEmail(entity.getEmail())){
			throw new IllegalArgumentException("该邮箱已经注册");
		}
		if(StringUtils.isNotBlank(entity.getMobile())&&checkSameMobile(entity.getMobile())){
			throw new IllegalArgumentException("该手机已经注册");
		}
		if(StringUtils.isNotBlank(entity.getUserName())&&checkSameMobile(entity.getUserName())){
			throw new IllegalArgumentException("该用户名已经注册");
		}
		String password = new Md5Hash(entity.getPassword()).toString();
		entity.setPassword(password);
//		entity.setUserName("username"+Math.random()*100);
		entity.setDisabled(0);
		accountDao.save(entity);
		
		return entity.getId();
	}
	
	@Override
	public String getUserNameByID(Long userID) {
		return accountDao.getUserNameByID(userID);
	}

	@Override
	public void confirmMakeFriend(Long userID,Long friendID,String opt){
		String state;
		if("true".equals(opt)){
			state = Const.Friend.status.valid;//同意添加为好友
		}else{
			state = Const.Friend.status.invalid;//决绝添加
		}
		designMakeFriend(userID, friendID, state, state);
	}
	@Override
	public Integer designMakeFriend(@Param("userID") Long userID,
			@Param("friendName") String friendName,
			@Param("state1") String state1, @Param("state1") String state2) {
		
		Preconditions.checkNotNull(userID, "userID不能为空");
		Preconditions.checkNotNull(friendName, "friendName不能为空");
		
		Long friendID = this.getIdbyEmail(friendName);
		if(friendID == null){
			throw new IllegalArgumentException("不存在该用户");
		}
		try {
			Friend entity = new Friend();
			entity.setUserID(userID);
			entity.setFriendID(friendID);
			entity.setStatus(state1);//申请好友
			Long id = friendDao.selectByUserFriend(userID, friendID);
			if(id != null){
				entity.setId(id);
				friendDao.update(entity);
			}else{
				friendDao.save(entity);
			}
			
			Friend entity1 = new Friend();
			entity1.setUserID(friendID);
			entity1.setFriendID(userID);
			entity1.setStatus(state2);//被申请好友
			Long id1 = friendDao.selectByUserFriend(friendID, userID);
			if(id1 != null){
				entity1.setId(id1);
				friendDao.update(entity1);
			}else{
				friendDao.save(entity1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	@Override
	public Account findByPhone(String phone) throws Exception {
		return accountDao.findByPhone(phone);
	}
}

package com.tour.account.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.account.entity.Account;
import com.tour.common.dao.IMybatisDao;
import com.tour.frame.utils.page.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.dao.Account
 */
@Repository
public interface AccountDao extends IMybatisDao<Account,Long>{

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Account selectAccountById(Long id) throws Exception;


	/**
	 * 根据用户名和用户类型查找用户
	 * 
	 * @param username
	 * @param userType
	 * @return
	 */
	Account selectByUsername(String username);
	
	Account selectByLoginProof(String loginProof);

	/**
	 * 获取用户权限标识
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	Set<String> findPermissions(String username);	
	
	Integer checkSameEmail(String email);
	
	Integer checkSameMoblie(String mobile);
	
	Integer checkSameUserName(String userName);
	
	String getUserNameByID(Long userID);
	
	Long getIdbyEmail(String email);
	/**
	 * 分页查找
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	List<Account> listPage(@Param("user") Account account,
			@Param("pagination") Pagination pagination);
	
	Account findByPhone(String phone) throws Exception;

}

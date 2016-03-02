package com.tour.resources.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.common.dao.IMybatisDao;
import com.tour.resources.entity.SysRight;
import com.tour.resources.entity.SysRole;
import com.tour.resources.entity.SysUser;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.dao.SysUser
 */
@Repository
public interface SysUserDao extends IMybatisDao<SysUser, Long> {
	/**
	 * 保存
	 *
	 */
	public Integer saveSysUser(SysUser entity) throws Exception;

	/**
	 * 修改
	 *
	 */
	public Integer updateSysUserById(SysUser entity) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SysUser selectSysUserById(Object id) throws Exception;
	
	SysUser selectSysUserByEntity(@Param("sysUser") SysUser entity) throws Exception;

	List<SysUser> findByAccountId(Long id) throws Exception;

	List<SysUser> list(@Param("sysUser") SysUser sysUser);
	
	List<SysRight> selectRoleByUserId(@Param("userId") Long id) throws SQLException;
	
	List<SysUser> selectUserByRole(@Param("role") SysRole role); 
	
	/**
	 * 删除现有用户角色
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer deleteRoleByUserId(@Param("userId") Long userId) throws SQLException;

	public Integer saveUserRole(@Param("roleList") List<Map<String, Long>> roleList) throws SQLException;
}

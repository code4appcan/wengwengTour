package com.tour.resources.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tour.common.dao.IMybatisDao;
import com.tour.resources.entity.SysRight;
import com.tour.resources.entity.SysRole;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.dao.SysRole
 */
@Repository
public interface SysRoleDao extends IMybatisDao<SysRole, Long> {
	/**
	 * 保存
	 */
	public Integer saveSysRole(SysRole entity) throws SQLException;

	/**
	 * 修改
	 */
	public Integer updateSysRoleById(SysRole entity) throws SQLException;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	SysRole selectSysRoleById(Object id) throws SQLException;

	List<SysRole> findByAccountId(Long id) throws SQLException;

	List<SysRole> list(@Param("sysRole") SysRole sysRole) throws SQLException;
	
	public List<SysRole> getUserRoleByUser(@Param("userId") Long id) throws SQLException;

	public List<SysRight> getRightByRoleId(@Param("roleId") Long id) throws SQLException;

	/**
	 * 删除现有角色权限
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Integer deleteRightByRoleId(@Param("roleId") Long roleId) throws SQLException;

	public Integer saveRoleRight(@Param("rightList") List<Map<String, Long>> rightList) throws SQLException;
}

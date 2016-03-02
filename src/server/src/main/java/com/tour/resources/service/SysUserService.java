package com.tour.resources.service;

import java.util.List;
import java.util.Map;

import com.tour.common.service.IMybatisService;
import com.tour.resources.entity.SysUser;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.service.SysUser
 */
public interface SysUserService extends IMybatisService<SysUser, Long> {
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
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer deleteSysUserById(Long id) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SysUser selectSysUserById(Long id) throws Exception;

	SysUser selectSysUserByEntity(SysUser entity) throws Exception;

	List<SysUser> list(SysUser resource);

	List<Map<String, Object>> selectRoleByUserId(Long id) throws Exception;

	void initSysUserLogin(SysUser entity) throws Exception;

	List<SysUser> selectSysUserByRole(Long roleId) throws Exception;
}

package com.tour.resources.service;

import java.util.List;
import java.util.Map;

import com.tour.common.service.IMybatisService;
import com.tour.resources.entity.SysRole;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.service.SysRole
 */
public interface SysRoleService extends IMybatisService<SysRole, Long> {
	/**
	 * 保存
	 *
	 */
	public Integer saveSysRole(SysRole entity) throws Exception;

	/**
	 * 修改
	 *
	 */
	public Integer updateSysRoleById(SysRole entity) throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer deleteSysRoleById(Long id) throws Exception;

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SysRole selectSysRoleById(Object id) throws Exception;

	List<SysRole> list(SysRole resource) throws Exception;

	/**
	 * 权限查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getRightByRoleId(Long id) throws Exception;

	/**
	 * 保存角色权限
	 * 
	 * @param roleid
	 * @param treeids
	 * @return
	 * @throws Exception
	 */
	public void saveRight(Long roleid, String treeids) throws Exception;
}

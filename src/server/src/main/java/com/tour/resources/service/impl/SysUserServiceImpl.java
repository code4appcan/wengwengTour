package com.tour.resources.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.frame.utils.ContextHolderUtils;
import com.tour.resources.dao.ResourcesDao;
import com.tour.resources.dao.SysRoleDao;
import com.tour.resources.dao.SysUserDao;
import com.tour.resources.entity.Resources;
import com.tour.resources.entity.SysRight;
import com.tour.resources.entity.SysRole;
import com.tour.resources.entity.SysUser;
import com.tour.resources.service.SysUserService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 */
@Service("sysUserService")
public class SysUserServiceImpl extends MybatisServiceImpl<SysUser, Long>implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private ResourcesDao resourcesDao;

	@Override
	protected IMybatisDao<SysUser, Long> getBaseDao() {
		return sysUserDao;
	}

	@Override
	public Integer saveSysUser(SysUser entity) throws Exception {
		Integer res = sysUserDao.saveSysUser(entity);
		if (res > 0) {
			Long id = entity.getId();
			// 删除
			sysUserDao.deleteRoleByUserId(id);

			// 插入角色
			if (StringUtils.isNotBlank(entity.getRoleArr())) {
				String[] roleArr = entity.getRoleArr().split(",");
				List<Map<String, Long>> roleList = new ArrayList<Map<String, Long>>();
				for (int i = 0; i < roleArr.length; i++) {
					Map<String, Long> map = new HashMap<String, Long>();
					map.put("role_id", Long.parseLong(roleArr[i]));
					map.put("user_id", id.longValue());
					roleList.add(map);
				}
				sysUserDao.saveUserRole(roleList);
			}
		}
		return res;
	}

	@Override
	public Integer deleteSysUserById(Long id) throws Exception {
		// 删除用户角色
		sysUserDao.deleteRoleByUserId(id);
		// 删除用户
		return sysUserDao.deleteById(id);
	}

	@Override
	public Integer updateSysUserById(SysUser entity) throws Exception {
		Integer res = sysUserDao.updateSysUserById(entity);
		if (res > 0) {
			Long id = entity.getId();
			// 删除
			sysUserDao.deleteRoleByUserId(id);
			// 插入角色
			if (StringUtils.isNotBlank(entity.getRoleArr())) {
				String[] roleArr = entity.getRoleArr().split(",");
				List<Map<String, Long>> roleList = new ArrayList<Map<String, Long>>();
				for (int i = 0; i < roleArr.length; i++) {
					Map<String, Long> map = new HashMap<String, Long>();
					map.put("role_id", Long.parseLong(roleArr[i]));
					map.put("user_id", id.longValue());
					roleList.add(map);
				}
				sysUserDao.saveUserRole(roleList);
			}
		}
		return res;
	}

	@Override
	public SysUser selectSysUserById(Long id) throws Exception {
		SysUser user = sysUserDao.selectSysUserById(id);
		if (user != null) {
			List<SysRole> roles = sysRoleDao.getUserRoleByUser(id);
			if (roles != null && !roles.isEmpty()) {
				String roleArr = "";
				String roleNameArr = "";
				for (SysRole role : roles) {
					roleArr += role.getId() + ",";
					roleNameArr += role.getName() + ",";
				}
				user.setRoleArr(roleArr);
				user.setRoleNameArr(roleNameArr);
			}
		}
		return user;
	}

	@Override
	public List<SysUser> list(SysUser resource) {
		return sysUserDao.list(resource);
	}

	@Override
	public List<Map<String, Object>> selectRoleByUserId(Long id) throws Exception {
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		List<SysRight> list = sysUserDao.selectRoleByUserId(id);
		if (list != null && !list.isEmpty()) {
			for (SysRight r : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", r.getId());
				map.put("pId", r.getPid());
				map.put("name", r.getName());
				map.put("checked", r.getChecked());
				resList.add(map);
			}
		}
		return resList;
	}

	@Override
	public SysUser selectSysUserByEntity(SysUser entity) throws Exception {
		return sysUserDao.selectSysUserByEntity(entity);
	}

	@Override
	public void initSysUserLogin(SysUser entity) throws Exception {
		HttpSession session = ContextHolderUtils.getSession();
		Long userId = entity.getId();
		String username = entity.getUsername();
		String name = entity.getName();
		List<SysRole> roles = sysRoleDao.getUserRoleByUser(userId);
		List<Resources> resources = resourcesDao.getResourcesByRole(roles);

		session.setAttribute("username", username);
		session.setAttribute("name", name);
		session.setAttribute("user", entity);
		session.setAttribute("roles", roles);
		session.setAttribute("resources", resources);
	}

	@Override
	public List<SysUser> selectSysUserByRole(Long roleId) throws Exception {
		SysRole role = new SysRole();
		role.setId(roleId);
		return sysUserDao.selectUserByRole(role);
	}

}

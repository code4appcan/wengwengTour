package com.tour.resources.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.common.dao.IMybatisDao;
import com.tour.common.service.impl.MybatisServiceImpl;
import com.tour.resources.dao.SysRoleDao;
import com.tour.resources.entity.SysRight;
import com.tour.resources.entity.SysRole;
import com.tour.resources.service.SysRoleService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends MybatisServiceImpl<SysRole, Long>implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	protected IMybatisDao<SysRole, Long> getBaseDao() {
		return sysRoleDao;
	}

	@Override
	public Integer saveSysRole(SysRole entity) throws Exception {
		return sysRoleDao.saveSysRole(entity);
	}

	@Override
	public Integer updateSysRoleById(SysRole entity) throws Exception {
		return sysRoleDao.updateSysRoleById(entity);
	}

	@Override
	public SysRole selectSysRoleById(Object id) throws Exception {
		return sysRoleDao.selectSysRoleById(id);
	}

	@Override
	public List<SysRole> list(SysRole resource) throws Exception {
		return sysRoleDao.list(resource);
	}

	@Override
	public Integer deleteSysRoleById(Long id) throws Exception {
		// 删除权限
		sysRoleDao.deleteRightByRoleId(id);
		return sysRoleDao.deleteById(id);
	}

	@Override
	public List<Map<String, Object>> getRightByRoleId(Long id) throws Exception {
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		List<SysRight> list = sysRoleDao.getRightByRoleId(id);
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
	public void saveRight(Long roleid, String treeids) throws Exception {
		// 删除
		sysRoleDao.deleteRightByRoleId(roleid);
		// 重新插入
		if (StringUtils.isNotBlank(treeids)) {
			String[] treeArr = treeids.split(",");
			List<Map<String, Long>> rightList = new ArrayList<Map<String, Long>>();
			for (int i = 0; i < treeArr.length; i++) {
				Map<String, Long> map = new HashMap<String, Long>();
				String treeid = treeArr[i];
				map.put("role_id", roleid);
				map.put("resources_id", Long.parseLong(treeid));
				rightList.add(map);
			}
			sysRoleDao.saveRoleRight(rightList);
		}
	}

}

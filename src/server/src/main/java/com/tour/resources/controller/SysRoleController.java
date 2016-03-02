package com.tour.resources.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.frame.filter.WebContext;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.page.Pagination;
import com.tour.resources.entity.SysRole;
import com.tour.resources.service.SysRoleService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 */
@Controller
@RequestMapping(value = "/sysrole")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("/list")
	public Object list(SysRole sysRole) {
		try {
			List<SysRole> list = sysRoleService.list(sysRole);
			WebContext.setAttribute("list", list);
			return "/sys/role/list";
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping("/add")
	public String add(Long id) {
		return "/sys/role/add";
	}

	@RequestMapping("/update")
	public String update(Long id) {
		WebContext.setAttribute("roleId", id);
		return "/sys/role/update";
	}

	@RequestMapping("/right")
	public String right(Long id) {
		WebContext.setAttribute("roleId", id);
		return "/sys/role/right";
	}

	@ResponseBody
	@RequestMapping("/getById/{id}")
	public Object getById(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			SysRole r = sysRoleService.selectSysRoleById(id);
			map.put("status", 1);
			map.put("data", r);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(SysRole sysRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Integer i = 0;
			if (sysRole.getId() != null && sysRole.getId() != 0) {
				i = sysRoleService.updateSysRoleById(sysRole);
			} else {
				i = sysRoleService.saveSysRole(sysRole);
			}
			if (i > 0) {
				map.put("status", 200);
				map.put("data", "保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequestMapping("/delete")
	public Object deletes(@QueryParam("id") Long id, Pagination pagination) {
		try {
			sysRoleService.deleteSysRoleById(id);
			SysRole role = new SysRole();
			List<SysRole> list = sysRoleService.list(role);
			WebContext.setAttribute("list", list);
			return "/sys/role/list";
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping("/getRight/{roleid}")
	public Object getRightByRoleId(@PathVariable Long roleid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = sysRoleService.getRightByRoleId(roleid);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/saveRight/{roleid}")
	public Object saveRight(@PathVariable Long roleid, @QueryParam("treeids") String treeids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			if (StringUtils.isNotBlank(treeids)) {
				sysRoleService.saveRight(roleid, treeids);
			}
			map.put("status", 200);
			map.put("data", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}
	
}

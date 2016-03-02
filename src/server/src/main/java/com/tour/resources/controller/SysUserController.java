package com.tour.resources.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.frame.filter.WebContext;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.Md5;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.page.Pagination;
import com.tour.resources.entity.SysUser;
import com.tour.resources.service.SysUserService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 */
@Controller
@RequestMapping(value = "/sysuser")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;

	@ResponseBody
	@RequestMapping(value = "/login")
	public Object loginPost(SysUser sysUser, Model model) throws Exception {
		// type 类型用来标注会员类型,0表示普通会员
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SysUser tempUser = sysUserService.selectSysUserByEntity(sysUser);
			if (tempUser == null) {
				map.put("error", "账号不存在!");
			} else {
				if (StringUtils.isEmpty(sysUser.getPassword())) {
					map.put("error", "请输入密码!");
				} else {
					String md5pwd = Md5.GetMD5Code(sysUser.getPassword());
					if (!md5pwd.equals(tempUser.getPassword())) {
						map.put("error", "您输入的密码不正确!");
					}
				}
			}
			if (map.isEmpty()) {
				// 初始化用户session
				sysUserService.initSysUserLogin(tempUser);
				map.put("status", 200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", "未知错误,请联系管理员.");
		}
		return map;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		// HttpSession session = ContextHolderUtils.getSession();
		HttpSession session = request.getSession(false);// 防止创建Session
		if (session != null) {
			// session.invalidate();
			session.removeAttribute("username");
			session.removeAttribute("name");
			session.removeAttribute("user");
			session.removeAttribute("roles");
			session.removeAttribute("resources");
		}
		return "redirect:/";
	}

	@RequestMapping("/list")
	public String list(SysUser sysUser) {
		List<SysUser> list = sysUserService.list(sysUser);
		WebContext.setAttribute("list", list);
		return "/sys/user/list";
	}

	@RequestMapping("/add")
	public String add(Long id) {
		return "/sys/user/add";
	}

	@RequestMapping("/update")
	public String update(Long id) {
		WebContext.setAttribute("userId", id);
		return "/sys/user/update";
	}

	@RequestMapping("/roles")
	public String roles(Long id) {
		if (id == null) {
			id = 0L;
		}
		WebContext.setAttribute("userId", id);
		return "/sys/user/roles";
	}

	@ResponseBody
	@RequestMapping("/getById/{id}")
	public Object getById(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			SysUser r = sysUserService.selectSysUserById(id);
			map.put("status", 1);
			map.put("data", r);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/checkrole/{roleid}")
	public Object checkrole(@PathVariable Long roleid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			List<SysUser> user = sysUserService.selectSysUserByRole(roleid);
			if (user != null && !user.isEmpty()) {
				map.put("error", "角色已分配，不能删除");
			} else {
				map.put("status", Const.SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", Const.DEFAULT_ERROR);
		}
		return map;
	}

	@RequestMapping("/delete")
	public Object deletes(@QueryParam("id") Long id, Pagination pagination) {
		try {
			sysUserService.deleteSysUserById(id);
			SysUser sysUser = new SysUser();
			List<SysUser> list = sysUserService.list(sysUser);
			WebContext.setAttribute("list", list);
			return "/sys/user/list";
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(SysUser sysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Integer i = 0;
			if (sysUser.getId() != null && sysUser.getId() != 0) {
				i = sysUserService.updateSysUserById(sysUser);
			} else {
				String md5pwd = Md5.GetMD5Code(sysUser.getPassword());
				sysUser.setPassword(md5pwd);
				i = sysUserService.saveSysUser(sysUser);
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

	@ResponseBody
	@RequestMapping("/getRole/{userid}")
	public Object getRoleByUserId(@PathVariable Long userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = sysUserService.selectRoleByUserId(userid);
			map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}
}

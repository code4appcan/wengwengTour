package com.tour.resources.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.frame.filter.WebContext;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.ContextHolderUtils;
import com.tour.resources.entity.Resources;
import com.tour.resources.service.ResourcesService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.web.Resources
 */
@Controller
@RequestMapping(value = "/resources")
public class ResourcesController {
	@Autowired
	private ResourcesService resourcesService;

	@ResponseBody
	@RequestMapping("/currentResources.json")
	public List<Resources> currentResources() {
/*		Long accountId = ShiroSessionUtils.getLoginAccount().getId();
		Long accountId =10l;
		try {
			List<Resources> list = resourcesService.findByAccountId(accountId);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		HttpSession session = ContextHolderUtils.getSession();
		List<Resources> list = (List<Resources>) session.getAttribute("resources");
		return list;
	}

	@RequestMapping("/list")
	public String list(Resources resource) {
		List<Resources> list = resourcesService.list(resource);
		WebContext.setAttribute("list", list);
		return "/sys/resources/list";
	}

	@RequiresPermissions("sys:perm:add")
	@RequestMapping("/add")
	public String add(Long id) {
		Resources r = new Resources();
		r.setType(0);
		List<Resources> list = resourcesService.list(r);
		WebContext.setAttribute("list", list);
		return "/sys/resources/add";
	}

	@ResponseBody
	@RequestMapping("/getById/{id}")
	public Object getById(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Resources r = resourcesService.selectResourcesById(id);
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
	public Object save(Resources resources) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", -1);
		try {
			Integer i = 0;
			if (resources.getId() != null && resources.getId() != 0) {
				i = resourcesService.updateResourcesById(resources);
			} else {
				i = resourcesService.saveResources(resources);
			}
			if (i > 0) {
				map.put("status", 0);
				map.put("data", "保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", Const.DEFAULT_ERROR);
		}
		return map;
	}
}

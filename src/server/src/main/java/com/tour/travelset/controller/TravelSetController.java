package com.tour.travelset.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.frame.upload.FileUploadDownloadUtil;
import com.tour.frame.utils.Const;
import com.tour.frame.utils.DateUtils;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.StringUtil;
import com.tour.frame.utils.page.Pagination;
import com.tour.travelset.entity.TravelHead;
import com.tour.travelset.entity.TravelItem;
import com.tour.travelset.entity.TravelSet;
import com.tour.travelset.service.TravelHeadService;
import com.tour.travelset.service.TravelItemService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 */
@Controller
@RequestMapping(value = "/travelset")
public class TravelSetController {
	@Autowired
	private TravelHeadService travelHeadService;
	@Autowired
	private TravelItemService travelItemService;

	@RequestMapping(value = "/list")
	public String list(TravelHead travel, Pagination pagination, Model model) throws Exception {
		List<TravelHead> list = travelHeadService.listPage(travel, pagination);
		model.addAttribute("list", list);
		return "/travelset/list";
	}
	
	/**
	 * 游记列表查询 分页
	 * 
	 * @param travel
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value = "/list_head")
	@ResponseBody
	public Object listHead(TravelHead travel, Pagination pagination) {
		try {
			if(travel.getStatus() == null){
				travel.setStatus(1L);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			List<TravelHead> list = travelHeadService.listPage(travel, pagination);
			map.put("travel", list);
			return ResponseUtils.buildSuccessRes(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	/**
	 * 下载图片
	 * 
	 * @param userID
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save_photo")
	public Object uploadImage(@QueryParam("dateStr") String dateStr, @QueryParam("travel") TravelHead travel,
			@Context HttpServletRequest request) {
		try {
			String savePaht = FileUploadDownloadUtil.uploadPic2Path(request, "jpg", "travel", "TRAVEL");
			if (StringUtil.isNotEmpty(savePaht)) {
				// 保存对象
				TravelItem item = new TravelItem();
				item.setImgUrl(savePaht);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(dateStr);
				item.setLinkDate(date);
				item.setHeadId(travel.getId());
				item.setItemType(TravelItem.TYPE_PIC);
				travelItemService.save(item);

				travel = travelHeadService.selectTravelHeadByEntity(travel);
				if (travel != null && StringUtil.isEmpty(travel.getCoverurl())) {
					// 抬头设置封面设置封面
					travel.setCoverurl(savePaht);
					travelHeadService.update(travel);
				}
			}
			return ResponseUtils.buildSuccessRes(savePaht);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
		
	}

	/**
	 * 新增、修改游记头部
	 * 
	 * @param travel
	 * @return
	 */
	@RequestMapping(value = "/save_head")
	@ResponseBody
	public Object saveHead(TravelHead travel) {
		try {
			if (StringUtils.isEmpty(travel.getUserid())) {
				return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, "登录之后才能发表游记");
			}
			if (StringUtils.isEmpty(travel.getId())) {
				travelHeadService.save(travel);
			} else {
				travelHeadService.update(travel);
			}
			return ResponseUtils.buildSuccessRes(travel);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	/**
	 * 新增修改游记明细（图片、文字）
	 * 
	 * @param travel
	 * @return
	 */
	@RequestMapping(value = "/save_item")
	@ResponseBody
	public Object saveItem(TravelItem travel) {
		try {
			if (StringUtil.isNotEmpty(travel.getLinkDateStr())) {
				String date = travel.getLinkDateStr();
				travel.setLinkDate(DateUtils.parseDate(date, "yyyy-MM-dd"));
			}else{
				travel.setLinkDate(new Date());
			}
			if (StringUtils.isEmpty(travel.getId())) {
				if (StringUtils.isEmpty(travel.getImgUrl())) {
					travel.setItemType(TravelItem.TYPE_NOTE);
				} else {
					travel.setItemType(TravelItem.TYPE_PIC);
				}
				travelItemService.save(travel);
			} else {
				travelItemService.update(travel);
			}
			return ResponseUtils.buildSuccessRes(travel);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	/**
	 * 删除游记（抬头、明细）
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete_head")
	@ResponseBody
	public Object deleteHead(@QueryParam("id") Long id) {
		try {
			Integer i = travelHeadService.deleteTravelSetById(id);
			return ResponseUtils.buildSuccessRes(i);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 修改
	 * @param travel
	 * @return
	 */
	@RequestMapping(value = "/update_head")
	@ResponseBody
	public Object updateHead(TravelHead travel) {
		try {
			Integer i = travelHeadService.update(travel);
			return ResponseUtils.buildSuccessRes(i);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	/**
	 * 删除游记 明细
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete_item")
	@ResponseBody
	public Object deleteItem(@QueryParam("id") Long id) {
		try {
			Integer i = travelItemService.deleteById(id);
			return ResponseUtils.buildSuccessRes(i);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/get_travel")
	@ResponseBody
	public Object get(@QueryParam("id") Long id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 查询抬头
			TravelHead head = new TravelHead();
			head.setId(id);
			head = travelHeadService.selectTravelHeadByEntity(head);
			map.put("travel_head", head);
			// 查询明细
			TravelItem item = new TravelItem();
			item.setHeadId(id);
			List<TravelItem> list = travelItemService.findListBy(item);
			// 组装格式
			List<TravelSet> set = TravelSet.assembleTravelSet(list);
			map.put("travel_item", set);
			return ResponseUtils.buildSuccessRes(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
}

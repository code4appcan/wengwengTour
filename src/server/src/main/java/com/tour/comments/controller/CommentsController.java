package com.tour.comments.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tour.frame.utils.Const;
import com.tour.frame.utils.ResponseUtils;
import com.tour.frame.utils.ValidatorUtils;
import com.tour.frame.utils.page.Pagination;
import com.tour.account.entity.Landscape;
import com.tour.comments.entity.Comments;
import com.tour.comments.service.CommentsService;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.tour.comments.web.Comments
 */
@Controller
public class CommentsController {
	@Resource
	private CommentsService commentsService;
	
	@RequestMapping(value="/save_comments")
	@ResponseBody
	public Object save(Comments comments) {
		try {
			commentsService.save(comments);
			return ResponseUtils.buildSuccessRes(comments);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/update_comments")
	@ResponseBody
	public Object update(Comments comments) {
		try {
			String message = ValidatorUtils.validate(comments);
			if (StringUtils.isBlank(message)) {
				commentsService.update(comments);
			}
			return ResponseUtils.buildSuccessRes(comments);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete_comments")
	@ResponseBody
	public Object delete(@QueryParam("id") Long id) {
		try {
			Integer comments = commentsService.deleteById(id);
			return ResponseUtils.buildSuccessRes(comments);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/get_comments")
	@ResponseBody
	public Object get(Comments comments,@QueryParam("typeid") Long typeid,@QueryParam("type") Long type,@QueryParam("pageNo")int pageNo, @QueryParam("pageSize")int pageSize) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Comments> list = commentsService.findBy(comments,typeid,type,pageNo, pageSize);
			map.put("comments", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value="/get_comments_list")
	@ResponseBody
	public Object get(Comments comments, Pagination pagination) {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Comments> list = commentsService.listPage(comments, pagination);
			map.put("comments", list);
			return ResponseUtils.buildSuccessRes(map);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value="/comments/list")
	public Object get(@QueryParam("keyWords") String keyWords,Comments comments, Pagination pagination,Model model) {
		try{
			if(keyWords !="" && keyWords !=null){
				String name=new String(keyWords.getBytes("ISO-8859-1"),"UTF-8");
				comments.setTypeName(name);
				comments.setUserName(name);
				List<Comments> list = commentsService.listPage(comments, pagination);
				model.addAttribute("list", list);
				model.addAttribute("pagination", pagination);
				return "/comments/list";
			}
			else{
				List<Comments> list = commentsService.listPage(comments, pagination);
				model.addAttribute("list", list);
				model.addAttribute("pagination", pagination);
				return "/comments/list";
			}
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}	
	@RequestMapping(value="/comments/update")
	public Object update(Comments comments,@QueryParam("id") Long id, Pagination pagination,Model model) {
		try {
			comments.setId(id);
			List<Comments> comment = commentsService.listPage(comments, pagination);
			model.addAttribute("list", comment);
			return "/comments/update";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
	@RequestMapping(value="/comments/delete")
	public Object deletes(@QueryParam("id") Long id,Comments comments, Pagination pagination,Model model) {
		try {
			Integer comment = commentsService.deleteById(id);
			
			List<Comments> list = commentsService.listPage(comments, pagination);
			model.addAttribute("list", list);
			model.addAttribute("pagination", pagination);
			return "/comments/list";
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtils.buildErrorRes(Const.GENERAL_ERROR, e.getMessage());
		}
	}
	
}
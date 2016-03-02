package com.tour.frame.utils;

import java.util.Map;
import java.util.WeakHashMap;

import javax.ws.rs.core.Response;


/**
 * build the response
 * @author bear
 *
 */
public class ResponseUtils {
	
	public static Object buildSuccessRes(Object data){
		Map<String,Object> result = new WeakHashMap<String, Object>();
		result.put("status", Const.SUCCESS);
		if(data != null && !data.equals("")){
			result.put("body", data);
		}
		return result;
	}
	
	public static Object buildSuccessRes( Map<String,Object> map,Object data){
		Map<String,Object> result = new WeakHashMap<String, Object>();
		result.put("status", Const.SUCCESS);
		if(data != null && !data.equals("")){
			result.put("body", data);
		}
		if(!map.isEmpty()){
			result.putAll(map);
		}
		return result;
	}
	public static Object buildSuccessRes( Map<String,Object> map){
		Map<String,Object> result = new WeakHashMap<String, Object>();
		if(!map.isEmpty()){
			result.put("status", Const.SUCCESS);
			result.put("body",map);
		}
		return result;
	}
	public static Object buildErrorRes(String state, String error){
		Map<String,Object> result = new WeakHashMap<String, Object>();
		result.put("status", state);
		result.put("error", error);
		return result;
	}

}

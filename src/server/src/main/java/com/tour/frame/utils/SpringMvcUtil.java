package com.tour.frame.utils;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringMvcUtil {
	  //-- Content Type 定义 --//
    public static final String TEXT_TYPE = "text/plain";
    public static final String JSON_TYPE = "application/json";
    public static final String XML_TYPE = "text/xml";
    public static final String HTML_TYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";
    public static final String EXCEL_TYPE = "application/vnd.ms-excel";

    //-- Header 定义 --//
    public static final String AUTHENTICATION_HEADER = "Authorization";

    //-- 常用数值定义 --//
    public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;
    /**
     * 功能说明：设置让浏览器弹出下载对话框的Header
     * @author bear
     * @created 2014年6月16日 上午9:27:04
     * @updated 
     * @param response
     * @param fileName 下载后的文件名
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            //中文文件名支持
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
        }
    }
    
	public static String getContentPath() {
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path =request.getContextPath();
		String contentPath = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path
				+ "/";		
		return contentPath;
	}	
	/**
	 * 
	 *取得RealPath.
	 *@return  String
	 */
	@SuppressWarnings("deprecation")
	public static String getRealPath() {
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String realPath=request.getRealPath("/");
		realPath=realPath.replaceAll("\\\\", "/");
		return realPath;
	}
	/**
	 * 
	 *取得HttpSession的简化函数.
	 *@return  HttpSession
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * 
	 *取得HttpSession的简化函数.
	 *@param isNew
	 *@return  HttpSession
	 */
	public static HttpSession getSession(HttpServletRequest request,boolean isNew) {
		return request.getSession(isNew);
	}

	/**
	 * 
	 *取得HttpSession中Attribute的简化函数.
	 *@param name
	 *@return  Object
	 */
	public static Object getSessionAttribute(HttpServletRequest request,String name) {
		HttpSession session = getSession(request,false);
		return (session != null ? session.getAttribute(name) : null);
	}

	/**
	 * 
	 *取得HttpRequest的简化函数.
	 *@return  HttpServletRequest
	 */
	/**
	 * 功能说明：pojo中获取HttpServletRequest对象
	 * @author bear
	 * @created 2014年6月16日 上午9:50:47
	 * @updated 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
	}

	/**
	 * pojo中获取参数
	 *取得HttpRequest中Parameter的简化方法.
	 *@param name
	 *@return  String
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 
	 *获取服务器端地址
	 *@return  String
	 */
	public static String getBasePathWithOutAfter(HttpServletRequest request) {
    	String path = request.getContextPath();
    	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() +
    					path ;		
		return basePath;
	}		
	/**
	 * 
	 *获取服务器端地址
	 *@return  String
	 */
	public static String getBasePath(HttpServletRequest request) {
    	String path = request.getContextPath();
    	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() +
    					path + "/";		
		return basePath;
	}	
	/**
	 * 序列化对象之后向response写出json字符串（默认编码是UTF-8）
	 * @param object 需要序列的对象
	 */
	public static void responseJSONWriter(HttpServletResponse response,Object object ) {
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter writer = response.getWriter();
//			writer.write(GsonUtil.toJson(object));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 序列化对象之后向response写出json字符串（指定编码特定的编码格式）
	 * @param object 需要序列的对象
	 * @param characterEncoding 编码格式
	 * 
	 */
	public static void responseJSONWriter(HttpServletResponse response,Object object,String characterEncoding) {
		response.setCharacterEncoding(characterEncoding);
		try {
			PrintWriter writer = response.getWriter();
//			writer.write(GsonUtil.toJson(object));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 向客户端输出内容（默认编码是UTF-8）
	 * @param text 需要输出到客户端的信息
	 */
	public static void responseWriter(HttpServletResponse response,String text ) {
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * 获取客户端ip
	 * 
	 * @return
	 */
	public static String getIpAddr() {     
		  HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	      String ip = request.getHeader("x-forwarded-for");     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("Proxy-Client-IP");     
	      }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("WL-Proxy-Client-IP");     
	      }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	          ip = request.getRemoteAddr();     
	      }     
	     return ip;     
	}
	/**
	 * 获取客户端ip
	 * 
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {     
	      String ip = request.getHeader("x-forwarded-for");     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("Proxy-Client-IP");     
	      }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("WL-Proxy-Client-IP");     
	      }     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	          ip = request.getRemoteAddr();     
	      }     
	     return ip;     
	}


}

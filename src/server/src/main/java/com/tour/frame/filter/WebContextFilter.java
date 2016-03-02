package com.tour.frame.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.tour.frame.utils.Const;

/**
 * 
 * @author bear
 *
 */
public class WebContextFilter implements Filter {

    @Override
    public void destroy() {

    }

    /**
     * 在进入时将request和response注册到WebContext中，结束时清除
     * 
     * @param request 要注入的request
     * @param response 要注入的response
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                             ServletException {
        /*
         * HttpServletResponse httpResponse = (HttpServletResponse) response;
         * httpResponse.setHeader("Pragma","No-cache"); httpResponse.setHeader("Cache-Control","no-cache");
         * httpResponse.setHeader("Expires","0");
         */
        try {
//        	Enumeration<String> e = request.getParameterNames();
//        	Map<String,String> map = new HashMap<String,String>();
//        	while(e.hasMoreElements()){
//        		String ele = e.nextElement();
//        		if(!"sign".equals(ele) && !"appkey".equals(ele)){
//            		map.put(ele, (String)(String)request.getParameter(ele));        			
//        		}
//        	}
        	WebContext.registry((HttpServletRequest) request, (HttpServletResponse) response);
//        	String sSign = sign(Const.APP_KEY,Const.APP_SECRET,map);
//        	String cSign = request.getParameter("sign");
//        	System.out.println(cSign+"=="+sSign);
//        	if(cSign!=null && !sSign.equals(cSign)){
//        		try {
//        			JSONObject json=new JSONObject();  
//					json.put("status", Const.NOT_AUTHORIED);
//	        		json.put("error", "签名验证不通过");
//	        		response.setCharacterEncoding("GBK");
//	        		response.getWriter().write(json.toString());
//				} catch (JSONException e1) {
//					e1.printStackTrace();
//				}
//        	}else{
        		chain.doFilter(request, response);	
//        	}
          
        } finally {
            WebContext.release();
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    public static String sign(String appKey, String secret, Map<String, String> paramMap)
	    {
	        // 对参数名进行字典排序
	        String[] keyArray = paramMap.keySet().toArray(new String[0]);
	        Arrays.sort(keyArray);

	        // 拼接有序的参数名-值串
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(appKey);
	        for (String key : keyArray)
	        {
	            stringBuilder.append(key).append(paramMap.get(key));
	        }

	        stringBuilder.append(secret);
	        String codes = stringBuilder.toString();
	        String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes).toUpperCase();
	        return sign;
	    }

   public static String getQueryString(String appKey, String secret, Map<String, String> paramMap)
    {
        String sign = sign(appKey, secret, paramMap);

        // 添加签名
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("appkey=").append(appKey).append("&sign=").append(sign);
        for (Entry<String, String> entry : paramMap.entrySet())
        {
            stringBuilder.append('&').append(entry.getKey()).append('=').append(entry.getValue());
        }
        String queryString = stringBuilder.toString();
        return queryString;
    }
}

package com.tour.frame.shiro;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.tour.frame.utils.AES;
import com.tour.frame.utils.Const;


public class StatelessAuthcFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        
        String clientDigest = request.getParameter(Const.PARAM_DIGEST);
        
        String appKey = request.getParameter(Const.APP_KEY);
        
        String s_token = request.getParameter(Const.S_TOKEY);
        
        String timestemp = request.getParameter(Const.TIMESTEMP);
        
        String principal = AES.decrypt(s_token, timestemp, "UTF-8");
        if(principal != null){
            String[] principals = principal.split(" ");
            
            String userID = principals[0];
            
            request.setAttribute("userID", Long.getLong(userID));        	
        }

        Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
        
        params.remove(Const.PARAM_DIGEST);
        params.remove(Const.S_TOKEY);
        params.remove(Const.TIMESTEMP);


        
        StatelessToken token = new StatelessToken(appKey, params, clientDigest);

        try {
            getSubject(request, response).login(token);
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(response); 
            return false;
        }
        return true;
    }

    
    /**
     * 登录失败时默认返回401状态码
     * @param response
     * @throws IOException
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        JSONObject json=new JSONObject(); 
		try {
			json.put("status", Const.NOT_AUTHORIED);
			json.put("error", "签名验证不通过");
		} catch (JSONException e) {
			e.printStackTrace();
		}
//		response.setCharacterEncoding("UTF-8");
        httpResponse.getWriter().write(json.toString());
    }
}

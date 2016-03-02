package com.tour.frame.shiro;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tour.frame.utils.HmacSHA256Utils;


public class StatelessToken implements AuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String app_key;
    private Map<String, ?> params;
    private String clientDigest;

    public StatelessToken(String app_key,  Map<String, ?> params, String clientDigest) {
        this.app_key = app_key;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public  Map<String, ?> getParams() {
        return params;
    }

    public void setParams( Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
       return app_key;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }
    
    public static void main(String[] args){
    	  String key = "dadadswdewq2ewdwqdwadsadasd";
    	  MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    	  params.add("username", "demo");
    	  params.add("email", "demo@demo.com");
    	  params.add("password", "123456");
    	  params.add("sign", HmacSHA256Utils.digest(key, params));
    }
}

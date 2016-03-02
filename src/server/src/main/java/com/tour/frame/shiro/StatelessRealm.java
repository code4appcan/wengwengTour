package com.tour.frame.shiro;


import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.tour.account.service.AccountService;
import com.tour.frame.utils.Const;


public class StatelessRealm extends AuthorizingRealm {
	@Resource
	private AccountService accountService;
	
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        authorizationInfo.addRole("admin");
        return authorizationInfo;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        StatelessToken statelessToken = (StatelessToken) token;
        String app_key = statelessToken.getApp_key();
        String serverDigest = signture(Const.APP_KEY,Const.APP_SECRET,statelessToken.getParams());
        System.out.println(statelessToken.getClientDigest());
        System.out.println(serverDigest);        	
        
        return new SimpleAuthenticationInfo(
        		app_key,
                serverDigest,
                getName());
    }
    
    protected String signture(String appKey, String secret, Map<String, ?> paramMap)
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
}

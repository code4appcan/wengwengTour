package com.tour.frame.shiro;

import com.tour.account.entity.Account;
import com.tour.frame.filter.WebContext;

public class ShiroStatelessUtils {

	public static final String LOGIN_ATTRIVUTE_NAME = "account";

	public static void setAttribute(String key, Object value) {
		WebContext.setAttribute(key, value);
	}

	public static Object getAttribute(String key) {
		return WebContext.currentRequest().getAttribute(key);
	}

	public static boolean removeAttribute(String key) {
		WebContext.currentRequest().removeAttribute(key);
		return true;
	}

	public boolean hasLogin() {
		return getAttribute(LOGIN_ATTRIVUTE_NAME) != null ? true : false;
	}

	public static void setAsLogin(Account account) {
		setAttribute(LOGIN_ATTRIVUTE_NAME, account);
	}

	public static void setAsLogout() {
		removeAttribute(LOGIN_ATTRIVUTE_NAME);
	}

	public static Account getLoginAccount() {
		return (Account) getAttribute(LOGIN_ATTRIVUTE_NAME);
	}

}

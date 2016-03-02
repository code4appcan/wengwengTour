package com.tour.frame.utils;

/**
 * 公用常量
 * 
 * @author bear
 *
 */
public class Const {
	// 默认验证码参数名称
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	
    public static final String PARAM_DIGEST = "digest";
    
    public static final String APP_KEY = "app_key";
    
	public static final String APP_SECRET = "bear";
    
    public static final String S_TOKEY = "s_token";
    
    public static final String TIMESTEMP = "timestemp";
	// 用户类型
	public static final String USER_TYPR = "user_type";
	// 默认错误提示
	public static final String DEFAULT_ERROR = "网络错误,请稍后重试!";
	
	public static final Integer FRIEND_STATE_WAIT = 1;
	
	public static final Integer FRIEND_STATE_WAITCHECK = 2;
	
	public static final Integer FRIEND_STATE_VALID = 0;
	
	public static final Integer FRIEND_STATE_DENY = 3;
	
	public static final Integer FRIEND_STATE_DEL = 4;
	
	public static final Integer GROUP_STATE_VALID = 0;
	
	public static final Integer GROUP_STATE_WIATCHECK = 1;
	
	public static final Integer GROUP_STATE_DENY = 2;
	
	public static final Integer GROUP_STATE_DEL = 3;
	
    public static final String SUCCESS="200";
	
	public static final String GENERAL_ERROR="0";
	
	public static final String NOT_FOUND="404";
	
	public static final String BAD_PARAM="400";
	
	public static final String NOT_AUTHORIED="401";
	
	public static final String SYSTEM_ERROR="500";
	
	public static final String	SIMPLEDATEFORMAT_YYYYMMDDHHMMSS	= "yyyyMMddHHmmss";
	


	public static class AppParameters {

		public static class landscapeFlag {// 用户发布景是的默认状态：0-显示 1-不显示

			public static String	name	= "landscapeFlag";
			public static String	show	= "0";
			public static String	noshow	= "1";
		}

	}

	public static class Result {

		public static final String	STATUS					= "status";
		public static final String	MESSAGE					= "message";

		public static final String	STATUS_SUCCESS			= "0";
		public static final String	STATUS_FAIL				= "1";
		public static final String	STATUS_ILLEGAL			= "-1";

		public static final String	SUCCESS_MESSAGE			= "成功";
		public static final String	FAIL_MESSAGE			= "失败";
		public static final String	STATUS_ILLEGAL_MESSAGE	= "访问受限";
	}

	/**
	 * <pre>
	 * Desc： 经纬度换算成距离
	 * @author peter.fu
	 * @refactor peter.fu
	 * @date   May 28, 2014 9:55:22 PM
	 * @version 1.0
	 * @see  
	 * REVISIONS: 
	 * Version 	   Date 		    Author 			  Description
	 * ------------------------------------------------------------------- 
	 * 1.0 		  May 28, 2014 	   peter.fu 	         1. Created this class. 
	 * </pre>
	 */
	public static class Distance {

		public static final double	jl_jd	= 102834.74258026089786013677476285;	// 每经度单位米;
		public static final double	jl_wd	= 111712.69150641055729984301412873;	// 每纬度单位米;
	}

	public static class Group {

		public static class type {// 类型 1-结伴 2-团队 3-普通

			public static int	together	= 1;
			public static int	team		= 2;
			public static int	ordinary	= 3;
		}

		public static class status {// '状态 O生效 C失效 ',

			public static String	valid	= "O";
			public static String	invalid	= "C";
		}

		public static class isAccept {// 是否接受 0-接受 1-拒绝接受 2-等待接收

			public static String	success	= "0";
			public static String	reject	= "1";
			public static String	waiting	= "2";

		}

		public static class isNotifyNewMsg {// 是否通知新消息 Y/N

			public static String	success	= "Y";
			public static String	fail	= "N";

		}

		public static class isTooFar {// 是否太远 Y/N

			public static String	success	= "Y";
			public static String	fail	= "N";

		}

		public static class isMiss {// 是否失联 Y/N

			public static String	success	= "Y";
			public static String	fail	= "N";

		}

	}

	public static class Landscape {

		public static class type {// 来源 0-网友发现的 1-官方发布的

			public static String	app		= "0";
			public static String	office	= "1";

		}

		public static class Operation {// 操作 0-挖景 1-分享景 2-留声

			public static int	create	= 0;
			public static int	copy	= 1;
			public static int	voice	= 2;

		}

		public static class msgType {// 操作 0-景的赞 1-景的留声 2-留声的赞 3-评论景 4-回复评论

			public static int	landscapePraise			= 0;
			public static int	landscapeVoice			= 1;
			public static int	voicePraise				= 2;
			public static int	landscapeComment		= 3;
			public static int	landscapeReplyComment	= 4;

		}

		public static class flag {// 是否显示在景列表页 0-显示 1-不显示 默认0

			public static int	show	= 0;
			public static int	noshow	= 1;

		}
	}

	public static class Friend {

		public static class status {// 1-确认生效 2.-已删除 3-验证对方加好友的求情 4-等待对方通过验证

			public static String	valid			= "1";
			public static String	invalid			= "2";
			public static String	waiting			= "3";
			public static String	waitingChecked	= "4";

		}

	}

	public static class Team {

		public static class isNotify {// 是否开启掉队提醒 Y/N

			public static String	success	= "Y";
			public static String	fail	= "N";

		}

		public static class status {// '状态 O生效 C主动失效 T到期失效',

			public static String	valid		= "O";
			public static String	invalid		= "C";
			public static String	timeover	= "T";

		}

	}

	public static class Together {

		public static class type {// 取值：1（吃饭），2（泡吧），4（同游）。7（全部）。三个基本取值，可以或在一起，得出一个值。实现多种组合

			public static String	meating		= "1";
			public static String	palying		= "2";
			public static String	travelling	= "4";
			public static String	all			= "7";

		}

		public static class status {// '状态 O生效 C失效 T到期失效',

			public static String	valid		= "O";
			public static String	invalid		= "C";
			public static String	timeover	= "T";
		}

	}

	public static class User {

		public static class office {

			public static int	userId	= 1;	// 官网账户id
		}

		public static class status {// '状态 O生效 C失效 ',

			public static String	valid	= "O";
			public static String	invalid	= "C";
		}

		public static class gender {// '状态 O生效 C失效 ',

			public static String	male	= "male";
			public static String	female	= "female";
		}

		public static class isTravel {// 是否处于旅途中状态 Y/N

			public static String	success	= "Y";
			public static String	fail	= "N";

		}

		public static class userNameType {// 第三方账户类型 qq renren sina

			public static String	qq		= "qq";
			public static String	renren	= "renren";
			public static String	sina	= "sina";

		}

		public static class platform {

			public static String	ios		= "ios";
			public static String	android	= "android";
			public static String	other	= "other";
		}
	}

	public static class Push {

		public static class type {

			public static String	teamAdd						= "0";	// 新加入团队
			public static String	teamExpireNotify			= "1";	// 团队过期提前提醒
			public static String	teamMissNotify				= "2";	// 团队掉队提醒
			public static String	togetherExpire				= "3";	// 结伴国企
			public static String	recevieNewMsgWhenOffline	= "4";	// 用户不在线但接受新消息

			public static String	notValidType				= "-1"; // 无效的id，主要是用来区分不需要推送的消息类型
			public static String	landscapePraise				= "11"; // 赞景
			public static String	landscapeVoice				= "12"; // 留声
			public static String	landscapeVoicePraise		= "13"; // 赞留声
			public static String	landscapeComment			= "14"; // 评论景
			public static String	landscapeReplyComment		= "15"; // 回复景的评论
		}
	}

	public static class AsynCompute {

		public static class status {// '状态 O生效 C失效 ',

			public static String	valid	= "O";
			public static String	invalid	= "C";
		}

		public static class opType {// 操作类型 删除留声-delVoice 删除景-delLandscape

			public static String	delVoice		= "delVoice";
			public static String	delLandscape	= "delLandscape";
			public static String	delUser			= "delUser";
		}
	}

	public static class Advertisement {

		public static class status {// '状态 O生效 C主动失效

			public static String	valid	= "O";
			public static String	invalid	= "C";

		}

	}

	public static class AppUpdateInfo {

		public static class type {// 消息类型

			public static String	activity	= "activity";

		}

	}

}

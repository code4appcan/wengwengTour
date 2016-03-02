package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "";
	// 商户的私钥
	public static String private_key = "MIICXQIBAAKBgQC93bWKd2Hag8Wvbm/+nxUT5qpaRB/AhJ/s7eBaRW30/ZTPzuKdISTbZS7seUqhi7Ct05ZySiwJwszWS5SySa+imW51lX8HkqUoMXYsDY2YdIDK3985UghI9cW3/bDlqu3VVgH1/C93Oa4i9xM/Im2rrim+FCfO9bB1sN/6EIIPiQIDAQABAoGBAJkym5juWvaF/Kxg390DX25/YFUVQsmEOcZi6fPssZxMQBatSAyhAaxFKGeGNsHChPy9DVeKoZ3+oGkI8yIocp3MXIGXSk1XGK+yOjt4xgPeGhWiIhUYq3xQ59nOJf8agH4fzinfXV5QTJfCPjNZcVgQr9T1A3KpSDQTIalKWjtBAkEA400kSwx8SBKcmFL1V+jXbizZf+DK11cn+rOB7oSEb4nwaTjXej5tTNmm2r3gj42k8SQ1wzDvS31xYJ28e4uuRwJBANXWlIGeIXnyo2cNJjJEVaW/SDlh2md6upkkv9QmqUQRSCXQHvv7Hp4oLaalM7PFruQFvM9CIEft789DQ8xAK68CQGOgazjtxwd8COEG83xNlSc4RziymZWbIO3Ugi2+LaIJzrL/9AojlJqrh2O299dT2SjMmzfrmeb3m5siZ/uqtH8CQQCoWO3DecWrJw3jlGOM0WWIPqZSnqn+K436AQeQsvsxXlWph0bNZC7/nmwKNScWEbyVQbsahFm7pyPTzYXzqFV7AkB7SylZzS5Eb7jfdcsSrFJMOV3v+6O8S+7KtgFwliMQhuW/eBJobrIPGLr2v2mEaI5Tvo+LYAWbXxXk2YnTng/q";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\paylog\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}

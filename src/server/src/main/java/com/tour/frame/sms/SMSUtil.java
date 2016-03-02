package com.tour.frame.sms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import sun.misc.BASE64Encoder;

public class SMSUtil {

	@SuppressWarnings("restriction")
	public static boolean sendSMS(String phone, String smsContent) {

		try {
			if (StringUtils.isBlank(phone) || StringUtils.isBlank(smsContent)) {
				// this.logger.warn("发送短信发现参数非法. phone=" + phone + " ,
				// smsContent=" + smsContent);
				return false;
			}
			String key = "api:97c2b14406ef43b432c27018978a03c6";//Tour
//			String key = "api:6f0847e29e080e03ac32805b27a0f89f";//点联
			
			CloseableHttpClient httpclient = buildHttpClient();

			HttpPost request = new HttpPost("https://sms-api.luosimao.com/v1/send.json");
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
					.build();// 设置请求和传输超时时间
			request.setConfig(requestConfig);
			request.addHeader("Accept-Encoding", "gzip");
			request.addHeader("Authorization",
					"Basic " + new BASE64Encoder().encode(key.getBytes("utf-8")));

			ByteArrayOutputStream bos = null;
			InputStream bis = null;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("mobile", phone));
				params.add(new BasicNameValuePair("message", smsContent));// "您的验证码是：1111。【弗洛格信息科技】"
				request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

				HttpResponse response = httpclient.execute(request);
				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					// this.logger.error("发送短信http调用返回失败. phone=" + phone + " ,
					// smsContent=" + smsContent + ", errorCode=" +
					// response.getStatusLine().getStatusCode());
					return false;
				}

				bis = response.getEntity().getContent();
				bos = new ByteArrayOutputStream();
				byte[] buf = new byte[10240];
				int count;
				while ((count = bis.read(buf)) != -1) {
					bos.write(buf, 0, count);
				}

				Header[] gzip = response.getHeaders("Content-Encoding");
				if (gzip.length > 0 && gzip[0].getValue().equalsIgnoreCase("gzip")) {
					return dealResult(readGzipContent(bos));
				}
				return dealResult(bos.toString());

			} finally {
				closeInputStream(bis);
				closeOutputStream(bos);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static CloseableHttpClient buildHttpClient()
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

		SSLContextBuilder builder = SSLContexts.custom();
		builder.loadTrustMaterial(null, new TrustStrategy() {

			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		});
		SSLContext sslContext = builder.build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

			public void verify(String host, SSLSocket ssl) throws IOException {
			}

			public void verify(String host, X509Certificate cert) throws SSLException {
			}

			public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
			}

			public boolean verify(String s, SSLSession sslSession) {
				return true;
			}
		});

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();

		return httpclient;
	}

	private static String readGzipContent(ByteArrayOutputStream bos) throws IOException, UnsupportedEncodingException {

		GZIPInputStream gzin = null;
		StringBuffer sb = new StringBuffer();
		try {
			gzin = new GZIPInputStream(new ByteArrayInputStream(bos.toByteArray()));
			int size;
			byte[] buf = new byte[10240];
			while ((size = gzin.read(buf)) != -1) {
				sb.append(new String(buf, 0, size, "utf-8"));
			}
		} finally {
			closeInputStream(gzin);
		}
		return sb.toString();
	}

	/**
	 * 解析返回结果
	 * @param result
	 * @return
	 */
	private static boolean dealResult(String result) {// {"error":0,"msg":"ok"}

		try {
			JSONObject jsonObj = new JSONObject(result);
			int error_code = jsonObj.getInt("error");
			String error_msg = jsonObj.getString("msg");
			if (error_code == 0) {
				System.out.println("Send message success.");
				return true;
			} else {
				System.out.println("Send message failed,code is " + error_code + ",msg is " + error_msg);
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
   
	private static boolean dealResult(String phone, String smsContent, String result) {// {"error":0,"msg":"ok"}

		System.out.println(result);
		// ObjectMapper om = new ObjectMapper();
		// om.readv
		// Integer status = (Integer) obj.get("error");
		// if (status==0) {
		//// this.logger.debug("发送短信的成功. phone=" + phone + " , smsContent=" +
		// smsContent + ",返回值=" + result);
		// return true;
		// }
		//// this.logger.error("发送短信的失败. phone=" + phone + " , smsContent=" +
		// smsContent + ",返回值=" + result);
		return false;
	}

	private static void closeInputStream(InputStream inputStream) throws IOException {

		if (inputStream != null) {
			inputStream.close();
		}
	}

	private static void closeOutputStream(OutputStream outputStream) throws IOException {

		if (outputStream != null) {
			outputStream.close();
		}
	}

	public static void main(String[] args) {
//		sendSMS("15071054326", "尊敬的用户，您的验证码是：12345，请在10分钟内输入【正益无线】");
		sendSMS("15071054326", "尊敬的用户，您的验证码是：12345，请在10分钟内输入【秘境巴东】");
	}
}

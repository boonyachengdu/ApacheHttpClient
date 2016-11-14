package com.boonya.client;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * Apache HttpClient JSON参数请求方式
 * 
 * @package com.wlyd.fmcgwms.util.api.APIHttpClient
 * @date 2016年11月11日 下午5:46:01
 * @author pengjunlin
 * @comment
 * @update
 */
@SuppressWarnings("deprecation")
public class SimpleHTTPClient {

	// 接口地址
	private String apiURL = "";

	private HttpClient httpClient = null;

	private Log logger = LogFactory.getLog(this.getClass());

	private HttpPost method = null;

	private long startTime = 0L;

	private long endTime = 0L;

	private int status = 0;

	/**
	 * 设置忽略安全验证
	 * 
	 * @MethodName: initHttps
	 * @Description:
	 * @throws
	 */
	private void initHttps() {
		// ========================设置忽略访问SSL===================
		// 创建TrustManager
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		};

		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
		try {
			ctx.init(null, new TrustManager[] { xtm }, null);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SSLSocketFactory sf = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme sch = new Scheme("https", 443, sf);
		httpClient.getConnectionManager().getSchemeRegistry().register(sch);
	}

	/**
	 * 接口地址
	 * 
	 * @param url
	 */
	public SimpleHTTPClient(String url) {
		this.apiURL = url;
		httpClient = new DefaultHttpClient();
		method = new HttpPost(apiURL);
		method.setHeader("Accept", "application/json");
		method.setHeader("Accpet-Encoding", "gzip");
		method.setHeader("Content-Encoding", "UTF-8");
		method.setHeader("Content-Type", "application/json; charset=UTF-8");
		method.setHeader("Sequence", UUID.randomUUID().toString());
		// 设置忽略访问SSL
		initHttps();
	}

	/**
	 * 接口地址
	 * 
	 * @param url
	 * @param uuid
	 */
	public SimpleHTTPClient(String url, String uuid) {
		apiURL = url;
		httpClient = new DefaultHttpClient();
		method = new HttpPost(apiURL);
		method.setHeader("Accept", "application/json");
		method.setHeader("Accpet-Encoding", "gzip");
		method.setHeader("Content-Encoding", "UTF-8");
		method.setHeader("Content-Type", "application/json; charset=UTF-8");
		method.setHeader("Sequence", uuid);
		// 设置忽略访问SSL
		initHttps();
	}

	/**
	 * 调用 API
	 * 
	 * @param parameters
	 * @return
	 */
	public String post(String parameters) {
		String body = null;
		logger.info("parameters:" + parameters);

		if (method != null & parameters != null
				&& !"".equals(parameters.trim())) {
			JSONObject jsonObject = JSONObject.parseObject(parameters);
			logger.info("json:" + jsonObject.toString());
			try {

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// 建立一个NameValuePair数组，用于存储欲传送的参数
				params.add(new BasicNameValuePair("data", parameters));

				StringEntity entity = new StringEntity(parameters, "UTF-8");
				// 添加参数
				method.setEntity(entity/*
										 * new UrlEncodedFormEntity(params,
										 * "UTF-8")
										 */);

				startTime = System.currentTimeMillis();

				// 设置编码
				HttpResponse response = httpClient.execute(method);
				endTime = System.currentTimeMillis();
				int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					status = 1;
				}

				// Read the response body
				body = EntityUtils.toString(response.getEntity());

			} catch (IOException e) {
				// 发生网络异常
				logger.error("exception occurred!\n" + e.getMessage());
				// 网络错误
				status = 3;
			} finally {
				logger.info("调用接口状态：" + status);
			}

		}
		return body;
	}

	/**
	 * 0.成功 1.执行方法失败 2.协议错误 3.网络错误
	 * 
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}

}

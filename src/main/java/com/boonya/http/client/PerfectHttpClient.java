package com.boonya.http.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@ThreadSafe
@SuppressWarnings("deprecation")
public class PerfectHttpClient{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	private  CloseableHttpClient httpClient = null;// 普通HTTP访问
	
	private  CloseableHttpClient httpsClient = null;// 基于SSL的HTTPS访问
	
	/**
	 * 设置忽略安全验证
	 * 
	 * @MethodName: initHttps
	 * @Description:
	 * @throws
	 */
	private void initHttps() {
		// ==设置忽略访问SSL==
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
			e.printStackTrace();
		}

		// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
		try {
			ctx.init(null, new TrustManager[] { xtm }, null);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		SSLSocketFactory sf = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme sch = new Scheme("https", 443, sf);
		httpsClient.getConnectionManager().getSchemeRegistry().register(sch);
	}
	
	
	public String post(String url,String requestBody){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String post(String url,String requestBody,String charset){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody,charset);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String post(String url,String requestBody,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody,charset);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String post(String url,String requestBody,ContentType contentType){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody, contentType);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	@Deprecated
	public String post(String url,String requestBody,String mimeType,String charset){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody, mimeType, charset);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String post(String url,List<NameValuePair> parameters){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String post(String url,List<NameValuePair> parameters,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String post(String url,Iterable<NameValuePair> parameters){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String post(String url,Iterable<NameValuePair> parameters,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			httpClient=HttpClients.createDefault(); 
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String postSSL(String url,String requestBody){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String postSSL(String url,String requestBody,String charset){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody,charset);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String postSSL(String url,String requestBody,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody,charset);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String postSSL(String url,String requestBody,ContentType contentType){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody, contentType);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	@Deprecated
	public String postSSL(String url,String requestBody,String mimeType,String charset){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			HttpEntity entity=new StringEntity(requestBody, mimeType, charset);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String postSSL(String url,List<NameValuePair> parameters){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String postSSL(String url,List<NameValuePair> parameters,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String postSSL(String url,Iterable<NameValuePair> parameters){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}
	
	public String postSSL(String url,Iterable<NameValuePair> parameters,Charset charset){
		HttpPost post=new HttpPost(url);
		try {
			httpsClient=HttpClients.createDefault(); 
			UrlEncodedFormEntity entity=new UrlEncodedFormEntity(parameters, charset);
			post.setEntity(entity);
			initHttps();// 设置HTTPS过滤
			long startTime = System.currentTimeMillis();
            CloseableHttpResponse response = httpsClient.execute(post); 
            long endTime = System.currentTimeMillis();
            try {  
                HttpEntity responseEntity = response.getEntity();  
                int statusCode = response.getStatusLine().getStatusCode();
				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed:" + response.getStatusLine());
					return  EntityUtils.toString(responseEntity, "UTF-8");
				}
                return null;
            } finally {  
                response.close();  
            }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch ( ClientProtocolException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			 // 关闭连接,释放资源    
            try {  
            	httpsClient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return null;
	}

}

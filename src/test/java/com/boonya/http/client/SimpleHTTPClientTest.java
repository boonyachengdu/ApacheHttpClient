package com.boonya.http.client;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.boonya.http.client.SimpleHTTPClient;

public class SimpleHTTPClientTest {
	
	
	@Test
	public void testPost(){
		String url="https://www.baidu.com/s?";
		JSONObject json=new JSONObject();
		json.put("keyword", "BBC");
		json.put("wd", "BBC");
		System.out.println(SimpleHTTPClient.getInstance().post(url, json.toJSONString())); 
	}

}

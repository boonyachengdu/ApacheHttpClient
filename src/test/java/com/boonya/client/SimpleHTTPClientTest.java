package com.boonya.client;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

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

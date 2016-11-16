package com.boonya.http.client;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.boonya.http.client.HTTPSUtil;

public class HTTPSUtilTest {
	
	
	@Test
	public void testPost(){
		String apiUrl="https://www.baidu.com/s?";
		JSONObject json=new JSONObject();
		json.put("keyword", "BBC");
		json.put("wd", "BBC");
		System.out.println(new HTTPSUtil().doPostSSL(apiUrl, json));
	}

}

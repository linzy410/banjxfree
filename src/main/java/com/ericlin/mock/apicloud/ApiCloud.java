package com.ericlin.mock.apicloud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.qnvip.util.HttpUtil;

public class ApiCloud {
	
	public void sign() throws IOException {
		//http://community.apicloud.com/bbs/plugin.php?id=gsignin:index&action=signin&formhash=0f2552c0
//		String url = URLEncoder.encode("id=gsignin:index&action=signin&formhash=0f2552c0", "UTF-8");
//		Document doc = Jsoup.connect("http://community.apicloud.com/bbs/plugin.php?id=gsignin:index")
//				.cookie("connect.sid", "s:64bUwSPUrqHx43czzaVKcWjC.HQseaXZL+0QCuuO9pqjV9z4550eKmoxL11PTPJGoWIc")
//				.cookie("username", "linzheyan@qnvip.com")
//				.get();
//		System.out.println(doc.html());
//		System.out.println(doc.select("a").attr("onclick"));
//		String response = HttpUtil.doGet("http://community.apicloud.com/bbs/plugin.php", "id=gsignin:index&action=signin&formhash=0f2552c0");
//		String response = HttpUtil.doGet("http://community.apicloud.com/bbs/plugin.php?id=gsignin:index", null);
//		System.out.println(response);
		
		
		Document doc = Jsoup.parse(doGet("http://community.apicloud.com/bbs/plugin.php?id=gsignin:index"), "http://community.apicloud.com");
		String signUrl = doc.select(".right").attr("abs:href");
		System.out.println(signUrl);
		Document doc1 = Jsoup.connect(signUrl).get();
		System.out.println(doc1.html());
		
	}
	
	public String doGet(String url) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
		HttpMethod method = new GetMethod(url);
		method.setRequestHeader("Cookie", "connect.sid=s:64bUwSPUrqHx43czzaVKcWjC.HQseaXZL+0QCuuO9pqjV9z4550eKmoxL11PTPJGoWIc");
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

	public static void main(String[] args) throws IOException {
		new ApiCloud().sign();
	}
}

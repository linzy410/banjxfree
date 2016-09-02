package com.ericlin.mock.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

import com.alibaba.fastjson.JSONObject;

public class WxPush {

	private static String templateId= "u8_V7H6adbm97s6TX4isoiQv64S3MIC5vmOCcYqtLEo"; 
	
	private static String WEI_XIN_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	private static String wxTemplateColor = "#173177";
	
	public static void send(String url) {
		WxTemplate t = excuteTemplate(url);
		doPost(JSONObject.toJSON(t).toString());
	}
	
	private static WxTemplate excuteTemplate(String jianlouUrl) {
		WxTemplate t = new WxTemplate();
		t.setTouser("oUJE0t8OdfhBVgMFNuFA7FJdH058");
		t.setTemplate_id(templateId);
		t.setUrl(jianlouUrl);
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();
		TemplateData first = new TemplateData("捡漏", wxTemplateColor);
		m.put("first", first);
		TemplateData orderMoneySum = new TemplateData(0 + "元", wxTemplateColor);
		m.put("orderMoneySum", orderMoneySum);
		TemplateData orderProductName = new TemplateData("捡漏", wxTemplateColor);
		m.put("orderProductName", orderProductName);
		String remarkMess = "";
		TemplateData remark = new TemplateData(remarkMess, wxTemplateColor);
		m.put("remark", remark);
		t.setData(m);
		return t;
	}

	private static String getToken() {
		String resp_token = com.qnvip.util.HttpUtil.doGet("http://tuan.qnvip.com/qnvip_access_token.php", null);
		JSONObject jb = JSONObject.parseObject(resp_token);
		return jb.get("access_token").toString();
	}

	private static String doPost(String json) {
		String url = WEI_XIN_TEMPLATE_URL + getToken();
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
		Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		PostMethod post = new PostMethod(url);

		post.setRequestBody(json);
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		try {
			client.executeMethod(post);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(post.getResponseBodyAsStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			if (post.getStatusCode() != HttpStatus.SC_OK) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return response.toString();
	}
}

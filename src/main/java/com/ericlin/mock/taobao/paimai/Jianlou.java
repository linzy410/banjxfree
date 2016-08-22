package com.ericlin.mock.taobao.paimai;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.qnvip.util.FileUtil;

public class Jianlou {
	
	private static String[] exclude = "车,酒,房,商标,域名,电视剧,织绣,箱包".split(",");
	private static int hignPrice = 101;
	

	public static void main(String[] args) throws IOException {
		String url = "https://paimai.taobao.com/json/home.htm";
		String response = doGet(url, null);
		Gson gson = new Gson();
		RespList respList = gson.fromJson(response, RespList.class);
		String path = "f:/", filename = System.currentTimeMillis() + ".html";
		File file = new File(path + filename);
		if (file.exists()) {
			file.delete();
		}
		String head = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"><link rel=\"stylesheet\" href=\"http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css\">"
				+ "<title>捡漏专场</title><style>.caption>p{margin:0;}b{color:#8E0019;}.album{width:290px;float: left;height: 448px;padding: 0 2px;}.focus{border: 4px solid #8e0019;}</style>"
				+ "</head><body><div class=\"container\" style=\"width: 100%;\"><div class=\"row\">";
		FileUtil.write(path, filename, head, true);
		for(AlbumWrap wrap : respList.getList()) {
			for (Album album : wrap.getAlbums()) {
				if (album.getStartTime() > System.currentTimeMillis()) {
					continue;
				}
				boolean ignore = false;
				for (String excludeStr : exclude) {
					if (album.getTitle().indexOf(excludeStr) >= 0) {
						ignore = true;
						break;
					}
				}
				if (ignore) {
					continue;
				}
				String albumUrl = "https:" + album.getAlbumUrl().trim();
				System.out.println(albumUrl);
				Document doc = Jsoup.connect(albumUrl).get();
				for (Element element : doc.select(".pmp-item-list>li.item")) {
					try {
						int currentPrice = Integer.parseInt(element.select(".xmpp-current-price").text());
						int bidNum = Integer.parseInt(element.select(".xmpp-bid-num").get(0).text());
						String focus = StringUtils.EMPTY;
						if (currentPrice > hignPrice) {
							continue;
						}
						if (currentPrice == 1 && bidNum == 0) {
							focus = " focus";
						}
						StringBuffer str = new StringBuffer();
						str.append("<div class=\"album\"><div class=\"thumbnail").append(focus).append("\">")
						.append("<a href=\"https:").append(element.select("p.title>a").attr("href")).append("\" target=\"_blank\"><img src=\"")
						.append(element.select("img").attr("data-ks-lazyload")).append("\"></a><div class=\"caption\"><p><a href=\"https:")
						.append(element.select("p.title>a").attr("href")).append("\" target=\"_blank\">").append(element.select("p.title").text())
						.append("</a></p><p>当前价：<b>￥").append(currentPrice)
						.append("</b></p><p>起拍价：<b>￥").append(element.select(".soon-price>.price-num>b").text())
						.append("</b></p><p>").append(element.select(".soon-time").text()).append("</p><p>").append(element.select(".over-time").text())
						.append("</p><p>出价次数: <b>").append(bidNum).append("</b></p></div></div></div>");
						FileUtil.write(path, filename, str.toString(), true);
					} catch (Exception e) {
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
		String end = "</div></div></body></html>";
		FileUtil.write(path, filename, end, true);
	}
	
	public static String doGet(String url, String queryString) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
		if (StringUtils.isNotEmpty(queryString)) {
			if (url.indexOf("?") > 0) {
				url += "&" + queryString;
			} else {
				url += "?" + queryString;
			}
		}
		HttpMethod method = new GetMethod(url);
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "gbk"));
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
}
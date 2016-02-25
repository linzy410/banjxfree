package com.ericlin.banjx;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BanjxFree {

	private static String[] emailArr = {"linzy410@126.com", "123561326@qq.com", "linzheyan@qnvip.com", "1669595239@qq.com"};
	
	private static String logPath = null;

	public static void main(String[] args) throws IOException {
		if (args == null || args.length == 0) {
			logPath = BanjxFree.class.getClassLoader().getResource("logs").getPath();
		} else {
			logPath = args[0];
		}
		goFree();
	}

	public static void goFree() throws IOException {
		wirteLog("-------" + (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new java.util.Date()) + "-------");
		for (String email : emailArr) {
			Connection login = Jsoup.connect("http://banjx.com/_login.php")
					.data("email", email)
					.data("passwd", "123456")
					.header("Accept", "application/json, text/javascript, */*; q=0.01")
					.header("Origin", "http://banjx.com")
					.header("X-Requested-With", "XMLHttpRequest")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.82 Safari/537.36")
					.header("Referer", "http://banjx.com/login.php")
					.header("Accept-Encoding", "gzip, deflate")
					.header("Accept-Language", "zh-CN,zh;q=0.8")
					.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			Response resp = login.method(Method.POST).execute();
			Document doc = resp.parse();
			wirteLog(email + ": " + ascii2native(doc.body().html().replaceAll("&quot;", "\"")));
			String user_email = resp.cookie("user_email");
			String user_pwd = resp.cookie("user_pwd");
			String uid = resp.cookie("uid");

			Document free = Jsoup.connect("http://banjx.com/_checkin.php")
					.header("Accept", "application/json, text/javascript, */*; q=0.01")
					.header("X-Requested-With", "XMLHttpRequest")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.82 Safari/537.36")
					.header("Referer", "http://banjx.com/user.php")
					.header("Accept-Encoding", "gzip, deflate, sdch")
					.header("Accept-Language", "zh-CN,zh;q=0.8")
					.cookie("user_email", user_email)
					.cookie("user_pwd", user_pwd).cookie("uid", uid).get();
			wirteLog(ascii2native(free.body().html().replaceAll("&quot;", "\"")));

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		wirteLog("--------------------------------");
	}

	public static String ascii2native(String utfString) {
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;
		while ((i = utfString.indexOf("\\u", pos)) != -1) {
			sb.append(utfString.substring(pos, i));
			if (i + 5 < utfString.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
			} else if (i < utfString.length()) {
				sb.append(utfString.substring(i, utfString.length()));
			}
		}
		return sb.toString();
	}

	public static void wirteLog(String content) {
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		File logFolder = new File(logPath + "/" + month);
		if (!logFolder.exists()) {
			logFolder.mkdirs();
		}
		String fileName = logFolder.getAbsolutePath() + "/log.txt";
		System.out.println(fileName);
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.write((content + "\r\n").getBytes());
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

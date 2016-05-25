package com.ericlin.mock.apicloud;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ApiCloud {
	
	public void sign() throws IOException {
		Document doc = Jsoup.connect("http://www.apicloud.com/console")
				.cookie("connect.sid", "s:64bUwSPUrqHx43czzaVKcWjC.HQseaXZL+0QCuuO9pqjV9z4550eKmoxL11PTPJGoWIc")
				.cookie("username", "linzheyan@qnvip.com")
				.get();
		System.out.println(doc.html());
	}

	public static void main(String[] args) throws IOException {
		new ApiCloud().sign();
	}
}

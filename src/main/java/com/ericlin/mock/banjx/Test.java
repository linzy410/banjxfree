package com.ericlin.mock.banjx;

import com.qnvip.util.HttpUtil;

public class Test {

	public static void main(String[] args) {
		String url = "http://120.55.66.227/Account/DoLogin";
		String params = "username=bocai&password=qinwin&machineCode=";
		String resp = HttpUtil.doPost(url, params);
		System.out.println(resp);
	}
}

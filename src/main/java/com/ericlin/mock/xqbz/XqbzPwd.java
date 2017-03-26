package com.ericlin.mock.xqbz;

import java.io.IOException;

import com.qnvip.util.DateFormatUtil;
import com.qnvip.util.FileUtil;
import com.qnvip.util.StringUtil;

public class XqbzPwd extends Thread {
	
	private String[] first = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	private int threadNo;

	@Override
	public void run() {
		int i = 0;
		System.out.println(threadNo + ": " + DateFormatUtil.getCurrentTime());
		while (true) {
			String pwd = first[threadNo] + StringUtil.zeroFill(i, 5);
			try {
				FileUtil.write("e:/pwd/6", threadNo +  ".txt", pwd + "\r\n", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (i > 99999) {
				break;
			}
			i++;
		}
		System.out.println(threadNo + ": " + DateFormatUtil.getCurrentTime());
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			XqbzPwd x = new XqbzPwd();
			x.setThreadNo(i);
			x.start();
		}
	}

	public int getThreadNo() {
		return threadNo;
	}

	public void setThreadNo(int threadNo) {
		this.threadNo = threadNo;
	}

	
}

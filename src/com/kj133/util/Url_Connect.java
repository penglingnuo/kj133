package com.kj133.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Url_Connect {
	/**
	 * �����Ƿ�����
	 * @param ip
	 * @return
	 */	 
	public  String ipConnect(String ip){
//				 String ip = "192.168.1.107";
//					ip = "google.com";
	        String ipReturn ="";
			try {
			final Process p = Runtime.getRuntime().exec("cmd /c ping -n 1 " + ip);  //�˴�1���������Ӿ�ȷ�ȣ���Ӱ������ٶ�
			final BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String temp = null;
			final StringBuffer strBuffer = new StringBuffer();
			while ((temp = (in.readLine())) != null)
			strBuffer.append(temp);
//					System.out.println(strBuffer);
			if (strBuffer.toString().matches(".*\\(\\d?\\d% loss\\).*")) {
				ipReturn="aa";
			} 
			else {
				ipReturn="bb";
			}
			} catch (final IOException e) {
			e.printStackTrace();
			}
			return ipReturn;
	}


}

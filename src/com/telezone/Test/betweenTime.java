package com.telezone.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class betweenTime{
	public static void main(String[]args){
		String starttime="2010-8-177:04:04";
		
		System.out.println(getInterval(starttime));
	}
	public static String getInterval(String beginStr){
		String returnStr = "";
		int hour=0,minute=0,second=0;
		
		try{
			Date currentTime=new Date();
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			String endStr = formatter.format(currentTime);
			
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			Date beginDate=df.parse(beginStr);
			Date endDate=df.parse(endStr);
			long millisecond=endDate.getTime()-beginDate.getTime();//��������õ����ڲ�X(��λ:����)
			
			hour=Math.abs((int)(millisecond/(1000*60*60)));
			minute=Math.abs((int)(millisecond/(1000*60)))-60*hour;
			second=Math.abs((int)(millisecond/1000))-60*minute-60*60*hour;
			
			returnStr=hour+"Сʱ"+minute+"����"+second+"��";
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnStr;
	}
}

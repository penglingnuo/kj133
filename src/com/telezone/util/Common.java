package com.telezone.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Common {
	
	// 井下区域人员
	public List createListWithInput(Integer number) {
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < number + 2; i++) {
			list.add(0);
		}

		return list;
	}
	
	//获得时间点到当前时间的时间间隔，可重构
	public String betweenTime(String startTime) {
		String returnStr = "";
		int hour=0,minute=0,second=0;
		
		try{
			Date currentTime=new Date();
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			String endStr = formatter.format(currentTime);
			
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			Date beginDate=df.parse(startTime);
			Date endDate=df.parse(endStr);
			long millisecond=endDate.getTime()-beginDate.getTime();//日期相减得到日期差X(单位:毫秒)
			
			hour=Math.abs((int)(millisecond/(1000*60*60)));
			minute=Math.abs((int)(millisecond/(1000*60)))-60*hour;
			second=Math.abs((int)(millisecond/1000))-60*minute-60*60*hour;
			
			returnStr=hour+"小时"+minute+"分钟"+second+"秒";
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnStr;
	}
	
	//获得时间点到当前时间的时间间隔，可重构
	public String betweenTime(String startTime, String endTime) {
		String returnStr = "";
		int hour=0,minute=0,second=0;
		
		try{
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			Date _endDate=formatter.parse(endTime);
			String endStr = formatter.format(_endDate);
			
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			Date beginDate=df.parse(startTime);
			Date endDate=df.parse(endStr);
			long millisecond=endDate.getTime()-beginDate.getTime();//日期相减得到日期差X(单位:毫秒)
			
			hour=Math.abs((int)(millisecond/(1000*60*60)));
			minute=Math.abs((int)(millisecond/(1000*60)))-60*hour;
			second=Math.abs((int)(millisecond/1000))-60*minute-60*60*hour;
			
			returnStr=hour+"小时"+minute+"分钟"+second+"秒";
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnStr;
	}
}

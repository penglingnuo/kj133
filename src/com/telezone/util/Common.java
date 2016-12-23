package com.telezone.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Common {
	
	// ����������Ա
	public List createListWithInput(Integer number) {
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < number + 2; i++) {
			list.add(0);
		}

		return list;
	}
	
	//���ʱ��㵽��ǰʱ���ʱ���������ع�
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
	
	//���ʱ��㵽��ǰʱ���ʱ���������ع�
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

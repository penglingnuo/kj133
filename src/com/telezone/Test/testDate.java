package com.telezone.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

public class testDate extends TestCase{
	public void testDate1() {
		String date = "2010-08-16 10:35:58.100";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date da = sdf.parse(date);
			
			System.out.println(sdf.format(da));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

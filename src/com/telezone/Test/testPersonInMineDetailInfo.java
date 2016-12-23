package com.telezone.Test;

import java.util.List;
import java.util.Map;

import com.telezone.services.IPersonInMine;
import com.telezone.serviesImpl.PersonInMineImpl;

public class testPersonInMineDetailInfo {
	public static void main(String[] args) {
		IPersonInMine ird = new PersonInMineImpl();
		
		Map map = ird.getStafferInCardReader("12", "1", "1","sys");
		
		System.out.println("123");
	}
}

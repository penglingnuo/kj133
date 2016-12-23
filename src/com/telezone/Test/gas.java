package com.telezone.Test;

import com.telezone.services.IGas;
import com.telezone.serviesImpl.GasImpl;

import junit.framework.TestCase;

public class gas extends TestCase{
	public void testGasAlarmInfo() {
		IGas gas = new GasImpl();
		
		gas.getAlarmInfoOfGas();
	}
}

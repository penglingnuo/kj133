package com.telezone.Test;

import java.util.Map;
import junit.framework.TestCase;
import com.telezone.services.IIndexRightData;
import com.telezone.serviesImpl.IndexRightDataImpl;

public class testIndexRightAlarm extends TestCase{
	public void testIndexRightAlarm() {
		IIndexRightData ird = new IndexRightDataImpl();

		Map map = ird.alarmInfoMap();
	}
}

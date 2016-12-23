package com.telezone.Test;

import com.telezone.IbatisBasicClass.OperateClass;
import com.telezone.domain.classes.EscapeLine;
import com.telezone.services.IEscapeLine;
import com.telezone.serviesImpl.EscapeLineImpl;

import junit.framework.TestCase;

public class testEscapeLine extends TestCase{
	public void testIn() {
		EscapeLine el = new EscapeLine();
		el.setPathname("2234");
		el.setPathinfo("2");
		el.setPoint("1162.2585220285,928.21536614941,710.37147597266,865.26592689785,822.78118892187,393.14513251113,1477.0057182863,271.74264252598,1173.4994933234,123.36182143301,883.48243391445,143.59556976387,1153.2657449926,640.44650099941");
		el.setMapid("2");
		
		OperateClass oc = new OperateClass();
		
		oc.insert("EscapeLine.insertEscapeLine", el);
	}
}

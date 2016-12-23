package com.telezone.Test;

import com.telezone.services.ISetCoordinate;
import com.telezone.serviesImpl.SetCoordinateImpl;

import junit.framework.TestCase;

public class setxy extends TestCase{
	public void testSetXY() {
		ISetCoordinate isc = new SetCoordinateImpl();
		
		isc.saveXYOfCardreaderOrLocator("1", "1", "78739", "-8375", "5");
	}
}

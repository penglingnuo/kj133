package com.telezone.Test;

import com.telezone.services.IMapListChange;
import com.telezone.serviesImpl.MapListChangeImpl;

public class testGetCardReaderAndLocatorData {
	public static void main(String[] args) {
		IMapListChange imlc = new MapListChangeImpl();
		imlc.getCardReaderAndLocatorData("1","sys");
	}
}

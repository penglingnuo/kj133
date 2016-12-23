package com.telezone.Test;

import com.telezone.services.IEscapeLine;
import com.telezone.serviesImpl.EscapeLineImpl;


public class t {
	public static void main(String[] args) {
		IEscapeLine iel = new EscapeLineImpl();
		iel.getLineByMapid("2");
	}
}

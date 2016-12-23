package com.telezone.Test;

import com.kj133.util.encrypt;

public class testJiaMi {
	public static void main(String[] args) {
		encrypt e = new encrypt();
		
		String jiami = e.Encrypt("321", "sys");
		
		System.out.println(jiami);
	}
}

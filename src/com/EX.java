package com;

import com.kj133.util.encrypt;

public class EX {
	public static void main(String[] args) {
		encrypt e = new encrypt();
		String a = e.Dencrypt("系统管理员", "31E056");
		
		System.out.println(a);
	}
}

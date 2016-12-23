package com.telezone.Test;

import com.kj133.entity.Moremaps;
import com.kj133.entity.bo.MoremapsBO;
import java.util.List;

public class moremapstest {
	public static void main(String[] args) {
		MoremapsBO bo = new MoremapsBO();
		Moremaps map = new Moremaps();
		List list = null;
		//增加
//		map.setMapid("6");
//		map.setMapname("aaaaaaa");
//		map.setMapjx("b");
//		map.setLinesx("12.212121,1212,654.12,515");
//		map.setMapmaxjxd("12.121212");
//		bo.addmapinfo(map);
		//修改
//		String mapname="bbbb";
//		String linesx="5555.11,94.15,154.1,542";
//		String mapmaxjxd="14454.121";
//		String mapid="6";
//		try {
//			bo.updatemapinfo(mapname, null, linesx, mapmaxjxd, mapid);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//查询单个
//		map = bo.getmapbyid("6");
//		System.out.println(map.getMapname());
//		System.out.println(map.getMapjx());
		//查所有
//		try {
//			list  = (List) bo.getallmaps();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(list.size());
		//删除
//		boolean b = bo.delmapinfo("5");
//		System.out.println(b);
	}
}

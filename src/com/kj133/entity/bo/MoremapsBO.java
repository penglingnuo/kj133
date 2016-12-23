package com.kj133.entity.bo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Moremaps;

public class MoremapsBO {

	private Logger log = Logger.getLogger(this.getClass());

	public MoremapsBO() {
	}

	/**
	 * 得到所有的地图列表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getallmaps() throws Exception {
		Engine engine = null;
		StringBuffer sb = new StringBuffer();
		List param = new ArrayList();
		List allmaps = null;
		sb
				.append(" select recordid,mapid,mapname,mapjx,linesx,mapmaxjxd from moremaps order by mapid ");
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			allmaps = query.getResults(sb.toString(), param.toArray(),
					Moremaps.class);
		} catch (Exception e) {
			e.printStackTrace();
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return allmaps;
	}

	/**
	 * 修改地图信息
	 * 
	 * @param mapid
	 * @param mapname
	 * @param mapjx
	 * @param linesx
	 * @param mapmaxjxd
	 * @return
	 * @throws Exception
	 */
	public boolean updatemapinfo(String mapname, String mapjx, String linesx,
			String mapmaxjxd, String mapid) throws Exception {
		boolean result = false;
		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" update moremaps set ");
		if (mapname != null && !mapname.equals("")) {
			sb.append(" mapname=?, ");
			param.add(mapname);
		}
		if (mapjx != null && !mapjx.equals("")) {
			sb.append(" mapjx=?, ");
			param.add(mapjx);
		}
		if (linesx != null && !linesx.equals("")) {
			sb.append(" linesx=?, ");
			param.add(linesx);
		}
		if (mapmaxjxd != null && !mapmaxjxd.equals("")) {
			sb.append(" mapmaxjxd=? ");
			param.add(mapmaxjxd);
		}
		if (mapid != null && !mapid.equals("")) {
			sb.append(" where mapid=? ");
			param.add(mapid);
		}
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
			result = true;
		} catch (Exception e) {
			result = false;
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * 增加地图信息
	 * 
	 * @param moremaps
	 * @return
	 */
	public boolean addmapinfo(Moremaps moremaps) {
		boolean result = false;
		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into MoreMaps values(?,?,?,?,?) ");
		param.add(moremaps.getMapid());
		param.add(moremaps.getMapname());
		param.add(moremaps.getMapjx());
		param.add(moremaps.getLinesx());
		param.add(moremaps.getMapmaxjxd());
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
			result = true;
		} catch (Exception e) {
			result = false;
			engine.rollback();
			log.error(e);
			e.printStackTrace();
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * 根据mapid查询地图信息
	 * 
	 * @param mapid
	 * @return
	 */
	public Moremaps getmapbyid(String mapid) {
		Moremaps map = null;
		Engine engine = null;
		List param = new ArrayList();
		List maps = null;
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select recordid,mapid,mapname,mapjx,linesx,mapmaxjxd from moremaps where mapid=? ");
		param.add(mapid);
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			maps = query.getResults(sb.toString(), param.toArray(),
					Moremaps.class);
			if (maps != null) {
				map = (Moremaps) maps.get(0);
			}
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 删除地图
	 * 
	 * @param mapid
	 * @return
	 */
	public boolean delmapinfo(String mapid) {
		boolean result = false;
		Engine engine = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from moremaps where mapid=? ");
		param.add(mapid);
		try {
			engine = EngineFactory.getEngine("test");
			engine.executeSpecialSQL(sb.toString(), param.toArray());
			engine.commit();
			result = true;
		} catch (Exception e) {
			result = false;
			engine.rollback();
			log.error(e);
			e.printStackTrace();
		} finally {
			engine.closeEngine();
		}
		return result;
	}

	/**
	 * 读取文件 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public String readFileByChars(String fileName) {
		File file = new File(fileName);
		char text;
		String t = "";
		Reader reader = null;
		try {
			// System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if (((char) tempchar) != '\r') {
					text = (char) tempchar;
					t += text;
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(t);
		return t;
	}

	/**
	 * 修改文本内容
	 * 
	 * @return
	 */
	public boolean updatetext(String path, String linesx,String maxjxd) {
		boolean result = false;

		try {
			BufferedWriter File_bak = new BufferedWriter(new FileWriter(
					new File(path)));

			String str = "";
			str += "var _450 = new OpenLayers.Bounds(\n";
			str += linesx;
			str += "\n);\n";
			str += "var options_450 = {\n";
			str += "controls: [],\n";
			str += "maxExtent: _450, //layer能显示出的最大范围\n";
			str += "maxResolution: "+maxjxd+", //layer能够显示的最大解析度\n";
			str += "projection: 'EPSG:4326',\n";
			str += "numZoomLevels: 9 //缩放级别的总数\n";
			str += "};";
			File_bak.write(str);
			// 必须先刷新,才能用close关闭
			File_bak.flush();
			File_bak.close();
			System.out.println("file write success");
			result = true;
		} catch (IOException ex) {
			result = false;
			ex.printStackTrace();
			System.out.println("file read or write error");
		}
		return result;
	}

//	 /**
//	 * 直接打开本地文件
//	 * @param dizhi
//	 */
//	 public void openjsfile(String address){
//	 String cmd = "cmd /c start "+address;
//	 //String cmd = "cmd /c start E:\\mapconfig.js";
//	 try {
//	 Runtime.getRuntime().exec(cmd);
//	 } catch (IOException e) {
//	 e.printStackTrace();
//	 }
//	 }

}

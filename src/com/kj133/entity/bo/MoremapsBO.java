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
	 * �õ����еĵ�ͼ�б���Ϣ
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
	 * �޸ĵ�ͼ��Ϣ
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
	 * ���ӵ�ͼ��Ϣ
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
	 * ����mapid��ѯ��ͼ��Ϣ
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
	 * ɾ����ͼ
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
	 * ��ȡ�ļ� ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
	 */
	public String readFileByChars(String fileName) {
		File file = new File(fileName);
		char text;
		String t = "";
		Reader reader = null;
		try {
			// System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");
			// һ�ζ�һ���ַ�
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�
				// ������������ַ��ֿ���ʾʱ���ỻ�����С�
				// ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�
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
	 * �޸��ı�����
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
			str += "maxExtent: _450, //layer����ʾ�������Χ\n";
			str += "maxResolution: "+maxjxd+", //layer�ܹ���ʾ����������\n";
			str += "projection: 'EPSG:4326',\n";
			str += "numZoomLevels: 9 //���ż��������\n";
			str += "};";
			File_bak.write(str);
			// ������ˢ��,������close�ر�
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
//	 * ֱ�Ӵ򿪱����ļ�
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

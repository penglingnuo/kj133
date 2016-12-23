package com.kj133.entity.bo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;
import org.speedframework.Speed;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.MapAdd;
import com.kj133.entity.MapList;
import com.kj133.entity.Search_MapLeft;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class MapListBO {
	
   private final Logger log=Logger.getLogger(this.getClass());
   public MapListBO(){
	   
   }
    /**
     *显示地图列表
     * */
   
   @SuppressWarnings("unchecked")
public List init(Search_MapLeft left)throws Exception{
	   List relist=null;
	   List param=new ArrayList();
	   StringBuffer sb =new StringBuffer();
	   Engine engine=null;
	   sb.append(" select mapid,mapname,mapinfo from maplist where 1=1 " );
	   String explain=left.getMap_explain();  
	   String name=left.getMap_name();
      
	   if( explain !=null && !explain.equals("")){//地图说明
		   sb.append(" and mapinfo like ? ");
		   param.add(explain+"%");
		   
	   }if( name != null && !name.equals("")){//名称
		   sb.append(" and mapname like ? ");
		   param.add(name+"%");
		   
	   }
	   try{
		   engine=EngineFactory.getEngine("test");
		   Query query=engine.getQuery();
	       relist=query.getResults(sb.toString(),param.toArray(),MapList.class);
	       engine.commit();
	   }catch(Exception e){
		   engine.rollback();
		   log.error(e);
		   throw e;
	   }finally{
		   engine.closeEngine();
	   }
	   return relist;
     }
   
    /**
     * 删除地图
     * */
   public void delete(String id)throws Exception{
	   Engine engine=null;
	   try{
		   engine=EngineFactory.getEngine("test");
		   MapList map=(MapList)engine.load(MapList.class,id);
		   engine.delete(map);
		   engine.commit();
	   }catch(Exception e){
		   engine.rollback();
		   log.error(e);
		   throw e;
	   }finally{
		   engine.closeEngine();
	   }
   }
   
   /**
    * 增加地图和缩略图
    * */
    @SuppressWarnings("unchecked")
	public void saveMap(MapAdd map)throws Exception {
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	sb.append("  insert into  maplist(mapid,mapinfo,mapdata,scale,mapname,maplevel,parentid,scaletoparentx,scaletoparenty,offsetx,offsety,CompressData,iscad ) ");
    	sb.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	FormFile file=map.getFile(); //取得上传的文件 
    	InputStream stream = file.getInputStream();//把文件读入
    	
    	//把原图改成缩略图
    	InputStream stream2 = file.getInputStream(); 
    	BufferedImage src=ImageIO.read(stream2);//构造Image对象
    	int wideth=src.getWidth(null); //得到源图宽
        int height=src.getHeight(null); //得到源图长
        BufferedImage tag=new BufferedImage(wideth/2,height/2,BufferedImage.TYPE_INT_BGR);
        tag.getGraphics().drawImage(src,0,0,wideth/2,height/2,null);//绘制缩小后的图
        FileOutputStream out=new FileOutputStream("C:\\WINDOWS\\Web\\"+file.toString());//输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag); //近JPEG编码
        out.close();
    	
        //从文件夹里面取出 ，插入到temporaryImage(存放小图)
//        File newFile=new File("D:\\Tomcat 5.0\\webapps\\kj133\\temporaryImage\\"+file.toString());
//        File newFile=new File("D:\\Program Files\\Apache Software Foundation\\Tomcat 5.0\\webapps\\kj133\\temporaryImage\\"+file.toString());
        File newFile=new File("C:\\WINDOWS\\Web\\"+file.toString());
        FileInputStream filestream=new FileInputStream(newFile);
        param.add(map.getId());
    	param.add(map.getInfo());
        param.add(Speed.initializeBlob(stream,stream.available())); 
    	param.add(map.getScale());
    	param.add(map.getName());
    	param.add("0");
    	param.add("-1");
    	param.add("1.0");
    	param.add("1.0");
    	param.add("0");
    	param.add("0");
        param.add(Speed.initializeBlob(filestream,filestream.available())); 
    	param.add("0");
    	
    	try{
    		engine=EngineFactory.getEngine("test");
    		engine.executeSpecialSQL(sb.toString(),param.toArray());
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
            log.error("增加地图异常",e);
            throw e;    		
    	}finally{
    		engine.closeEngine();
    	}
     }
    
    /**
	  * 提取单条数据
	  */
	 public MapList load(String id)throws Exception{
		 Engine engine=null;
		 MapList map=null;
		 try{
			 engine=EngineFactory.getEngine("test");
			 map=(MapList)engine.load(MapList.class,id);
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return map;
	 }
	 /**
	  * udpate Map
	  * */
	  @SuppressWarnings("unchecked")
	public void update(MapAdd map)throws Exception{
		  Engine engine=null;
		  List param=new ArrayList();
		  StringBuffer sb=new StringBuffer();
		  FormFile file=map.getFile();
		  if( file == null )
		  {  
			 sb.append(" update maplist set mapinfo=?,scale=?,mapname=? where mapid= ? ");
			 param.add(map.getInfo());
			 param.add(map.getScale());
			 param.add(map.getName());
			 param.add(map.getId());
		   }else{
			  InputStream stream = file.getInputStream();//把文件读入
			  sb.append("update maplist set mapinfo=?,mapdata=?,scale=?,mapname=? where mapid= ?");
			  param.add(map.getInfo());
			  param.add(Speed.initializeBlob(stream,stream.available()));
			  param.add(map.getScale());
			  param.add(map.getName());
			  param.add(map.getId());
		   }
		  try{
			  engine=EngineFactory.getEngine();
			  engine.executeSpecialSQL(sb.toString(),param.toArray());
              engine.commit();
		  }catch(Exception e){
			  engine.rollback();
			  log.error(e);
			  throw e;
		  }finally{
			  engine.closeEngine();
		  }
	  }
}

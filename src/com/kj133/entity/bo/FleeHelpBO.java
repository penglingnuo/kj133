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
import com.kj133.entity.FleeHelp;
import com.kj133.entity.MapAdd;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

 

public class FleeHelpBO {

	private final Logger log=Logger.getLogger(this.getClass());
	 public FleeHelpBO(){
		 
	 }
	 
	 
	 /**
	  * ��ȡ��������
	  */
	 public FleeHelp load(String id)throws Exception{
		 Engine engine=null;
		 FleeHelp map=null;
		 try{
			 engine=EngineFactory.getEngine("test");
			 map=(FleeHelp)engine.load(FleeHelp.class,id);
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
	  * ��ȡ����ID��
	  */
	 public List getMaxId( )throws Exception{
			List list=null;
			Engine engine=null;
			List param=new ArrayList();
			StringBuffer sb=new StringBuffer();
			sb.append(" select top 1  recordid,mapdata,compressdata,mapinfo  from fleeHelp  order by recordid desc  ");
			try{
				 engine=EngineFactory.getEngine("test");
				 Query query=engine.getQuery();
			     list=query.getResults(sb.toString(),param.toArray(),FleeHelp.class ); 
			     engine.commit();
			}catch(Exception e){
				engine.rollback();
				log.error(e);
				throw e;			
			}finally{
				 engine.closeEngine();
			}
			return list;
		}
	 
	 
	 
	 
	 /**
	    * ���ӵ�ͼ������ͼ
	    * */
	    @SuppressWarnings("unchecked")
		public void saveMap(MapAdd map)throws Exception {
	    	Engine engine=null;
	    	List param=new ArrayList();
	    	StringBuffer sb=new StringBuffer();
	        sb.append("   insert into fleeHelp values(?,?,?,?) ");
	    	FormFile file=map.getFile(); //ȡ���ϴ����ļ� 
	    	InputStream stream = file.getInputStream();//���ļ�����
	    	
	    	//��ԭͼ�ĳ�����ͼ
	    	InputStream stream2 = file.getInputStream(); 
	    	BufferedImage src=ImageIO.read(stream2);//����Image����
	    	int wideth=src.getWidth(null); //�õ�Դͼ��
	        int height=src.getHeight(null); //�õ�Դͼ��
	        BufferedImage tag=new BufferedImage(wideth/2,height/2,BufferedImage.TYPE_INT_BGR);
	        tag.getGraphics().drawImage(src,0,0,wideth/2,height/2,null);//������С���ͼ
	       // FileOutputStream out=new FileOutputStream("E:\\Program Files\\Apache Software Foundation\\Tomcat 5.0\\webapps\\kj133\\temporaryImage\\"+file.toString());//������ļ���
	        FileOutputStream out=new FileOutputStream("D:\\Tomcat 5.0\\webapps\\kj133\\temporaryImage\\"+file.toString());//������ļ���
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        encoder.encode(tag); //��JPEG����
	        out.close();
	    	
	        //���ļ�������ȡ�� �����뵽temporaryImage(���Сͼ)
//	        File newFile=new File("E:\\Program Files\\Apache Software Foundation\\Tomcat 5.0\\webapps\\kj133\\temporaryImage\\"+file.toString());
	        File newFile=new File("E:\\Tomcat 5.0\\webapps\\kj133\\temporaryImage\\"+file.toString());
	        FileInputStream filestream=new FileInputStream(newFile);
	        param.add(map.getId());
	        param.add(map.getInfo());
	        param.add(Speed.initializeBlob(stream,stream.available())); 
	        param.add(Speed.initializeBlob(filestream,filestream.available())); 
	    	
	    	
	    	try{
	    		engine=EngineFactory.getEngine("test");
	    		engine.executeSpecialSQL(sb.toString(),param.toArray());
	    		engine.commit();
	    	}catch(Exception e){
	    		engine.rollback();
	            log.error("���ӵ�ͼ�쳣",e);
	            throw e;    		
	    	}finally{
	    		engine.closeEngine();
	    	}
	     }
	    
	    
	    
	    
	/***
	 * 
	 * @param map
	 * @throws Exception  �޸ĵ�ͼ
	 */
	@SuppressWarnings("unchecked")
	public void update(MapAdd map)throws Exception{
		  Engine engine=null;
		  List param=new ArrayList();
		  StringBuffer sb=new StringBuffer();
		  FormFile file=map.getFile();
		  if( file == null )//�ļ�Ϊ��
		   {  
			 sb.append(" update FleeHelp set mapid=? ,MapInfo= ?");
			 param.add(map.getId());
			 param.add(map.getInfo());
		   }else{
			  InputStream stream = file.getInputStream();//���ļ�����
			  sb.append("update FleeHelp set mapid=? ,MapInfo= ?,mapdata=?");
			  param.add(map.getId());
			  param.add(map.getInfo());
			  param.add(Speed.initializeBlob(stream,stream.available()));
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

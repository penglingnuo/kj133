package com.kj133.entity.bo;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;
import org.speedframework.Speed;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;
import com.kj133.entity.vo.EmployeeVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.entity.AddEmployee_Form;
import com.kj133.entity.Search_Employee;
import com.kj133.entity.Staffer;
import com.kj133.entity.vo.EditCardVO;
import com.kj133.util.Global;

public class EmployeeBO {
  
	 private Logger log=Logger.getLogger(this.getClass());
	 
	 public EmployeeBO(){
		 
	 }
	 
	 /**
	  * 卡
	  * */
	 public List getCard()throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		  sb.append(" select * from (select cardid as cardid ,cardmode as cardmode  from recog where cardid<>0 ) as QueryTable order by cardid ");
		  try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
			  relist=query.getResults(sb.toString(),param.toArray(),EditCardVO.class);
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
	  * 获取员工信息
	  * */
	 @SuppressWarnings("unchecked")
	public List getStaffer(Search_Employee employee,Pagination pagination)throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		  sb.append(" select CardID as cardid,[Name] as username, Mobile as mobile, Department as department, ");
          sb.append(" [Group] as gro,Occupation as occupation ,WorkType as worktype  from staffer where 1=1  ");

          if(employee.getCardid() != null && !employee.getCardid().equals("")){
        	Global  gobal=new Global();
   		    List list=gobal.SuggestEmployees(employee.getCardid());
  			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
  				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
  				sb.append(" and staffer.stafferid=? ");
  				param.add(vo.getStafferid());
  			}else{//没有卡号或用户名
  				sb.append(" and staffer.[name]=? ");
  				param.add(employee.getCardid());
  			}
//			  sb.append(" and  staffer.stafferid = ? ");
//			  param.add(employee.getCardid());
		  }if(employee.getDepart() != null && employee.getDepart().length() >0 ){
			  sb.append(" and  staffer.department = ?");
			  param.add(employee.getDepart());
		  }if(employee.getWorktype() != null &&  employee.getWorktype().length() >0 ){
			  sb.append(" and  staffer.worktype = ? ");
			  param.add(employee.getWorktype());
		  }  
          try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
			  relist=query.getResults(sb.toString(),param.toArray(),EmployeeVO.class,pagination);
			  System.out.println("sb==="+sb);
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
	  * 获取员工信息
	  * 刘萌更改，暂时使用
	  * */
	 @SuppressWarnings("unchecked")
	 public List getStaffer(Search_Employee employee)throws Exception{
		 List relist=null;
		 List param=new ArrayList();
		 Engine engine=null;
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select stafferID as stafferid,CardID as cardid,[Name] as username, Mobile as mobile, Department as department, ");
		 sb.append(" [Group] as gro,Occupation as occupation ,WorkType as worktype ,convert(varchar(200),jointime,111) as jointime  from staffer where 1=1  ");
		 
		 if(employee.getCardid() != null && !employee.getCardid().equals("")){
			 Global  gobal=new Global();
			 List list=gobal.SuggestEmployees(employee.getCardid());
			 if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				 SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				 sb.append(" and staffer.stafferid=? ");
				 param.add(vo.getStafferid());
			 }else{//没有卡号或用户名
				 sb.append(" and staffer.[name]=? ");
				 param.add(employee.getCardid());
			 }
//			  sb.append(" and  staffer.stafferid = ? ");
//			  param.add(employee.getCardid());
		 }if(employee.getDepart() != null && employee.getDepart().length() >0 ){
			 sb.append(" and  staffer.department = ?");
			 param.add(employee.getDepart());
		 }if(employee.getWorktype() != null &&  employee.getWorktype().length() >0 ){
			 sb.append(" and  staffer.worktype = ? ");
			 param.add(employee.getWorktype());
		 }  
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),EmployeeVO.class);
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
	 public List getStafferPrint(Search_Employee employee)throws Exception{
		 List relist=null;
		 List param=new ArrayList();
		 Engine engine=null;
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select stafferID as stafferid,CardID as cardid,[Name] as username, Mobile as mobile, Department as department, ");
		 sb.append(" [Group] as gro,Occupation as occupation ,WorkType as worktype ,convert(varchar(200),jointime,111) as jointime  from staffer where 1=1  ");
		 
		 if(employee.getCardid() != null && !employee.getCardid().equals("")){
       	  Global  gobal=new Global();
  		    List list=gobal.SuggestEmployees(employee.getCardid());
 			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
 				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
 				sb.append(" and staffer.stafferid=? ");
 				param.add(vo.getStafferid());
 			}else{//没有卡号或用户名
 				sb.append(" and staffer.[name]=? ");
 				param.add(employee.getCardid());
 			}
//			  sb.append(" and  staffer.stafferid = ? ");
//			  param.add(employee.getCardid());
		  }if(employee.getDepart() != null && employee.getDepart().length() >0 ){
			 sb.append(" and  staffer.department = ?");
			 param.add(employee.getDepart());
		 }if(employee.getWorktype() != null &&  employee.getWorktype().length() >0 ){
			 sb.append(" and  staffer.worktype = ? ");
			 param.add(employee.getWorktype());
		 }  
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),EmployeeVO.class);
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
	  * 删除员工
	  * */
	 @SuppressWarnings("unchecked")
	public void delete(String sid)throws Exception{
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		  sb.append(" delete from  staffer  where stafferid= ? ");
		  List param=new ArrayList();
		  param.add(sid);
		  try{
			  engine=EngineFactory.getEngine("test");
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
	 /***
	  * save
	  */
	 @SuppressWarnings("unchecked")
	public void save(AddEmployee_Form emp)throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 Calendar cal=Calendar.getInstance();
		 SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	 FormFile file=emp.getFile(); //取得上传的文件 
    	 InputStream stream = file.getInputStream();//把文件读入
		 StringBuffer sb=new StringBuffer();
		 sb.append(" insert into staffer (stafferID,CardID,[Name],Sex,Birthday,Certificate,CertificateID, ");
		 sb.append(" Marriage, Stature,Weight,BloodType,Eyesight,Education,Technica,WorkPhone,Mobile,Address, ");
		 sb.append(" PostZip,HomePhone,Department,[Group],Occupation,WorkType,JoinTime,photo,CreateTime) ");
		 sb.append("  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ");
		 param.add(emp.getStafferid());
		 param.add(emp.getCardid());
		 param.add(emp.getName());
		 param.add(emp.getSex());
		 param.add(emp.getBirthday());
		 param.add(emp.getCertificate());
		 param.add(emp.getCertificateid());
		 param.add(emp.getMarriage());
		 param.add(emp.getStature());
		 param.add(emp.getWeight());
		 param.add(emp.getBloodtype());
		 param.add(emp.getEyesight());
		 param.add(emp.getEducation());
		 param.add(emp.getTechnica());
		 param.add(emp.getWorkphone());
		 param.add(emp.getMobile());
		 param.add(emp.getAddress());
		 param.add(emp.getPostzip());
		 param.add(emp.getHomephone());
		 param.add(emp.getDepartment());
		 param.add(emp.getGroup());
		 param.add(emp.getOccupation());
		 param.add(emp.getWorktype());
		 param.add(emp.getJointime());
		 param.add(Speed.initializeBlob(stream,stream.available())); 
		 param.add(formatter.format(cal.getTime()));
		 try{
			  engine=EngineFactory.getEngine("test");
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
	 
	 /**
	  * load Employee
	  * */
	 @SuppressWarnings("unchecked")
	public Staffer load(String sid)throws Exception{
		 Engine engine=null; 
		 Staffer staffer=null;
		 try{
			 engine=EngineFactory.getEngine("test");
			 staffer=(Staffer)engine.load(Staffer.class,sid);
//			 System.out.println("..........."+staffer.getAddress());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return staffer;
	 }
	 
	 
	 /**
	  * update Employee
	  * */
    @SuppressWarnings("unchecked")
	public  void upda(AddEmployee_Form emp,String cid,String sid,String sid1)throws Exception{
    	Engine  engine=null;
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	if( emp.getFile() == null){
        	sb.append(" update staffer set stafferid=?,cardid=?,[Name]=?,Sex=?,Birthday=?,Certificate=?,CertificateID=?,Marriage=?, Stature=?, ");
        	sb.append(" Weight=?,BloodType=?,Eyesight=?,Education=?,Technica=?,WorkPhone=?,Mobile=?,Address=?,PostZip=?, ");
        	sb.append(" HomePhone=?,Department=?,[Group]=?,Occupation=?,WorkType=?,JoinTime=?,modifytime = ? where stafferid=?");
        	 param.add(sid);
   		 	 param.add(cid); 
        	 param.add(emp.getName());
    		 param.add(emp.getSex());
    		 param.add(emp.getBirthday());
    		 param.add(emp.getCertificate());
    		 param.add(emp.getCertificateid());
    		 param.add(emp.getMarriage());
    		 param.add(emp.getStature());
    		 param.add(emp.getWeight());
    		 param.add(emp.getBloodtype());
    		 param.add(emp.getEyesight());
    		 param.add(emp.getEducation());
    		 param.add(emp.getTechnica());
    		 param.add(emp.getWorkphone());
    		 param.add(emp.getMobile());
    		 param.add(emp.getAddress());
    		 param.add(emp.getPostzip());
    		 param.add(emp.getHomephone());
    		 param.add(emp.getDepartment());
    		 param.add(emp.getGroup());
    		 param.add(emp.getOccupation());
    		 param.add(emp.getWorktype());
    		 param.add(emp.getJointime());
    		 param.add(formatter.format(cal.getTime()));
    		 param.add(sid1);

    	}else{
        	 FormFile file=emp.getFile(); //取得上传的文件
        	 InputStream stream = file.getInputStream();//把文件读入
    		 sb.append(" update staffer set [Name]=?,Sex=?,Birthday=?,Certificate=?,CertificateID=?,Marriage=?, Stature=?, ");
    	     sb.append(" Weight=?,BloodType=?,Eyesight=?,Education=?,Technica=?,WorkPhone=?,Mobile=?,Address=?,PostZip=?, ");
    	     sb.append(" HomePhone=?,Department=?,[Group]=?,Occupation=?,WorkType=?,JoinTime=?,photo=?,modifytime = ?  where cardid=? ");
    	     param.add(emp.getName());
    		 param.add(emp.getSex());
    		 param.add(emp.getBirthday());
    		 param.add(emp.getCertificate());
    		 param.add(emp.getCertificateid());
    		 param.add(emp.getMarriage());
    		 param.add(emp.getStature());
    		 param.add(emp.getWeight());
    		 param.add(emp.getBloodtype());
    		 param.add(emp.getEyesight());
    		 param.add(emp.getEducation());
    		 param.add(emp.getTechnica());
    		 param.add(emp.getWorkphone());
    		 param.add(emp.getMobile());
    		 param.add(emp.getAddress());
    		 param.add(emp.getPostzip());
    		 param.add(emp.getHomephone());
    		 param.add(emp.getDepartment());
    		 param.add(emp.getGroup());
    		 param.add(emp.getOccupation());
    		 param.add(emp.getWorktype());
    		 param.add(emp.getJointime());
    		 param.add(Speed.initializeBlob(stream,stream.available())); 
    		 param.add(formatter.format(cal.getTime()));
    		 param.add(cid);
    	}
    	try{
    	    engine=EngineFactory.getEngine("test");
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

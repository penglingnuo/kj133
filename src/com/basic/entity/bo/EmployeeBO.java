package com.basic.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Ouser;
import com.kj133.entity.Search_Employee;
import com.kj133.entity.vo.EmployeeVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class EmployeeBO {
	
	private Logger log=Logger.getLogger(this.getClass());
	 
	 public EmployeeBO(){
		 
	 }
	 
	
	/**
	  * 获取员工信息   有参查询
	  * */
	 @SuppressWarnings("unchecked")
	public List getStaffer(Search_Employee employee,Pagination pagination)throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		  String idname=employee.getCardid();
		  String cardid=null;
		  if(idname!=null && !idname.equals("")){
			  String [] idhnames=idname.split("--");
			  cardid=idhnames[0];
		  }
		  sb.append(" select CardID as cardid,[Name] as username,sta.Department as department, ");
         sb.append(" [Group] as gro,Occupation as occupation ,WorkType as worktype  from staffer sta,reportpopedom rp where 1=1  ");
         sb.append(" and rp.userid=? and rp.department=sta.department");
         param.add(employee.getUserid());

         if(employee.getCardid() != null && !employee.getCardid().equals("")){
       	Global  gobal=new Global();
  		    List list=gobal.SuggestEmployees(cardid);
 			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
 				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
 				sb.append(" and sta.stafferid=? ");
 				param.add(vo.getStafferid());
 			}else{//没有卡号或用户名
 				sb.append(" and sta.[name]=? ");
 				param.add(cardid);
 			}
		  }if(employee.getGroup() != null && employee.getGroup().length() >0 ){
			  sb.append(" and  sta.[department] = ?");
			  param.add(employee.getGroup());
		  }
         try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
			  relist=query.getResults(sb.toString(),param.toArray(),EmployeeVO.class,pagination);
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
	 
	 @SuppressWarnings("unchecked")
	 public List getStafferPrint(Search_Employee employee)throws Exception{
		 List relist=null;
		 List param=new ArrayList();
		 Engine engine=null;
		 StringBuffer sb=new StringBuffer();
		 String idname=employee.getCardid();
		  String cardid=null;
		  if(idname!=null && !idname.equals("")){
			  String [] idhnames=idname.split("--");
			  cardid=idhnames[0];
		  }
		 sb.append(" select CardID as cardid,[Name] as username,sta.Department as department, ");
         sb.append(" [Group] as gro,Occupation as occupation ,WorkType as worktype  from staffer sta,reportpopedom rp where 1=1  ");
         sb.append(" and rp.userid=? and rp.department=sta.department");
         param.add(employee.getUserid());
         
         if(employee.getCardid() != null && !employee.getCardid().equals("")){
            	Global  gobal=new Global();
       		    List list=gobal.SuggestEmployees(cardid);
      			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
      				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
      				sb.append(" and sta.stafferid=? ");
      				param.add(vo.getStafferid());
      			}else{//没有卡号或用户名
      				sb.append(" and sta.[name]=? ");
      				param.add(cardid);
      			}
     		  }if(employee.getGroup() != null && employee.getGroup().length() >0 ){
     			  sb.append(" and  sta.[department] = ?");
     			  param.add(employee.getGroup());
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

}

package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_Employee_menology_mount;
import com.kj133.entity.vo.Employee_menology_mountVO;

public class Employee_menology_mountBO {


	/**
	 * 员工考勤设置
	 */
	
	private Logger log=Logger.getLogger(this.getClass());
	public Employee_menology_mountBO(){
		
	}
	
	
    /***
     * 
     * @param mou
     * @return 查询
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public List init(Search_Employee_menology_mount mou)throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append(" select staffer.stafferid , [name], cardid, [group] , worktype,isnull(attendanceSet.minworktime,0)as ");
		sb.append(" minworktime  from staffer  left join AttendanceSet  on  staffer.stafferid=attendanceSet.stafferid  where 1=1 ");
	    
		if(mou.getSid() !=null && !mou.getSid().equals("")){
	        sb.append(" and staffer.stafferid= ?");
	        param.add(mou.getSid());
	    }if(mou.getWorktype() != null && !mou.getWorktype().equals("")){
	    	sb.append(" and worktype  = ? ");
	    	param.add(mou.getWorktype());
	    }
		try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
		     list=query.getResults(sb.toString(),param.toArray(),Employee_menology_mountVO.class); 
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
	
	
	
	 /***
	  * 
	  * @param id
	  * @return 删除
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	public void delete(String id)throws Exception{
		   Engine engine=null;
		   List param=new ArrayList();
		   StringBuffer sb=new StringBuffer();
		   sb.append(" delete from AttendanceSet where  stafferid=? ");
		   param.add(id);
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
	  * 
	  * @param id
	  * @param miniworktime  
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	public void add(String id,String miniworktime)throws Exception{
		   Engine engine=null;
		   List param=new ArrayList();
		   StringBuffer sb=new StringBuffer();
		   sb.append(" insert into attendanceSet(stafferid,minworktime) values (?,?) ");
//		   sb.append(" insert into attendanceSet(stafferid,miniworktime) values (?,?) ");
		   param.add(id);
		   param.add(miniworktime);
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
	  * 
	  * @param miniworktime
	  * @param stafferid
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	public void update(String miniworktime,String stafferid)throws Exception{
		   Engine engine=null;
		   List param=new ArrayList();
		   StringBuffer sb=new StringBuffer();
		   sb.append(" update AttendanceSet set  miniworktime=? where stafferid= ?");
		   param.add(miniworktime);
		   param.add(stafferid);
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

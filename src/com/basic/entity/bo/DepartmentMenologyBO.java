package com.basic.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.basic.entity.vo.DepartmentMenologyVO;
import com.basic.entity.vo.OverTimeVO;
import com.kj133.entity.Search_Department_menology;
import com.kj133.entity.bo.Department_menologyBO;
import com.kj133.entity.vo.Department_menologyVO;
import com.kj133.util.Global;

public class DepartmentMenologyBO {
	
	 /**
	   *  部门考勤月报
	   * */
		private static final Logger log=Logger.getLogger(Department_menologyBO.class);
		public DepartmentMenologyBO(){
			
		}

		/**
		 * 部门结果集
		 * */
		@SuppressWarnings("unchecked")
		public List getList(Search_Department_menology men,Pagination pagin)throws Exception{
			List relist=null;
			Engine engine=null;
			List param=new ArrayList();
			StringBuffer sb=new StringBuffer();
			Global go=new Global();
			
			String idname=men.getCardid();
			String cardid=null;
			if(idname!=null && !idname.equals("")){
				String []idnames =idname.split("--");
				cardid=idnames[0];
			}
			String stime=men.getStime();
			int days=go.getDaysOfMonth(stime);
			String etime=stime;
			String department=men.getDep();
			String gro=men.getGro();
			
			sb.append(" select aaaa.name,aaaa.cardid,aaaa.department,aaaa.[group],aaaa.bantypename,aaaa.zao,aaaa.zhong,aaaa.wan,aaaa.overtimecountsg,aaaa.overtimecountxg,(aaaa.overtimecountsg+aaaa.overtimecountxg) countall, ");
			//sb.append(" convert(numeric(8,1),ROUND(isnull(workalltime,0) /60.0, 1)) as workalltime from ( ");
			sb.append(" substring(rtrim(1000+aaaa.workalltime/60),2,3)+'时'+substring(rtrim(100+aaaa.workalltime%60),2,2)+'分钟' as workalltime from ( ");
			sb.append(" select aaa.name,aaa.cardid,aaa.department,aaa.[group],aaa.bantypename, ");
			sb.append(" max(case ban_name when '早班' then aaa.overtimecountsg+aaa.overtimecountxg else 0 end) zao, ");
			sb.append(" max(case ban_name when '中班' then aaa.overtimecountsg+aaa.overtimecountxg else 0 end) zhong, ");
			sb.append(" max(case ban_name when '晚班' then aaa.overtimecountsg+aaa.overtimecountxg else 0 end) wan ,  ");
	        sb.append(" sum(aaa.overtimecountsg)overtimecountsg,sum(aaa.overtimecountxg)overtimecountxg, ");
	        sb.append(" sum(aaa.worktime) as workalltime  ");
	        sb.append("  from ( ");
	        sb.append(" select aa.name,aa.cardid,aa.department,aa.[group],aa.bantypename,aa.ban_name,sum(aa.worktime)worktime,sum(aa.overtimecountsg)overtimecountsg,sum(aa.overtimecountxg)overtimecountxg from ( ");
	        sb.append(" select a.*,case when  a.worktime >= a.mintime then 1  else 0 end as overtimecountsg,case when  a.worktime < a.mintime then 1  else 0 end as overtimecountxg from ( ");
	        sb.append(" select s.name,w.cardid,s.department,s.[group],m.bantypename,b.ban_name, ");
	        sb.append(" datediff(mi,downtime,uptime)worktime,wot.mintime, 1 countall ");
	        sb.append(" from workattendanceex w left join staffer s on s.cardid=w.cardid");
	        
	        if(cardid!=null && !cardid.equals("")){
				sb.append(" and s.cardid= ?");
				param.add(cardid);
				
			}if(department!=null && !department.equals("")){
				sb.append(" and s.department=? ");
				param.add(department);
				
			}if(gro!=null && !gro.equals("")){
				sb.append(" and s.[group]=? ");
				param.add(gro);
				
			}
	        
	        
	        
	        sb.append(" left join MainBanType m on m.bantypeid=w.bantypeid ");
	        sb.append(" left join bantype b on b.ban_id= w.banid ");
	        sb.append(" left join workovertime wot on wot.bantypeid=w.bantypeid and wot.stafferid=w.stafferid ");
	        sb.append(" where  downtime>=? and downtime<=? and uptime is not null )a ) aa ");
	        sb.append(" group by aa.cardid,aa.name,aa.department,aa.[group],aa.bantypename,aa.ban_name )aaa  ");
	        sb.append(" group by aaa.cardid,aaa.name,aaa.department,aaa.[group],aaa.bantypename)aaaa,reportpopedom rep");
	        sb.append(" where rep.department=aaaa.department and rep.userid=? ");
	        
	        param.add(stime+"-01 00:00:00");
	        param.add(etime+"-"+String.valueOf(days)+" 23:59:59");
	        param.add(men.getUserid());
			
			
			try{
				engine=EngineFactory.getEngine("test");
			    Query query=engine.getQuery();
			    relist=query.getResults(sb.toString(),param.toArray(),DepartmentMenologyVO.class,pagin);
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
		 * 部门结果集  导出Execl
		 * */
		@SuppressWarnings("unchecked")
		public List getList(Search_Department_menology men)throws Exception{
			List relist=null;
			Engine engine=null;
			List param=new ArrayList();
			StringBuffer sb=new StringBuffer();
			Global go=new Global();
			
			String idname=men.getCardid();
			String cardid=null;
			if(idname!=null && !idname.equals("")){
				String []idnames =idname.split("--");
				cardid=idnames[0];
			}
			String stime=men.getStime();
			int days=go.getDaysOfMonth(stime);
			String etime=stime;
			String department=men.getDep();
			String gro=men.getGro();
			
			sb.append(" select aaaa.name,aaaa.cardid,aaaa.department,aaaa.[group],aaaa.bantypename,aaaa.zao,aaaa.zhong,aaaa.wan,aaaa.overtimecountsg,aaaa.overtimecountxg,(aaaa.overtimecountsg+aaaa.overtimecountxg) countall, ");
			sb.append(" convert(numeric(8,1),ROUND(isnull(workalltime,0) /60.0, 1)) as workalltime from ( ");
			sb.append(" select aaa.name,aaa.cardid,aaa.department,aaa.[group],aaa.bantypename, ");
			sb.append(" max(case ban_name when '早班' then aaa.overtimecountsg+aaa.overtimecountxg else 0 end) zao, ");
			sb.append(" max(case ban_name when '中班' then aaa.overtimecountsg+aaa.overtimecountxg else 0 end) zhong, ");
			sb.append(" max(case ban_name when '晚班' then aaa.overtimecountsg+aaa.overtimecountxg else 0 end) wan ,  ");
	        sb.append(" sum(aaa.overtimecountsg)overtimecountsg,sum(aaa.overtimecountxg)overtimecountxg, ");
	        sb.append(" sum(aaa.worktime) as workalltime  ");
	        sb.append("  from ( ");
	        sb.append(" select aa.name,aa.cardid,aa.department,aa.[group],aa.bantypename,aa.ban_name,sum(aa.worktime)worktime,sum(aa.overtimecountsg)overtimecountsg,sum(aa.overtimecountxg)overtimecountxg from ( ");
	        sb.append(" select a.*,case when  a.worktime >= a.mintime then 1  else 0 end as overtimecountsg,case when  a.worktime < a.mintime then 1  else 0 end as overtimecountxg from ( ");
	        sb.append(" select s.name,w.cardid,s.department,s.[group],m.bantypename,b.ban_name, ");
	        sb.append(" datediff(mi,downtime,uptime)worktime,wot.mintime, 1 countall ");
	        sb.append(" from workattendanceex w left join staffer s on s.cardid=w.cardid");
	        
	        if(cardid!=null && !cardid.equals("")){
				sb.append(" and s.cardid= ?");
				param.add(cardid);
				
			}if(department!=null && !department.equals("")){
				sb.append(" and s.department=? ");
				param.add(department);
				
			}if(gro!=null && !gro.equals("")){
				sb.append(" and s.[group]=? ");
				param.add(gro);
				
			}
	        
	        
	        
	        sb.append(" left join MainBanType m on m.bantypeid=w.bantypeid ");
	        sb.append(" left join bantype b on b.ban_id= w.banid ");
	        sb.append(" left join workovertime wot on wot.bantypeid=w.bantypeid and wot.stafferid=w.stafferid ");
	        sb.append(" where  downtime>=? and downtime<=? and uptime is not null )a ) aa ");
	        sb.append(" group by aa.cardid,aa.name,aa.department,aa.[group],aa.bantypename,aa.ban_name )aaa  ");
	        sb.append(" group by aaa.cardid,aaa.name,aaa.department,aaa.[group],aaa.bantypename)aaaa,reportpopedom rep");
	        sb.append(" where rep.department=aaaa.department and rep.userid=? ");
	        
	        param.add(stime+"-01 00:00:00");
	        param.add(etime+"-"+String.valueOf(days)+" 23:59:59");
	        param.add(men.getUserid());
			
			
			try{
				engine=EngineFactory.getEngine("test");
			    Query query=engine.getQuery();
			    relist=query.getResults(sb.toString(),param.toArray(),DepartmentMenologyVO.class);
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

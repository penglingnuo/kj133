package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Search_Department_menology;
import com.kj133.entity.vo.Department_menologyVO;
import com.kj133.util.Global;
public class Department_menologyBO {
  /**
   *  部门考勤月报
   * */
	private static final Logger log=Logger.getLogger(Department_menologyBO.class);
	public Department_menologyBO(){
		
	}
	
	/**
	 * 部门结果集
	 * */
	@SuppressWarnings("unchecked")
	public List getList(Search_Department_menology men,String name)throws Exception{
		List relist=null;
		Global go = new Global();
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
			
	    	/*sb.append(" select c.*,isnull(shunxu.shunxu,9999) shunxu,c.Worktime/(case when (zao+zhong+wan+qita)=0 then 1 else (zao+zhong+wan+qita) end) counttime,总人数 countp,1.0*cishu/downsum pcdcount from (");
	    	sb.append(" select [group],Count(*) downsum,sum(worktime) worktime,sum(zao) zao,sum(zhong) zhong,sum(wan) wan,sum(qita) qita,sum(次数) cishu from(");
	    	sb.append(" select [group],stafferid,sum(worktime) worktime,sum(zao) zao,sum(zhong) zhong,sum(wan) wan,sum(qita) qita,sum(次数) 次数 from ( ");
	    	sb.append(" select [group],sum(worktime) worktime,stafferid,sum(次数) 次数, ");
	        sb.append(" case when ban_ID=1 then Count(stafferid) else 0 end as zao, ");
	        sb.append(" case when ban_ID=2 then Count(stafferid) else 0 end as zhong, ");
	        sb.append(" case when ban_ID=3 then Count(stafferid) else 0 end as wan, ");
	        sb.append(" case when ban_ID<>1 and ban_ID<>2 and ban_ID<>3 then Count(stafferid) else 0 end as qita ");
	        sb.append(" from ( ");
	    			
	        sb.append(" select [group],stafferid,cardid,ban_ID,ban_name,sum(worktime) worktime,sum(记数) 次数 from ( ");
	        sb.append(" select aa.*,ban_ID,bantype.ban_name,1 记数 from( ");
	        sb.append(" select e.*,g.[group] from ( ");
	        sb.append(" select  stafferid,cardid,bantypeid,kqtime,downtime,case when datediff(mi,downtime,uptime)<0 then 0 else datediff(mi,downtime,uptime) end as worktime from ");  
	        sb.append(" (select w.*,isnull(s.bantypeid,0) bantypeid from workattendanceex w left  join workovertime s on w.stafferid=s.stafferid) w where "); 
	    			
	    			
	        sb.append(" convert(varchar(10),kqtime,20)>=? and convert(varchar(10),kqtime,20)<? ");
//	        sb.append(" convert(varchar(10),kqtime,20)=? ");
	        param.add(men.getStime()+"-01");
	        param.add(go.getDay(men.getStime()+"-01",go.getDaysOfMonth(men.getStime()+"-01")));
	        sb.append(" ) e,(select cardid,cardmode from recog where cardmode<>'车辆') f,(select stafferid,[group] from staffer where [group]<>'参观人员' ");

	        if( men.getGro() !=null && !men.getGro().equals("")){
	    	sb.append(" and [group] = ? ");
	    	param.add(men.getGro());
	        }
	        
	        sb.append(" ) g where e.cardid=f.cardid and e.stafferid=g.stafferid  ");
	        sb.append(" ) aa,bantype where aa.bantypeid=bantype.bantypeid and  ");
	        sb.append(" ((start_stime < end_stime and substring(convert(char,downtime,108),1,5)>=start_stime and substring(convert(char,downtime,108),1,5)<end_stime) or ");
	        sb.append(" (start_stime > end_stime and (substring(convert(char,downtime,108),1,5)>=start_stime or substring(convert(char,downtime,108),1,5)<end_stime))) ");
	        sb.append(" ) h group by [group],stafferid,cardid,ban_ID,ban_name,convert(varchar(10),kqtime,20) ");
	    			
	    			
	        sb.append(" ) a group by [group],stafferid,ban_ID,ban_name,次数 ");
	        sb.append(" ) aaa group by [group],stafferid ");
	        sb.append(" ) b group by [group] ");
	        sb.append(" ) c  ");
	        sb.append(" left join (select [group],count(*) 总人数 from staffer a,recog b where a.Cardid=b.Cardid group by a.[group]) staffer on c.[group]=staffer.[group] ");
//	        sb.append(" left join shunxu on shunxu.[group]=c.[group] order by isnull(shunxu.shunxu,9999)  ");
	        sb.append(" left join shunxu on shunxu.[group]=c.[group] order by c.[group]  ");*/
			
			sb.append(" select aaa.[group],总人数 countp,入井人数 downsum,上井人数 upsum,次数 cishu,上井次数 upcishu,worktime,平均时长 counttime,人均入井次数 pcdcount,ban01 zao,ban02 zhong ,ban03 wan from( ");
			sb.append(" select c.*,isnull(shunxu.shunxu,9999) shunxu,c.Worktime/ ");
			sb.append(" (case when (Ban01+Ban02+Ban03)=0 then 1 else  ( Ban01+Ban02+Ban03) end) 平均时长, ");
			sb.append(" 总人数,1.0*次数/入井人数 人均入井次数 from ( ");
			sb.append(" select [group],Count(*) 入井人数,sum(上井人数) 上井人数,sum(worktime) worktime, ");
			sb.append(" sum(Ban01) as 'Ban01', sum(Ban02) as 'Ban02', sum(Ban03) as 'Ban03', ");
			sb.append(" sum(次数) 次数,sum(up次数) as 上井次数 from( ");
			sb.append(" select [group],stafferid,sum(worktime) worktime, ");
			sb.append(" sum(Ban01) as 'Ban01', sum(Ban02) as 'Ban02', sum(Ban03) as 'Ban03', ");
			sb.append(" sum(次数) 次数,sum(up次数) as up次数 ,  上井人数 from ( ");
			sb.append(" select [group],sum(worktime) worktime,stafferid,sum(次数) 次数,sum(up次数) as up次数 ,  上井人数 ");
			sb.append(" ,case when Ban_ID = 1 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban01' ,case when Ban_ID = 2 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban02' ,case when Ban_ID = 3 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban03' ");
			sb.append(" from ( ");
			sb.append(" select [group],stafferid,cardid,ban_ID,ban_name,sum(worktime) worktime,sum(记数) 次数,sum(up计数) up次数,case when sum(up计数) > 0 then 1 else 0 end 上井人数,bantypeid from ( ");
			sb.append(" select aa.*,ban_ID,bantype.ban_name,1 记数,case when isnull(uptime,0) =0 then 0 else 1 end up计数 from( ");
			sb.append(" select e.*,g.[group] from ( ");
			sb.append(" select  stafferid,cardid,bantypeid,kqtime,downtime,uptime,case when datediff(mi,downtime,uptime)<0 then 0 else datediff(mi,downtime,uptime) end as worktime from ");
			sb.append(" (select w.* from workattendanceex w left  join workovertime s on w.stafferid=s.stafferid) w where ");
//			sb.append(" convert(varchar(10),kqtime,20)>='2009-05-01' and convert(varchar(10),kqtime,20)<'2009-06-01' ");
			sb.append(" convert(varchar(10),kqtime,20)>=? and convert(varchar(10),kqtime,20)<? ");
			param.add(men.getStime()+"-01");
	        param.add(go.getDay(men.getStime()+"-01",go.getDaysOfMonth(men.getStime()+"-01")));
			sb.append(" ) e,(select cardid,cardmode from recog where cardmode<>'车辆') f,(select stafferid,[group] from staffer where [group]<>'参观人员') g ");
			sb.append(" where e.cardid=f.cardid and e.stafferid=g.stafferid ");
			sb.append(" ) aa,bantype where aa.bantypeid=bantype.bantypeid and  ");
			sb.append(" ((start_stime < end_stime and substring(convert(char,downtime,108),1,5)>=start_stime and substring(convert(char,downtime,108),1,5)<end_stime) or ");
			sb.append(" (start_stime > end_stime and (substring(convert(char,downtime,108),1,5)>=start_stime or substring(convert(char,downtime,108),1,5)<end_stime))) ");
			sb.append(" ) h group by [group],stafferid,cardid,ban_ID,ban_name,convert(varchar(10),kqtime,20),bantypeid ");
			sb.append(" ) a group by [group],stafferid,ban_ID,ban_name,次数,up次数,bantypeid,上井人数 ");
			sb.append(" ) aaa group by [group],stafferid,上井人数 ");
			sb.append(" ) b group by [group] ");
			sb.append(" ) c  ");
			sb.append(" left join (select [group],count(*) 总人数 from staffer a,recog b where a.Cardid=b.Cardid group by a.[group]) staffer on c.[group]=staffer.[group] ");
			sb.append(" left join shunxu on shunxu.[group]=c.[group]  ");
			sb.append(" ) aaa,reportpopedom rp  where rp.department=aaa.[group] and rp.userid='"+name+"' order by aaa.[group] ");
		
		
    	


    	try{
        	engine=EngineFactory.getEngine("test");
//        	engine.executeProcedureCall("{ call caldepday (?,?)}",new Object[] {dep.getStime(),userid});
        	Query query=engine.getQuery();
            relist=query.getResults(sb.toString(),param.toArray(),Department_menologyVO.class);
            int countp = 0;//总人数
            int downsum = 0;  //入井人数
            int upsum = 0;  //上井人数
            int cishu = 0; //入井次数
            int upcishu = 0; //上井次数
            int zao = 0,zhong =0,wan = 0,qita = 0;
            String worktime = "";   // 总时长
            String counttime = "";  // 平均时长
            double pcdcount = 0.0;   //  人均入井次数
            double pcdcount1 = 0.0;   //  人均入井次数
            

            int counttimes = 0;
            int alltimes = 0;
            String ae = "";
            String ce = "";
            int cc = 0;

            if( relist != null && relist.size() > 0){
            	for(int i=0;i<relist.size();i++){
            		Department_menologyVO vo=(Department_menologyVO)relist.get(i);
            		vo.setCount(String.valueOf(i+1));//序号
            		cc = Integer.valueOf(String.valueOf(i+1));
            		countp = countp + vo.getCountp();
            		downsum = downsum + vo.getDownsum();
            		upsum = upsum + vo.getUpsum();
            		cishu = cishu + vo.getCishu();
            		upcishu = upcishu + vo.getUpcishu();
            		zao = zao + vo.getZao();
            		zhong = zhong + vo.getZhong();
            		wan = wan + vo.getWan();
//            		qita = qita + vo.getQita();
            	
            		pcdcount = pcdcount + vo.getPcdcount();
            		vo.setPcdcount(Double.valueOf(go.RoundFracToString(vo.getPcdcount(),3)));
 				   int at = Integer.valueOf(vo.getWorktime()== null ? 0 : Integer.parseInt(vo.getWorktime()));
					int hour = at / 60;
					int min = at % 60;
					worktime = hour + "小时" + min + "分";
					
					alltimes += at;   
					vo.setWorktime(worktime);
					
					int ct = Integer.valueOf(vo.getCounttime()== null ? 0 : Integer.parseInt(vo.getCounttime()));
					int hours = ct / 60;
					int mins = ct % 60;
					counttime = hours + "小时" + mins + "分";
					counttimes += ct ;
					vo.setCounttime(counttime);

            	}
            	Department_menologyVO vo=new Department_menologyVO();
             vo.setCount("合计");

             vo.setCountp(countp);
             vo.setDownsum(downsum);
             vo.setUpsum(upsum);
             vo.setCishu(cishu);
             vo.setUpcishu(upcishu);
             vo.setZao(zao);
             vo.setZhong(zhong);
             vo.setWan(wan);
//             vo.setQita(qita);
     		  int ah = alltimes / 60;
    		  int am = alltimes % 60;
    		  ae = ah + "小时" + am + "分";
    		  vo.setWorktime(ae);
    		  
    		  int cs = counttimes / cc ;
    		  int cth = cs / 60;
    		  int ctm = cs % 60;
    		  ce = cth + "小时" + ctm + "分";
    		  vo.setCounttime(ce);
    		  
    		 double pc =  pcdcount / cc ;
    		 
    		 vo.setPcdcount(Double.valueOf(go.RoundFracToString(pc,3)));
             relist.add(vo);
            }

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

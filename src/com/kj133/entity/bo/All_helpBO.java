package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;
import com.kj133.entity.Search_AllHelp;
import com.kj133.entity.vo.All_helpVO;

public class All_helpBO {
       private final Logger log=Logger.getLogger(this.getClass());
       
       public All_helpBO(){
    	   
       }
       /**
        * 所有报警
        * */
       @SuppressWarnings("unchecked")
	public List init(Search_AllHelp ahelp,Pagination pagin)throws Exception{
    	   List relist=null;
    	   List param=new ArrayList();
    	   StringBuffer sb=new StringBuffer();
    	   Engine engine=null;
    	   sb.append(" declare @ds datetime,@de datetime,@dss datetime ");
    	   sb.append(" select @ds=convert(datetime,?),@de=convert(datetime,?)+1,@dss=@ds-3 ");
    	   sb.append(" select * from (select a.stafferid as stafferid,[name] as username,[group] as grop,worktype ");
    	   sb.append(" as worktype,a.cardreaderid as cardreaderid,cardreader.shortname as shortname,a.locatorid as ");
    	   sb.append(" locatorid,locator.shortname as locatorname,a.state as state,alarminfo.info as info,maxtime ");
    	   sb.append(" as etime,mintime as stime,@ds as startdate,@de as enddate ");
    	   sb.append(" from( select cardid,cardreaderid,locatorid,state,min(starttime) as mintime,max(endtime) as  ");
    	   sb.append(" maxtime,stafferid from v_locatedata where state&0x6e>0 and endtime>=@ds and starttime<@de and ");
    	   sb.append(" starttime>@dss group by cardid,cardreaderid,locatorid,state,stafferid)as a, ");
    	   sb.append(" staffer,cardreader,locator,alarminfo  ");
    	   sb.append(" where a.stafferid=staffer.stafferid and a.cardreaderid=cardreader.cardreaderid and a.state=alarminfo.state ");
    	   sb.append("and a.locatorid=locator.locatorid ) as querytable  where  1=1  " ); 
 
    	    String gro=ahelp.getGro();
	   	    String sid=ahelp.getSid();
	   	    String cid=ahelp.getCid();
	   	    String lid=ahelp.getLid();
    	    String radio=ahelp.getRad();
    	    String info[]=ahelp.getInfo();
    	     
	   	    String stime=ahelp.getStime();//截取注册时间大
	   		String s1=stime.substring(0,10);  
	   		String s2=stime.substring(11,stime.length()); 
	        
	   		String etime=ahelp.getEtime();    //截取注册时间小于
	   		String e1=etime.substring(0,10);  
	   		String e2=etime.substring(11,etime.length());  
	   		
	   		param.add(s1);
	   		param.add(e1);

	   	    if( gro != null && !gro.equals("")){//班组
	   	    	sb.append(" and grop = ? ");
	   	    	param.add(gro);
	   	    }if( sid != null && !sid.equals("")){//员工号
	   	    	sb.append(" and stafferid= ? ");
	   	    	param.add(sid);
	   	    }if( cid != null && !cid.equals("")){//分站号
	   	        sb.append("  and cardreaderid= ? ");
	   	        param.add(cid);	   	    
	   	    }if(lid != null && !lid.equals("")){//定位器号
	   	    	sb.append("  and locatorid= ? ");
	   	    	param.add(lid);
	   	    }if( radio != null && !radio.equals("")){
				  int j=0; 
				  if( radio.equals("radand"))
				  	{
			   	     	if( info != null && info.length>0 ){
			   	    	 for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					          }
			   	    	   sb.append(" and state = ? ");
						   param.add(String.valueOf(j));
			   	       }
				   }else{
				   	     if( info != null && info.length>0 ){  
				   	    	for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					        }
				   		     sb.append(" and state&? >0 ");
						     param.add(String.valueOf(j));
				   	    }
				   }
	   	    }try{
    	    	  sb.append(" and convert(char(8),etime,8)>=? and  convert(char(8),stime,8)<? ");
    		   	  param.add(s2);
    		   	  param.add(e2);
    	    	  engine=EngineFactory.getEngine("test");
    	    	  Query query=engine.getQuery();
    	    	  relist=query.getResults(sb.toString(),param.toArray(),All_helpVO.class,pagin);
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

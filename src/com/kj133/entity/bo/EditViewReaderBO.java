package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.CardReader1;
import com.kj133.entity.Search_ViewReader;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class EditViewReaderBO {
	
     private Logger log=Logger.getLogger(this.getClass());
     public EditViewReaderBO(){
     }
     
     /**
      * 编辑分站
      * 获取数据
      * */
    @SuppressWarnings("unchecked")
	public List init(Search_ViewReader reader,Pagination pagination)throws Exception{
    	List relist=null;
    	List param=new ArrayList();
    	Engine engine=null;
    	StringBuffer sb=new StringBuffer();

    	sb.append(" select * from (select CardReaderId,CRName,r.ShortName,r.x,r.y,r.z,r.RegDate,r.Mapid,ignoretimes,ignorelocator,locator.shortname sname,locatorignoretimes,r.state,r.modifydate,case r.ground when 1 then '井上设备' when 0 then '井下设备' else '不区分' end as ground,case checkreader when 1 then '是' else '否' end as checkreader,case when useantenna&1>0 then 'A' else '' end + case when useantenna&2>0 then 'B' else '' end + case when useantenna&4>0 then 'C' else '' end as useantenna1,useantenna,case IfAlarm when 1 then '使用' else '不使用'  end as IfAlarm from cardreader as r left join locator on r.ignorelocator=locator.locatorid where cardreaderID<>0 ");
    	sb.append(" ) as QueryTable  where 1=1  ");
    	

    	 String cid=reader.getCid();  
    	 String cname=reader.getCname(); 
    	
    	 String stime=reader.getStime(); //大于
    	 String minstime=reader.getMinstime(); //大于
    	 String maxstime=reader.getMaxstime(); //大于
    	 String etime=reader.getEtime();//小于
    	 
    	 if( cid!=null && !cid.equals("")){//分站号
    		Global  gobal=new Global();
 		    List list=gobal.SuggestCardreaders(cid);
 			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
 				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
 				sb.append(" and rtrim(cardreaderid) like  ? ");
 				param.add(vo.getCardreaderid()+"%");
 			}else{//没有卡号或用户名
 				sb.append(" and   crname like  ?  ");
 				param.add(cid);
 			}
//        	 sb.append(" and rtrim(cardreaderid) like  ? ");
//        	 param.add(cid+"%");
    	 }
//         if( cname!=null && !cname.equals("")){//分站名
//        	 sb.append(" and   crname like  ?  ");
//        	 param.add(cname+"%");
//         }
         if( stime!=null && !stime.equals("")){//注册时间大于
			 sb.append("  and regdate>= ?  ");
			 param.add(stime);
			 
		 }if( minstime!=null && !minstime.equals("")){//注册时间大于
			 sb.append("   and  convert(char(8),regdate,8)>= ? ");
			 
			 param.add(minstime); 
		 }if( etime!=null && !etime.equals("")){//注册时间小于
        	 sb.append("  and  regdate<= ? ");
        	 param.add(etime);
        	  
         }if( maxstime!=null && !maxstime.equals("")){//注册时间小于
        	 sb.append("   and  convert(char(8),regdate,8)<= ? ");
        	 
        	 param.add(maxstime); 
         }


         try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    	    relist=query.getResults(sb.toString(),param.toArray(),CardReader1.class,pagination);
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
    public List initPrint(Search_ViewReader reader)throws Exception{
    	List relist=null;
    	List param=new ArrayList();
    	Engine engine=null;
    	StringBuffer sb=new StringBuffer();

    	sb.append(" select * from (select CardReaderId,CRName,r.ShortName,r.x,r.y,r.z,r.RegDate,r.Mapid,ignoretimes,ignorelocator,locator.shortname sname,locatorignoretimes,r.state,r.modifydate,case r.ground when 1 then '井上设备' when 0 then '井下设备' else '不区分' end as ground,case checkreader when 1 then '是' else '否' end as checkreader,case when useantenna&1>0 then 'A' else '' end + case when useantenna&2>0 then 'B' else '' end + case when useantenna&4>0 then 'C' else '' end as useantenna1,useantenna,case IfAlarm when 1 then '使用' else '不使用'  end as IfAlarm from cardreader as r left join locator on r.ignorelocator=locator.locatorid where cardreaderID<>0 ");
    	sb.append(" ) as QueryTable  where 1=1  ");
    	

    	 String cid=reader.getCid();  
    	 String cname=reader.getCname(); 
    	
    	 String stime=reader.getStime(); //大于
    	 String minstime=reader.getMinstime(); //大于
    	 String maxstime=reader.getMaxstime(); //大于
    	 String etime=reader.getEtime();//小于
    	 
    	 if( cid!=null && !cid.equals("")){//分站号
     		Global  gobal=new Global();
  		    List list=gobal.SuggestCardreaders(cid);
  			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
  				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
  				sb.append(" and rtrim(cardreaderid) like  ? ");
  				param.add(vo.getCardreaderid()+"%");
  			}else{//没有卡号或用户名
  				sb.append(" and   crname like  ?  ");
  				param.add(cid);
  			}
//         	 sb.append(" and rtrim(cardreaderid) like  ? ");
//         	 param.add(cid+"%");
     	 }
//    	 if( cid!=null && !cid.equals("")){//分站号
//        	 sb.append(" and rtrim(cardreaderid) like  ? ");
//        	 param.add(cid+"%");
//         }if( cname!=null && !cname.equals("")){//分站名
//        	 sb.append(" and   crname like  ?  ");
//        	 param.add(cname+"%");
//         }
         if( stime!=null && !stime.equals("")){//注册时间大于
			 sb.append("  and regdate>= ?  ");
			 param.add(stime);
			 
		 }if( minstime!=null && !minstime.equals("")){//注册时间大于
			 sb.append("   and  convert(char(8),regdate,8)>= ? ");
			 
			 param.add(minstime); 
		 }if( etime!=null && !etime.equals("")){//注册时间小于
        	 sb.append("  and  regdate<= ? ");
        	 param.add(etime);
        	  
         }if( maxstime!=null && !maxstime.equals("")){//注册时间小于
        	 sb.append("   and  convert(char(8),regdate,8)<= ? ");
        	 
        	 param.add(maxstime); 
         }
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		relist=query.getResults(sb.toString(),param.toArray(),CardReader1.class);
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

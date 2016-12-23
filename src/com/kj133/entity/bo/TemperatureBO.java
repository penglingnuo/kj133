package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query; 

import com.kj133.entity.Search_Temperature;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.entity.vo.TemperatureVO;
import com.kj133.util.Global;

public class TemperatureBO {
	
	 private final  Logger log=Logger.getLogger(this.getClass());
     public TemperatureBO(){
    	 
     }
     
     /**
      * page is note null
      * */
     @SuppressWarnings("unchecked")
	public List notNull(Search_Temperature temp,String  page)throws Exception{
    	 int pageindex=( Integer.parseInt(page)-1) * 2000;
    	 
    	 List relist=null;
    	 List param=new ArrayList();
    	 Engine engine=null;
    	 StringBuffer sb=new StringBuffer();
    	 String stime=temp.getStartime();
	     String etime=temp.getEndtime();
	     String minstime=temp.getMinstime();
		 String maxstime=temp.getMaxstime();
    	   
    	 String crid=temp.getCardreaderid();
    	 
    	 sb.append(" declare @ds datetime,@de datetime  select @ds=convert(datetime,?),@de=convert(datetime,?)+1   ");
    	 param.add(stime);
         param.add(etime);
    	 sb.append(" declare @d datetime declare @c smallint select top 1 @d=recordtime,@c=cardreaderid from(select top  " + pageindex);
    	 sb.append(" * from v_readerdata where  recordtime>=@ds and recordtime<@de      ");
    	 if( minstime != null && !minstime.equals("") ){
        	 sb.append("  and  convert(char(8),recordtime,8)>=? ");
        	 param.add(minstime);
         }if( maxstime != null && !maxstime.equals("") ){
        	 sb.append(" and convert(char(8),recordtime,8)< ?  ");
        	 param.add(maxstime);
         }
    	 sb.append("  order by recordtime,cardreaderid)as temptable order by recordtime desc,cardreaderid desc");
    	 sb.append(" select a.cardreaderid as cid ,convert(varchar(200),a.Recordtime,20) as times,a.state, a.temperature  as temp,a.cardcount as count,a.interruptcount as inter,a.ignorecount  as ignor, ");
    	 sb.append(" isnull(shortname,'未注册或已删除') as cname from (select top  2000 ");
    	 sb.append(" * from v_readerdata where (recordtime>@d or (recordtime=@d and  ");
    	 sb.append(" cardreaderid>@c)) and  recordtime>=@ds and recordtime<@de ");
         
    	 
         
      	 try{
      		if( minstime != null && !minstime.equals("") ){
           	 sb.append("  and  convert(char(8),recordtime,8)>=? ");
           	 param.add(minstime);
            }if( maxstime != null && !maxstime.equals("") ){
           	 sb.append(" and convert(char(8),recordtime,8)< ?  ");
           	 param.add(maxstime);
            }
      		   sb.append("  )as a left join cardreader as b on a.cardreaderid=b.cardreaderid where 1=1");
      		 if( crid != null && !crid.equals("") ){
            	 Global  gobal=new Global();
     		    List list=gobal.SuggestCardreaders(crid);
     			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
     				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
     				sb.append(" and b.cardreaderid=? ");
     				param.add(vo.getCardreaderid());
     			}else{//没有卡号或用户名
     				sb.append(" and b.shortname=? ");
     				param.add(crid);
     			}
//            	 sb.append("  and cardreaderid = ? ");
//            	 param.add(crid);
             }
      	       
      		   engine=EngineFactory.getEngine("test");
    		   Query query=engine.getQuery();
    		   relist=query.getResults(sb.toString(),param.toArray(),TemperatureVO.class);
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
     public List notNullPrint(Search_Temperature temp,String  page)throws Exception{
    	 int pageindex=( Integer.parseInt(page)-1) * 2000;
    	 
    	 List relist=null;
    	 List param=new ArrayList();
    	 Engine engine=null;
    	 StringBuffer sb=new StringBuffer();
    	 String stime=temp.getStartime();
    	 String etime=temp.getEndtime();
    	 String minstime=temp.getMinstime();
		 String maxstime=temp.getMaxstime();
    	 String crid=temp.getCardreaderid();
    	 
    	 sb.append(" declare @ds datetime,@de datetime  select @ds=convert(datetime,?),@de=convert(datetime,?)+1   ");
    	 param.add(stime);
    	 param.add(etime);
    	 sb.append(" declare @d datetime declare @c smallint select top 1 @d=recordtime,@c=cardreaderid from(select top  " + pageindex);
    	 sb.append(" * from v_readerdata where  recordtime>=@ds and recordtime<@de   ");
    	 if( minstime != null && !minstime.equals("") ){
           	 sb.append("  and  convert(char(8),recordtime,8)>=? ");
           	 param.add(minstime);
            }if( maxstime != null && !maxstime.equals("") ){
           	 sb.append(" and convert(char(8),recordtime,8)< ?  ");
           	 param.add(maxstime);
            }
    	 sb.append("   order by recordtime,cardreaderid)as temptable order by recordtime desc,cardreaderid desc");
    	 sb.append(" select a.cardreaderid as cid ,convert(varchar(200),a.Recordtime,20) as times,a.state, a.temperature  as temp,a.cardcount as count,a.interruptcount as inter,a.ignorecount  as ignor, ");
    	 sb.append(" isnull(shortname,'未注册或已删除') as cname from (select top  2000 ");
    	 sb.append(" * from v_readerdata where (recordtime>@d or (recordtime=@d and  ");
    	 sb.append(" cardreaderid>@c)) and  recordtime>=@ds and recordtime<@de ");
    	 
    	
    	 
//    	 if( crid != null && !crid.equals("") ){
//    		 sb.append("  and cardreaderid = ? ");
//    		 param.add(crid);
//    	 }
    	 try{
    		 if( minstime != null && !minstime.equals("") ){
               	 sb.append("  and  convert(char(8),recordtime,8)>=? ");
               	 param.add(minstime);
                }if( maxstime != null && !maxstime.equals("") ){
               	 sb.append(" and convert(char(8),recordtime,8)< ?  ");
               	 param.add(maxstime);
                }
    		 sb.append("  )as a left join cardreader as b on a.cardreaderid=b.cardreaderid where 1=1");
    		 if( crid != null && !crid.equals("") ){
            	 Global  gobal=new Global();
     		    List list=gobal.SuggestCardreaders(crid);
     			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
     				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
     				sb.append(" and b.cardreaderid=? ");
     				param.add(vo.getCardreaderid());
     			}else{//没有卡号或用户名
     				sb.append(" and b.shortname=? ");
     				param.add(crid);
     			}
//            	 sb.append("  and cardreaderid = ? ");
//            	 param.add(crid);
             }
    		 engine=EngineFactory.getEngine("test");
    		 Query query=engine.getQuery();
    		 relist=query.getResults(sb.toString(),param.toArray(),TemperatureVO.class);
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
      * page is null
      * */
     @SuppressWarnings("unchecked")
	public List isNull(Search_Temperature temp)throws Exception {
    	 List relist=null;
    	 List param=new ArrayList();
    	 Engine engine=null;
    	 StringBuffer sb=new StringBuffer();
    	 String stime=temp.getStartime();
	     String etime=temp.getEndtime();
	     String minstime=temp.getMinstime();
		 String maxstime=temp.getMaxstime();
    	 String crid=temp.getCardreaderid();
    	 
    	 sb.append(" declare @ds datetime,@de datetime  select @ds=convert(datetime,?),@de=convert(datetime,?)+1   ");
    	 sb.append(" select a.cardreaderid as cid ,convert(varchar(20),a.Recordtime,20) as times,a.state, a.temperature  as temp,a.cardcount as count,a.interruptcount as inter , ");
    	 sb.append(" a.ignorecount as ignor ,isnull(shortname,'未注册或已删除') as cname from(select top 2000 * from v_readerdata where  recordtime>=@ds  ");
         sb.append(" and recordtime<@de ");
    	 param.add(stime);
         param.add(etime);
 
    	 
//         if( crid != null && !crid.equals("") ){
//        	 sb.append(" and  cardreaderid = ? ");
//        	 param.add(crid);
//         }
      	 try{
      		if( minstime != null && !minstime.equals("") ){
              	 sb.append("  and  convert(char(8),recordtime,8)>=? ");
              	 param.add(minstime);
               }if( maxstime != null && !maxstime.equals("") ){
              	 sb.append(" and convert(char(8),recordtime,8)< ?  ");
              	 param.add(maxstime);
               }
      		   
      		   sb.append(" order by recordtime,cardreaderid)as a left join cardreader as b on a.cardreaderid=b.cardreaderid where 1=1");
      		 if( crid != null && !crid.equals("") ){
            	 Global  gobal=new Global();
     		    List list=gobal.SuggestCardreaders(crid);
     			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
     				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
     				sb.append(" and b.cardreaderid=? ");
     				param.add(vo.getCardreaderid());
     			}else{//没有卡号或用户名
     				sb.append(" and b.shortname=? ");
     				param.add(crid);
     			}
//            	 sb.append("  and cardreaderid = ? ");
//            	 param.add(crid);
             }
      		   engine=EngineFactory.getEngine("test");
    		   Query query=engine.getQuery();
    		   relist=query.getResults(sb.toString(),param.toArray(),TemperatureVO.class);
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
      * 获取总的页数
      * */
     @SuppressWarnings("unchecked")
	public List allCount(Search_Temperature temp)throws Exception {
    	 List relist=null;
    	 List param=new ArrayList();
    	 Engine engine=null;
    	 StringBuffer sb=new StringBuffer();
    	 String stime=temp.getStartime();
	     String etime=temp.getEndtime();
	     String minstime=temp.getMinstime();
		 String maxstime=temp.getMaxstime();
    	 sb.append(" declare @ds datetime,@de datetime select @ds=convert(datetime,?),@de=convert(datetime,?)+1 ");
    	 sb.append(" select count(*) as allcount  from v_readerdata where  recordtime>=@ds and recordtime<@de ");
    	 
    	 param.add(stime);
         param.add(etime);
    	 if(temp.getCardreaderid() != null && !temp.getCardreaderid().equals("")){
    		 Global  gobal=new Global();
 		    List list=gobal.SuggestCardreaders(temp.getCardreaderid());
 			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
 				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
 				sb.append(" and cardreaderid=? ");
 				param.add(vo.getCardreaderid());
 			}else{//没有卡号或用户名
 				sb.append(" and cardreaderid=? ");
 				param.add(1234567);
 			}
    	 }
    	 try{
    		 if( minstime != null && !minstime.equals("") ){
               	 sb.append("  and  convert(char(8),recordtime,8)>=? ");
               	 param.add(minstime);
                }if( maxstime != null && !maxstime.equals("") ){
               	 sb.append(" and convert(char(8),recordtime,8)< ?  ");
               	 param.add(maxstime);
                }
    		   engine=EngineFactory.getEngine("test");
    		   Query query=engine.getQuery();
    	       relist=query.getResults(sb.toString(),param.toArray(),TemperatureVO.class);
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

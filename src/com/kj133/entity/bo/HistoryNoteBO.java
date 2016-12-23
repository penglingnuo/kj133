package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_HistoryNote;
import com.kj133.entity.vo.HistoryNoteVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class HistoryNoteBO {
  
	private final Logger log=Logger.getLogger(this.getClass());
	public HistoryNoteBO(){
	}
	
	/**
	 * 历史数据查询
	 *page is null
	 * */
	@SuppressWarnings("unchecked")
	public List first(Search_HistoryNote note)throws Exception{
		List relist=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		Engine engine=null;
		sb.append(" declare @ds datetime,@de datetime ");
		sb.append(" select @ds=convert(datetime,?),@de=convert(datetime,?)+1 ");
		sb.append(" select a.cardreaderid as cid,a.Recordtime as times,a.state as stat,a.temperature ");
		sb.append(" as temper, a.cardcount as ccount, ");
		sb.append(" isnull (shortname,'未注册或已删除') as name,info as info from (select top 2000 v_readerdata.*,  ");
		sb.append(" cardreader.shortname from v_readerdata left join cardreader on v_readerdata.cardreaderid=cardreader.cardreaderid where ");
		sb.append(" recordtime>=@ds and recordtime<@de  ");
		    
			String radio=note.getRad(); //radio   
			String[] info=note.getInfo();//select
			String cid=note.getCid(); 
		    String stime=note.getStime(); 
		    String minstime=note.getMinstime(); 
		    String maxstime=note.getMaxstime(); 
			  
		 
	   	    String etime=note.getEtime();
			

			param.add(stime);
			param.add(etime);
			
			if( cid!= null && !cid.equals("")){
				Global  gobal=new Global();
			    List list=gobal.SuggestCardreaders(cid);
				if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sb.append(" and v_readerdata.cardreaderid=? ");
					param.add(vo.getCardreaderid());
				}else{//没有卡号或用户名
					sb.append(" and cardreader.shortname=? ");
					param.add(cid);
				}
//				sb.append(" and v_readerdata.cardreaderid = ? ");
//				param.add(cid);
			}if( radio != null && !radio.equals("")){
				  int j=0; 
				  if( radio.equals("radand"))
				    {
			   	     if( info != null && info.length>0 ){
			   	    	 for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					                }
					    	   sb.append("  and  v_readerdata.state = ? ");
						       param.add(String.valueOf(j));
			   	          }
				   }else{
				   	     if( info != null && info.length>0 ){  
				   	    	for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					        }
					    	 sb.append(" and  v_readerdata.state&?>0 ");
						     param.add(String.valueOf(j));
				   	     }
				   }
	   	    }
			
			try{
				if( minstime!= null && !minstime.equals("")){
					sb.append(" and  convert(char(8), recordtime,8)>=? ");
					param.add(minstime);
				}if( maxstime!= null && !maxstime.equals("")){
					sb.append(" and  convert(char(8),recordtime,8)<? ");
					param.add(maxstime);
				}
			    
			     sb.append("  order by recordtime,v_readerdata.cardreaderid )as a,readeralarminfo as c where a.state=c.state   " );
				 
				 engine=EngineFactory.getEngine("test");
				 Query query=engine.getQuery();
			     relist=query.getResults(sb.toString(),param.toArray(),HistoryNoteVO.class);
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
	 * page is not null
	 * */
	@SuppressWarnings("unchecked")
	public List next(Search_HistoryNote note,String page)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 
		 String radio=note.getRad(); //radio   
		String[] info=note.getInfo();//select
		String cid=note.getCid(); 
	    String stime=note.getStime(); 
	    String minstime=note.getMinstime(); 
	    String maxstime=note.getMaxstime(); 
		 
	   	    String etime=note.getEtime();
		 sb.append(" declare @ds datetime,@de datetime  select @ds=convert(datetime,?),@de=convert(datetime,?)+1  ");
		 param.add(stime);
		 param.add(etime);
		 sb.append(" declare @d datetime declare @c smallint select top 1 @d=recordtime,@c=cardreaderid from(select top  ");
		 sb.append((Integer.parseInt(page)-1)*2000);
		 sb.append(" * from v_readerdata where  recordtime>=@ds and recordtime<@de    ");
		 if( minstime!= null && !minstime.equals("")){
				sb.append(" and  convert(char(8),recordtime,8)>=? ");
				param.add(minstime);
		 }if( maxstime!= null && !maxstime.equals("")){
				sb.append(" and convert(char(8),recordtime,8)< ?  ");
				param.add(maxstime);
		 }
		 sb.append("  order by recordtime,cardreaderid)as temptable order by recordtime desc, ");
		 sb.append(" cardreaderid desc select a.cardreaderid as cid,a.Recordtime as times,a.state as stat ,a.temperature as temper,a.cardcount ");
		 sb.append(" as ccount,isnull(shortname,'分站名称') as name,info as info  ");
		 sb.append(" from (select top 2000 v_readerdata.*,cardreader.shortname from v_readerdata left join cardreader on v_readerdata.cardreaderid= ");
		 sb.append(" cardreader.cardreaderid where (recordtime>@d or (recordtime=@d and v_readerdata.cardreaderid>@c)) and  recordtime>=@ds and recordtime<@de ");
		 
		
			if( cid!= null && !cid.equals("")){
				Global  gobal=new Global();
			    List list=gobal.SuggestCardreaders(cid);
				if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sb.append(" and v_readerdata.cardreaderid=? ");
					param.add(vo.getCardreaderid());
				}else{//没有卡号或用户名
					sb.append(" and cardreader.shortname=? ");
					param.add(cid);
				}
//				sb.append(" and v_readerdata.cardreaderid = ? ");
//				param.add(cid);
			}if( radio != null && !radio.equals("")){
				  int j=0; 
				  if( radio.equals("radand"))
				    {
			   	     if( info != null && info.length>0 ){
			   	    	 for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					                }
					    	   sb.append("  and  v_readerdata.state = ? ");
						       param.add(String.valueOf(j));
			   	          }
				   }else{
				   	     if( info != null && info.length>0 ){  
				   	    	for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					        }
					    	 sb.append(" and  v_readerdata.state&?>0 ");
						     param.add(String.valueOf(j));
				   	     }
				   }
	   	    }
		 try{
			 if( minstime!= null && !minstime.equals("")){
					sb.append(" and  convert(char(8),recordtime,8)>=? ");
					param.add(minstime);
			 }if( maxstime!= null && !maxstime.equals("")){
					sb.append(" and convert(char(8),recordtime,8)< ?  ");
					param.add(maxstime);
			 }
			  sb.append("   )as a,readeralarminfo as c where a.state=c.state ");
			  
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
		      relist=query.getResults(sb.toString(),param.toArray(),HistoryNoteVO.class);
		      engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.commit();
		 }
		 return relist;
	}
	
	
	 /**
	  * 总的记录数
	  * */
	 @SuppressWarnings("unchecked")
	public List getAllcount(Search_HistoryNote note)throws Exception {
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" declare @ds datetime,@de datetime  select @ds=convert(datetime,?), @de=convert(datetime,?)+1  ");
		 sb.append(" select count(*) as count  from v_readerdata left join cardreader on v_readerdata.cardreaderid=cardreader.cardreaderid where ");
		 sb.append(" ( recordtime>=@ds and recordtime<@de  ");
		    String radio=note.getRad(); //radio   
			String[] info=note.getInfo();//select
			String cid=note.getCid(); 
		    String stime=note.getStime(); 
		    String minstime=note.getMinstime(); 
		    String maxstime=note.getMaxstime(); 
		 
	   	    String etime=note.getEtime();
			

			param.add(stime);
			param.add(etime);
			
			if( cid!= null && !cid.equals("")){
				Global  gobal=new Global();
			    List list=gobal.SuggestCardreaders(cid);
				if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sb.append(" and v_readerdata.cardreaderid=? ");
					param.add(vo.getCardreaderid());
				}else{//没有卡号或用户名
					sb.append(" and cardreader.shortname=? ");
					param.add(cid);
				}
			}if( radio != null && !radio.equals("")){
				  int j=0; 
				  if( radio.equals("radand"))
				    {
			   	     if( info != null && info.length>0 ){
			   	    	 for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					                }
					    	   sb.append("  and  v_readerdata.state = ? ");
						       param.add(String.valueOf(j));
			   	          }
				   }else{
				   	     if( info != null && info.length>0 ){  
				   	    	for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					        }
					    	 sb.append(" and  v_readerdata.state&?>0 ");
						     param.add(String.valueOf(j));
				   	     }
				   }
	   	    }
		 try{
			 if( minstime!= null && !minstime.equals("")){
					sb.append(" and  convert(char(8),recordtime,8)>=? ");
					param.add(minstime);
			 }if( maxstime!= null && !maxstime.equals("")){
					sb.append(" and convert(char(8),recordtime,8)< ?  ");
					param.add(maxstime);
			 }
			 sb.append("  ) ");
			 
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
		     relist=query.getResults(sb.toString(),param.toArray(),HistoryNoteVO.class);
		     engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.commit();
		 }
		 return relist;
	 }
}

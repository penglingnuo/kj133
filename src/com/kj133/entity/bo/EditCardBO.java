package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_eidtCard;
import com.kj133.entity.vo.EditCardVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class EditCardBO {
	 
	 private Logger log=Logger.getLogger(this.getClass());
	 public EditCardBO(){
		 
	 }
	 
	 /**
	  * 编辑卡
	  * 获取查询信息
	  */
	 @SuppressWarnings("unchecked")
	public List init(Search_eidtCard ser_card,Pagination pagin)throws Exception{
    	 Engine engine=null;
    	 List param=new ArrayList();
    	 StringBuffer sb=new StringBuffer();
    	 Query query=null;
    	 List relist=null;
/*    	 sb.append(" select * from (select a.CardID as cardid,a.CardMode as cardmode,isnull(b.worktype,'其他') as " 
    		  +" worktype,isnull(b.[name],'未绑定') as name,isnull(b.[Group],'未绑定') as gro,a.CardState  " 
    		  +" cardstate,convert(varchar(200),a.RegDate,20) as regdate  from recog as a left join staffer  " 
    		  +" as b on a.cardid=b.cardid where a.CardID<>0 ) as QueryTable where 1=1 ");*/
    	 sb.append(" select * from (select a.CardID,a.CardMode,isnull(b.worktype,'其他') as worktype,isnull(b.[name],'未绑定') as name,isnull(b.[Group],'未绑定') as gro,a.CardState,a.RegDate,a.modifydate from recog as a left join staffer as b on a.cardid=b.cardid where a.CardID<>0 ");
    	 sb.append("  ) as QueryTable  where 1=1 ");
    	  

    	 String cid=ser_card.getCardid();  
    	 String cname=ser_card.getUsername();  
    	 String type=ser_card.getWorktype();  
    	
    	 String stime=ser_card.getStime();//大于 当添加成功后，转到这里来执行，stime为null,所以给他个初始值
    	 String etime=ser_card.getEtime();//小于
    	 String minstime=ser_card.getMinstime();//小于
    	 String maxstime=ser_card.getMaxstime();//小于
 
    	 
    	if(cid != null && !cid.equals("")){//卡号不为空
    		Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(cid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and rtrim(cardid) like ?  ");
				param.add(cid);
			}else{//没有卡号或用户名
				sb.append(" and name like ? ");
				param.add(cid);
			}
//            sb.append( "  and rtrim(cardid) like ? " );
//            param.add(cid+"%");
    	}
//    	if(cname !=null && !cname.equals("")){//姓名不为空
//    		sb.append( " and name like ? " );
//    		param.add(cname+"%");
//    	}
    	if(type !=null && !type.equals("")){//类型不为空
    		sb.append( " and worktype = ? " );
    		param.add(type);
    	}if(stime !=null && !stime.equals("")){
    		sb.append( " and regdate >= ?  " );
    		param.add(stime);
    		
    	}if(minstime !=null && !minstime.equals("")){
    		sb.append( "  and  convert(char(8),regdate,8)>=? " );
    		
    		param.add(minstime);
    	}if(etime !=null && !etime.equals("")){
    		sb.append( " and regdate <= ?  ");
    		param.add(etime);
    		
    	}if(maxstime !=null && !maxstime.equals("")){
    		sb.append( "  and  convert(char(8),regdate,8)<=? ");
    		param.add(maxstime);
    	}
 	
    	try{
    		    sb.append(" order by cardid ");
    	    	engine=EngineFactory.getEngine("test");
    	    	query=engine.getQuery();
    	    	relist=query.getResults(sb.toString(),param.toArray(),EditCardVO.class,pagin);
    	    	//System.out.println("sb="+sb);
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
	 public List initPrint(Search_eidtCard ser_card)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 Query query=null;
		 List relist=null;
    	 sb.append(" select * from (select a.CardID,a.CardMode,isnull(b.worktype,'其他') as worktype,isnull(b.[name],'未绑定') as name,isnull(b.[Group],'未绑定') as gro,a.CardState,a.RegDate,a.modifydate from recog as a left join staffer as b on a.cardid=b.cardid where a.CardID<>0 ");
    	 sb.append("  ) as QueryTable  where 1=1 ");
    	  

    	 String cid=ser_card.getCardid();  
    	 String cname=ser_card.getUsername();  
    	 String type=ser_card.getWorktype();  
    	
    	 String stime=ser_card.getStime();//大于 当添加成功后，转到这里来执行，stime为null,所以给他个初始值
    	 String etime=ser_card.getEtime();//小于
    	 String minstime=ser_card.getMinstime();//小于
    	 String maxstime=ser_card.getMaxstime();//小于
 
    	 if(cid != null && !cid.equals("")){//卡号不为空
     		Global  gobal=new Global();
  		    List list=gobal.SuggestEmployees(cid);
 			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
 				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
 				sb.append(" and rtrim(cardid) like ?  ");
 				param.add(vo.getStafferid());
 			}else{//没有卡号或用户名
 				sb.append(" and name like ? ");
 				param.add(cid);
 			}
//             sb.append( "  and rtrim(cardid) like ? " );
//             param.add(cid+"%");
     	}
//    	if(cid != null && !cid.equals("")){//卡号不为空
//            sb.append( "  and rtrim(cardid) like ? " );
//            param.add(cid+"%");
//    	}if(cname !=null && !cname.equals("")){//姓名不为空
//    		sb.append( " and name like ? " );
//    		param.add(cname+"%");
//    	}
    	if(type !=null && !type.equals("")){//类型不为空
    		sb.append( " and worktype = ? " );
    		param.add(type);
    	}if(stime !=null && !stime.equals("")){
    		sb.append( " and regdate >= ?  " );
    		param.add(stime);
    		
    	}if(minstime !=null && !minstime.equals("")){
    		sb.append( "  and  convert(char(8),regdate,8)>=? " );
    		
    		param.add(minstime);
    	}if(etime !=null && !etime.equals("")){
    		sb.append( " and regdate <= ?  ");
    		param.add(etime);
    		
    	}if(maxstime !=null && !maxstime.equals("")){
    		sb.append( "  and  convert(char(8),regdate,8)<=? ");
    		
    		param.add(maxstime);
    	}
		 try{
			 sb.append(" order by cardid ");
			 engine=EngineFactory.getEngine("test");
			 query=engine.getQuery();
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
}

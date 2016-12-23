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
	  * �༭��
	  * ��ȡ��ѯ��Ϣ
	  */
	 @SuppressWarnings("unchecked")
	public List init(Search_eidtCard ser_card,Pagination pagin)throws Exception{
    	 Engine engine=null;
    	 List param=new ArrayList();
    	 StringBuffer sb=new StringBuffer();
    	 Query query=null;
    	 List relist=null;
/*    	 sb.append(" select * from (select a.CardID as cardid,a.CardMode as cardmode,isnull(b.worktype,'����') as " 
    		  +" worktype,isnull(b.[name],'δ��') as name,isnull(b.[Group],'δ��') as gro,a.CardState  " 
    		  +" cardstate,convert(varchar(200),a.RegDate,20) as regdate  from recog as a left join staffer  " 
    		  +" as b on a.cardid=b.cardid where a.CardID<>0 ) as QueryTable where 1=1 ");*/
    	 sb.append(" select * from (select a.CardID,a.CardMode,isnull(b.worktype,'����') as worktype,isnull(b.[name],'δ��') as name,isnull(b.[Group],'δ��') as gro,a.CardState,a.RegDate,a.modifydate from recog as a left join staffer as b on a.cardid=b.cardid where a.CardID<>0 ");
    	 sb.append("  ) as QueryTable  where 1=1 ");
    	  

    	 String cid=ser_card.getCardid();  
    	 String cname=ser_card.getUsername();  
    	 String type=ser_card.getWorktype();  
    	
    	 String stime=ser_card.getStime();//���� ����ӳɹ���ת��������ִ�У�stimeΪnull,���Ը�������ʼֵ
    	 String etime=ser_card.getEtime();//С��
    	 String minstime=ser_card.getMinstime();//С��
    	 String maxstime=ser_card.getMaxstime();//С��
 
    	 
    	if(cid != null && !cid.equals("")){//���Ų�Ϊ��
    		Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(cid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and rtrim(cardid) like ?  ");
				param.add(cid);
			}else{//û�п��Ż��û���
				sb.append(" and name like ? ");
				param.add(cid);
			}
//            sb.append( "  and rtrim(cardid) like ? " );
//            param.add(cid+"%");
    	}
//    	if(cname !=null && !cname.equals("")){//������Ϊ��
//    		sb.append( " and name like ? " );
//    		param.add(cname+"%");
//    	}
    	if(type !=null && !type.equals("")){//���Ͳ�Ϊ��
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
    	 sb.append(" select * from (select a.CardID,a.CardMode,isnull(b.worktype,'����') as worktype,isnull(b.[name],'δ��') as name,isnull(b.[Group],'δ��') as gro,a.CardState,a.RegDate,a.modifydate from recog as a left join staffer as b on a.cardid=b.cardid where a.CardID<>0 ");
    	 sb.append("  ) as QueryTable  where 1=1 ");
    	  

    	 String cid=ser_card.getCardid();  
    	 String cname=ser_card.getUsername();  
    	 String type=ser_card.getWorktype();  
    	
    	 String stime=ser_card.getStime();//���� ����ӳɹ���ת��������ִ�У�stimeΪnull,���Ը�������ʼֵ
    	 String etime=ser_card.getEtime();//С��
    	 String minstime=ser_card.getMinstime();//С��
    	 String maxstime=ser_card.getMaxstime();//С��
 
    	 if(cid != null && !cid.equals("")){//���Ų�Ϊ��
     		Global  gobal=new Global();
  		    List list=gobal.SuggestEmployees(cid);
 			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
 				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
 				sb.append(" and rtrim(cardid) like ?  ");
 				param.add(vo.getStafferid());
 			}else{//û�п��Ż��û���
 				sb.append(" and name like ? ");
 				param.add(cid);
 			}
//             sb.append( "  and rtrim(cardid) like ? " );
//             param.add(cid+"%");
     	}
//    	if(cid != null && !cid.equals("")){//���Ų�Ϊ��
//            sb.append( "  and rtrim(cardid) like ? " );
//            param.add(cid+"%");
//    	}if(cname !=null && !cname.equals("")){//������Ϊ��
//    		sb.append( " and name like ? " );
//    		param.add(cname+"%");
//    	}
    	if(type !=null && !type.equals("")){//���Ͳ�Ϊ��
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

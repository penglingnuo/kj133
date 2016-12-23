package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_AreaStick;
import com.kj133.entity.vo.AreaStickVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class AreaStickBO {
	
	    private final Logger log=Logger.getLogger(this.getClass());
		public  AreaStickBO(){
		   
	   }
	   /**
	    * ����ͣ����Ϣ��ѯ
	    * */
	     @SuppressWarnings("unchecked")
		public List init(Search_AreaStick stick,Pagination pagin)throws Exception{
	    	 Engine engine=null;
	    	 List relist=null;
	    	 List param=new ArrayList();
	    	
	    	 String cid=stick.getCid();  
	    	 String sid=stick.getSid(); 
             String gro=stick.getGro();  
             String type=stick.getType();  
             
             String minstime=stick.getMinstime();
             String maxstime=stick.getMaxstime();
             String stime=stick.getStime();//��ȡע��ʱ�����
     		 String etime=stick.getEtime();   //��ȡע��ʱ��С��

     		 
	    	 StringBuffer sb=new StringBuffer();
	    	 sb.append(" select a.stafferID as sid,a.cardreaderid as cid,a.inTime as inttim,a.inlocator as inloca, ");
	    	 sb.append(" a.outtime as outcid,a.outlocator as outloca,startendinfo.info as outrea,a.stayInterval as ");
	    	 sb.append(" staty,a.Interrupttimes as times,a.ReinTime as resinti,case a.[Name] when '' then 'δ��'  ");
	    	 sb.append(" else a.[Name] end as username,case a.worktype when '' then '����' else a.worktype end as type, ");
	    	 sb.append(" case a.[group] when '' then 'δ��' else a.[group]end as gro,isnull(shortname,'δע�����ɾ��') ");
	    	 sb.append(" as shname from(select   v_stayinterval.*,[Name],worktype,[group] from v_stayinterval,staffer where  ");
	    	 sb.append(" v_stayinterval.stafferID=staffer.stafferID and ( outtime>= ? and intime<convert(datetime,?)+1 and intime>dateadd(dd,-3,? )  ");
	    	 sb.append("  ");
      		 param.add(stime);
      		 param.add(etime);
      		 param.add(stime);
             
      		 if( sid != null && !sid.equals("")){//Ա����
      			Global  gobal=new Global();
     		    List list2=gobal.SuggestEmployees(sid);
    			if(list2.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
    				SuggestEmployeeVO vo=(SuggestEmployeeVO)list2.get(0);
    				sb.append(" and rtrim(v_stayinterval.stafferid)=? ");
    				param.add(vo.getStafferid());
    			}else{//û�п��Ż��û���
    				sb.append(" and [name]=? ");
    				param.add(sid);
    			}
//      			 sb.append(" and  rtrim(v_stayinterval.stafferid) = ?  ");
//      			 param.add(sid);
      		 }if( gro != null && !gro.equals("")){//����
      			 sb.append(" and  staffer.[group] = ?  ");
      			 param.add(gro);
      		 }if( type != null && !type.equals("")){//ְ������
      			 sb.append(" and  staffer.worktype =? ");
      			 param.add(type);
      		 }if( minstime != null && !minstime.equals("")){//ְ������
      			 sb.append(" and  convert(char(8),outtime,8)>=? ");
      			 param.add(minstime);
      		 }if( maxstime != null && !maxstime.equals("")){//ְ������
      			 sb.append(" and  convert(char(8),intime,8)<? ");
      			 param.add(maxstime);
      		 }
      		 
      		 
      		 sb.append(" ))as a left join cardreader as b on a.cardreaderid=b.cardreaderid left join startendinfo on startendinfo.state=a.OutReason  where 1=1  ");
      		if( cid != null && !cid.equals("")){//��վ��
      			Global  gobal=new Global();
     		    List list1=gobal.SuggestCardreaders(cid);
    			if(list1.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
    				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
    				sb.append(" and rtrim(b.cardreaderid)=? ");
    				param.add(vo.getCardreaderid());
    			}else{//û�п��Ż��û���
    				sb.append(" and b.shortname=? ");
    				param.add(cid);
    			}
//      			sb.append(" and  rtrim(cardreaderid) = ?  ");
//      			param.add(cid);
      		 }
      		sb.append(" order by a.intime,a.cardid,a.cardreaderid ");
      		 
	    	 try{
	    		 engine=EngineFactory.getEngine("test");
	    		 Query query=engine.getQuery();
	             relist=query.getResults(sb.toString(),param.toArray(),AreaStickVO.class,pagin);
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
	     public List initPrint(Search_AreaStick stick)throws Exception{
	    	 Engine engine=null;
	    	 List relist=null;
	    	 List param=new ArrayList();
	    	 
	    	 String cid=stick.getCid();  
	    	 String sid=stick.getSid(); 
	    	 String gro=stick.getGro();  
	    	 String type=stick.getType();  
	    	 
             String minstime=stick.getMinstime();
             String maxstime=stick.getMaxstime();
             String stime=stick.getStime();//��ȡע��ʱ�����
     		 String etime=stick.getEtime();   //��ȡע��ʱ��С��

     		 
	    	 StringBuffer sb=new StringBuffer();
	    	 sb.append(" select a.stafferID as sid,a.cardreaderid as cid,a.inTime as inttim,a.inlocator as inloca, ");
	    	 sb.append(" a.outtime as outcid,a.outlocator as outloca,startendinfo.info as outrea,a.stayInterval as ");
	    	 sb.append(" staty,a.Interrupttimes as times,a.ReinTime as resinti,case a.[Name] when '' then 'δ��'  ");
	    	 sb.append(" else a.[Name] end as username,case a.worktype when '' then '����' else a.worktype end as type, ");
	    	 sb.append(" case a.[group] when '' then 'δ��' else a.[group]end as gro,isnull(shortname,'δע�����ɾ��') ");
	    	 sb.append(" as shname from(select   v_stayinterval.*,[Name],worktype,[group] from v_stayinterval,staffer where  ");
	    	 sb.append(" v_stayinterval.stafferID=staffer.stafferID and ( outtime>= ? and intime<convert(datetime,?)+1 and intime>dateadd(dd,-3,? )  ");
	    	 sb.append("  ");
      		 param.add(stime);
      		 param.add(etime);
      		 param.add(stime);
             
      		if( sid != null && !sid.equals("")){//Ա����
      			Global  gobal=new Global();
     		    List list2=gobal.SuggestEmployees(sid);
    			if(list2.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
    				SuggestEmployeeVO vo=(SuggestEmployeeVO)list2.get(0);
    				sb.append(" and rtrim(v_stayinterval.stafferid)=? ");
    				param.add(vo.getStafferid());
    			}else{//û�п��Ż��û���
    				sb.append(" and [name]=? ");
    				param.add(sid);
    			}
//      			 sb.append(" and  rtrim(v_stayinterval.stafferid) = ?  ");
//      			 param.add(sid);
      		 }if( gro != null && !gro.equals("")){//����
      			 sb.append(" and  staffer.[group] = ?  ");
      			 param.add(gro);
      		 }if( type != null && !type.equals("")){//ְ������
      			 sb.append(" and  staffer.worktype =? ");
      			 param.add(type);
      		 }if( minstime != null && !minstime.equals("")){//ְ������
      			 sb.append(" and  convert(char(8),outtime,8)>=? ");
      			 param.add(minstime);
      		 }if( maxstime != null && !maxstime.equals("")){//ְ������
      			 sb.append(" and  convert(char(8),intime,8)<? ");
      			 param.add(maxstime);
      		 }
      		 
      		 
      		 sb.append(" ))as a left join cardreader as b on a.cardreaderid=b.cardreaderid left join startendinfo on startendinfo.state=a.OutReason  where 1=1  ");
      		if( cid != null && !cid.equals("")){//��վ��
      			Global  gobal=new Global();
     		    List list1=gobal.SuggestCardreaders(cid);
    			if(list1.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
    				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
    				sb.append(" and rtrim(b.cardreaderid)=? ");
    				param.add(vo.getCardreaderid());
    			}else{//û�п��Ż��û���
    				sb.append(" and b.shortname=? ");
    				param.add(cid);
    			}
//      			sb.append(" and  rtrim(cardreaderid) = ?  ");
//      			param.add(cid);
      		 }
      		sb.append(" order by a.intime,a.cardid,a.cardreaderid ");
	    	 try{
	    		 
	    		 engine=EngineFactory.getEngine("test");
	    		 Query query=engine.getQuery();
	    		 relist=query.getResults(sb.toString(),param.toArray(),AreaStickVO.class);
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

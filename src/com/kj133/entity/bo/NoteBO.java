package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_Note;
import com.kj133.entity.vo.NoteVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class NoteBO {
	
	private final Logger log=Logger.getLogger(this.getClass());
    public NoteBO(){
    	
    }
    
    /**
     *  
     * page==null(���ѯ��ʱ��page == null)
     * */
      @SuppressWarnings("unchecked")
	public List first(Search_Note note)throws Exception {
    	List relist=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	Engine engine=null;

 		sb.append("  declare @ds datetime,@de datetime,@dss datetime   ");
// 		sb.append(" select @ds=convert(datetime,?),@de=convert(datetime,?)+1,@dss=@ds-3  ");
	    sb.append(" select @ds=convert(datetime,?),@de=convert(datetime,?),@dss=@ds-3  ");
		sb.append(" select temptable1.cardid as cid,temptable1.cardreaderid as rid,temptable1.locatorid as lid,temptable1.mapid as mid,temptable1.state ");
    	sb.append(" as sta,temptable1.starttime   as stime,temptable1.endtime as etime,temptable1.stayinterval ");
    	sb.append(" as stay,  temptable1.Endby as enb,temptable1.Startby as sby,temptable1.Antenna ");
    	sb.append(" as ant, isnull(temptable1.[Name],'δ��') as   name,isnull(temptable1.worktype,'����') as type,");
    	sb.append(" isnull(temptable1.[group],'δ��') as gro,  temptable1.info as info,temptable1.shortname ");
    	sb.append(" as sname,temptable1.����ԭ�� as overinfo,  isnull(locator.shortname,'δע�����ɾ��') as ");
    	sb.append(" lname from( select  a.*,e.info as ����ԭ��,   isnull(shortname,'δע�����ɾ��') as shortname,");
    	sb.append(" d.info from(select top 2000 v_locatedata.*,   [Name],worktype,[group] from v_locatedata  left ");
    	sb.append(" join staffer on v_locatedata.stafferid=   staffer.stafferid where ( starttime>=@ds and endtime<=@de  " );
//        sb.append(" join staffer on v_locatedata.stafferid=   staffer.stafferid where ( endtime>=@ds ");
//        sb.append(" and starttime<@de and starttime>@dss  " );
       
   	    String stime=note.getStime();//��ȡע��ʱ���
//		String s1=stime.substring(0,10);  
//		String s2=stime.substring(11,stime.length()); 
//   
		String etime=note.getEtime();    //��ȡע��ʱ��С��
//		String e1=etime.substring(0,10);  
//		String e2=etime.substring(11,etime.length());
//		param.add(s1);
//		param.add(e1);
		param.add(stime);
		param.add(etime);
	
    	String cid=note.getCid();
    	String lid=note.getLid();
    	String sid=note.getSid();
    	String gro=note.getGro();
    	String type=note.getType();
    	String radio=note.getRad();//radio
        String[] info=note.getInfo();//select
       
        
		if(  sid != null && !sid.equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and staffer.stafferid=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and staffer.[name]=? ");
				param.add(sid);
			}
//			sb.append(" and  staffer.stafferid= ? ");
//		    param.add(sid);
		    
		}if( gro != null && !gro.equals("")){
			sb.append("  and staffer.[group]= ? ");
			param.add(gro);
			
		}if( type != null && !type.equals("")){
			sb.append("  and staffer.worktype = ? ");
			param.add(type);
		}if( radio != null && !radio.equals("")){
			  int j=0; 
			 if( radio.equals("radand"))
			   {
		   	     if( info != null && info.length>0 ){
		   	    	 for(int i=0;i<info.length;i++){
				            j=j+Integer.parseInt(info[i]);
				                }
				    	   sb.append(" and v_locatedata.state  = ? ");
					       param.add(String.valueOf(j));
		   	          }
			   }else{
			   	     if( info != null && info.length>0 ){  
			   	    	for(int i=0;i<info.length;i++){
				            j=j+Integer.parseInt(info[i]);
				        }
				    	 sb.append(" and v_locatedata.state&? >0 ");
					     param.add(String.valueOf(j));
			   	     }
			   }
   	    }try{
   	     
//	   	    sb.append(" and  convert  (char(8),endtime,8)>= ?  and  convert(char(8),starttime,8)< ? ) ) ");
//	   	    param.add(s2);
//	   	    param.add(e2);
	 		sb.append(") ) as a left join cardreader as b on a.cardreaderid=b.cardreaderid,alarminfo as   ");
	 		sb.append(" d,startendinfo as e where a.state=d.state and a.endby=e.state " );
	 		if( cid !=null && !cid.equals("")){//��վ��
				Global  gobal=new Global();
			    List list=gobal.SuggestCardreaders(cid);
				if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sb.append(" and b.cardreaderid=? ");
					param.add(vo.getCardreaderid());
				}else{//û�п��Ż��û���
					sb.append(" and b.shortname=? ");
					param.add(cid);
				}
//				sb.append(" and  v_locatedata.cardreaderid= ? ");
//				param.add(cid);
				
			}
	 		sb.append("  ) as temptable1 left join locator on temptable1.locatorid=locator.locatorid ");
	 		if( lid!=null && !lid.equals("")){//��λ����
				Global  gobal=new Global();
	 		    List list1=gobal.SuggestLocators(lid);
	 		    
				if(list1.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
					
					sb.append(" where locator.locatorid= ? ");
//					sb.append(" and v_locatedata.locatorid= ? ");
					param.add(vo.getLocatorid());
				}else{//û�п��Ż��û���
					sb.append(" where locator.shortname=? ");
					param.add(lid);
				}
//				sb.append(" and v_locatedata.locatorid= ? ");
//				param.add(lid);
				
			}
	        engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    	    relist=query.getResults(sb.toString(),param.toArray(),NoteVO.class);
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
       * page is not nulll
       * */
    @SuppressWarnings("unchecked")
	public List next(Search_Note note,String page)throws Exception {
    	List relist=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	Engine engine=null;
//    	String stime=note.getStime();//��ȡע��ʱ���
//    	String s1=stime.substring(0,10);  
//    	String s2=stime.substring(11,stime.length()); 
//    	
//    	String etime=note.getEtime();//��ȡע��ʱ��С��
//    	String e1=etime.substring(0,10);  
//    	String e2=etime.substring(11,etime.length());
    	
        String stime=note.getStime();//��ȡע��ʱ���
		String etime=note.getEtime();//��ȡע��ʱ��С��
		
		  sb.append("  declare @ds datetime,@de datetime,@dss datetime   ");
//		  sb.append(" select @ds=convert(datetime,?),@de=convert(datetime,?)+1,@dss=@ds-3  ");
	      sb.append(" select @ds=convert(datetime,?),@de=convert(datetime,?),@dss=@ds-3  ");
		  sb.append(" declare @d datetime declare @c smallint declare @cr smallint select top 1 @d=starttime,@c=cardid,@cr=cardreaderid from(select top ");
		  sb.append((Integer.parseInt(page)-1)*2000);
		  sb.append(" v_locatedata.*,[Name],worktype,[group] from v_locatedata left join staffer on v_locatedata.stafferid=staffer.stafferid  where  ");
		  sb.append(" ( endtime>=@ds and starttime<@de and starttime>@dss ");
		  sb.append(" ) order by starttime,v_locatedata.cardid,v_locatedata.cardreaderid)as temptable order by starttime desc,cardid desc, ");
		  sb.append(" temptable.cardreaderid desc select temptable1.cardid as cid,temptable1.cardreaderid as rid,temptable1.locatorid   as lid,temptable1.mapid ");
		  sb.append(" as mid,temptable1.state  as sta,temptable1.starttime  ");
		  sb.append(" as stime,temptable1.endtime as etime,temptable1.stayinterval  as stay,  temptable1.endby as enb,temptable1.startby as sby, ");
		  sb.append(" temptable1.antenna  as ant, isnull(temptable1.[name],'δ��') as   name,isnull(temptable1.worktype,'����') as type, ");
		  sb.append(" isnull(temptable1.[group],'δ��') as gro,  temptable1.info as info,temptable1.shortname  as sname,temptable1.����ԭ�� as overinfo,  ");
		  sb.append(" isnull(locator.shortname,'δע�����ɾ��') as  lname from( select  a.*,e.info as ����ԭ��,   isnull(shortname,'δע�����ɾ��') as ");
		  sb.append(" shortname, d.info from(select top 2000 v_locatedata.*,[Name],worktype,[group] from v_locatedata  left join staffer on v_locatedata.stafferid ");
		  sb.append(" =staffer.stafferid where (starttime>@d or (starttime=@d and v_locatedata.cardid>@c) or (starttime=@d and v_locatedata.cardid=@c and ");
		  sb.append(" v_locatedata.cardreaderid>@cr)) and  endtime>=@ds and starttime<@de and starttime>@dss   ");
          param.add(stime);
    	  param.add(etime);
//		  param.add(s2);
//		  param.add(e2);

    	String cid=note.getCid();
    	String lid=note.getLid();
    	String sid=note.getSid();
    	String gro=note.getGro();
    	String type=note.getType();
    	String radio=note.getRad();//radio
        String[] info=note.getInfo();//select
    
//		if( cid !=null && !cid.equals("")){//��վ��
//			sb.append(" and  v_locatedata.cardreaderid= ? ");
//			param.add(cid);
//			
//		}if( lid!=null && !lid.equals("")){//��λ����
//			sb.append(" and v_locatedata.locatorid= ? ");
//			param.add(lid);
//			
//		}
		if(  sid != null && !sid.equals("")){//Ա����
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and staffer.stafferid=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and staffer.[name]=? ");
				param.add(sid);
			}
//			sb.append(" and  staffer.stafferid= ? ");
//		    param.add(sid);
		    
		}if( gro != null && !gro.equals("")){//����
			sb.append("  and staffer.[group]= ? ");
			param.add(gro);
			
		}if( type != null && !type.equals("")){//����
			sb.append("  and staffer.worktype = ? ");
			param.add(type);
		}if( radio != null && !radio.equals("")){
			  int j=0; 
			 if( radio.equals("radand"))
			   {
		   	     if( info != null && info.length>0 ){
		   	    	 for(int i=0;i<info.length;i++){
				            j=j+Integer.parseInt(info[i]);
				                }
				    	   sb.append(" and v_locatedata.state  = ? ");
					       param.add(String.valueOf(j));
		   	          }
			   }else{
			   	     if( info != null && info.length>0 ){  
			   	    	 System.out.println("select or is not null");
			   	    	for(int i=0;i<info.length;i++){
				            j=j+Integer.parseInt(info[i]);
				        }
				    	 sb.append(" and v_locatedata.state&? >0 ");
					     param.add(String.valueOf(j));
			   	     }
			   }
   	    }try{
//  		    sb.append(" and  convert  (char(8),endtime,8)>= ?  and  convert(char(8),starttime,8)< ? ) ");
//  		    param.add(s2);
//  		    param.add(e2);  
			sb.append(" ) as a left join cardreader as b on a.cardreaderid=b.cardreaderid,alarminfo as   ");
			sb.append(" d,startendinfo as e where a.state=d.state and a.endby=e.state " );
			if( cid !=null && !cid.equals("")){//��վ��
				Global  gobal=new Global();
			    List list=gobal.SuggestCardreaders(cid);
				if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sb.append(" and b.cardreaderid=? ");
					param.add(vo.getCardreaderid());
				}else{//û�п��Ż��û���
					sb.append(" and b.shortname=? ");
					param.add(cid);
				}
//				sb.append(" and  v_locatedata.cardreaderid= ? ");
//				param.add(cid);
				
			}
			sb.append("  ) as temptable1 left join locator on temptable1.locatorid=locator.locatorid  ");
			if( lid!=null && !lid.equals("")){//��λ����
				Global  gobal=new Global();
	 		    List list1=gobal.SuggestLocators(lid);
	 		    
				if(list1.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
					
					sb.append(" where locator.locatorid= ? ");
//					sb.append(" and v_locatedata.locatorid= ? ");
					param.add(vo.getLocatorid());
				}else{//û�п��Ż��û���
					sb.append(" where locator.shortname=? ");
					param.add(lid);
				}
//				sb.append(" and v_locatedata.locatorid= ? ");
//				param.add(lid);
				
			}
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    	    relist=query.getResults(sb.toString(),param.toArray(),NoteVO.class);
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
        * ��ò�ѯ�ļ�¼��
        * */
	    @SuppressWarnings("unchecked")
		public List getAllcount(Search_Note note)throws Exception{
	 	   List relist=null;
	 	   Engine engine=null;
	 	   List param=new ArrayList();
	 	   StringBuffer sb=new StringBuffer();
//	 	   sb.append(" declare @ds datetime,@de datetime,@dss datetime select @ds=convert(datetime,?), ");
//	 	   sb.append(" @de=convert(datetime,?)+1,@dss=@ds-3 select count(*) as count ");
//	 	   sb.append(" from v_locatedata left join staffer on v_locatedata.stafferID=staffer.stafferID where  ");
//	 	   sb.append(" ( endtime>=@ds and starttime<@de and starttime>@dss  ");
//	 	   String stime=note.getStime();//��ȡע��ʱ���
//	 	   String s1=stime.substring(0,10);  
//	 	   String s2=stime.substring(11,stime.length()); 
//	 	   
//	 	   String etime=note.getEtime();//��ȡע��ʱ��С��
//	 	   String e1=etime.substring(0,10);  
//	 	   String e2=etime.substring(11,etime.length());
//	 	   param.add(s1);
//	 	   param.add(e1);
	 	   
	 	   sb.append(" declare @ds datetime,@de datetime,@dss datetime select @ds=convert(datetime,?), ");
	 	   sb.append(" @de=convert(datetime,?),@dss=@ds-3 select count(*) as count ");
	 	   sb.append(" from v_locatedata left join staffer on v_locatedata.stafferID=staffer.stafferID where  ");
	 	   sb.append(" ( starttime>=@ds and endtime<=@de  ");
	        String stime=note.getStime();//��ȡע��ʱ���
			String etime=note.getEtime();//��ȡע��ʱ��С��
	 	    param.add(stime);
	 	    param.add(etime);
	 	    
	 	  	String cid=note.getCid();
	    	String lid=note.getLid();
	    	String sid=note.getSid();
	    	String gro=note.getGro();
	    	String type=note.getType();
	    	String radio=note.getRad();//radio
	        String[] info=note.getInfo();//select
			if( cid !=null && !cid.equals("")){//��վ��
				Global  gobal=new Global();
			    List list=gobal.SuggestCardreaders(cid);
				if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sb.append(" and v_locatedata.cardreaderid=? ");
					param.add(vo.getCardreaderid());
				}else{//û�п��Ż��û���
					sb.append(" and staffer.[name]=? ");
					param.add(cid);
				}
//				sb.append(" and  v_locatedata.cardreaderid = ? ");
//				param.add(cid);
				
			}if( lid!=null && !lid.equals("")){//��λ����
				Global  gobal=new Global();
	 		    List list1=gobal.SuggestLocators(lid);
	 		    
				if(list1.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
					
					sb.append(" and v_locatedata.locatorid= ? ");
//					sb.append(" and v_locatedata.locatorid= ? ");
					param.add(vo.getLocatorid());
				}else{//û�п��Ż��û���
					sb.append(" and staffer.[name]=? ");
					param.add(lid);
				}
//				sb.append("and  v_locatedata.locatorid = ? ");
//				param.add(lid);
				
			}if(  sid != null && !sid.equals("")){
				Global  gobal=new Global();
	 		    List list=gobal.SuggestEmployees(sid);
				if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sb.append(" and staffer.stafferid=? ");
					param.add(vo.getStafferid());
				}else{//û�п��Ż��û���
					sb.append(" and staffer.[name]=? ");
					param.add(sid);
				}
//				sb.append(" and  staffer.stafferid = ? ");
//			    param.add(sid);
			    
			}if( gro != null && !gro.equals("")){
				sb.append("  and   staffer.[group] = ? ");
				param.add(gro);
				
			}if( type != null && !type.equals("")){
				sb.append("  and  staffer.worktype = ? ");
				param.add(type);
			}if( radio != null && !radio.equals("")){
				  int j=0; 
				 if( radio.equals("radand"))
				   {
			   	     if( info != null && info.length>0 ){
			   	    	 for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					                }
					    	   sb.append("  and  v_locatedata.state = ? ");
						       param.add(String.valueOf(j));
			   	          }
				   }else{
				   	     if( info != null && info.length>0 ){  
				   	    	for(int i=0;i<info.length;i++){
					            j=j+Integer.parseInt(info[i]);
					        }
					    	 sb.append(" and v_locatedata.state&? >0 ");
						     param.add(String.valueOf(j));
				   	     }
				   }
	   	    } 
	 	   try{
//	 		    sb.append(" and  convert(char(8),endtime,8)>=? and  convert(char(8),starttime,8)<? ) ");
//	    		param.add(s2);
//	    		param.add(e2);
	 		   	sb.append(")");
	 		    engine=EngineFactory.getEngine("test");
	 		    Query query=engine.getQuery();
	 	        relist=query.getResults(sb.toString(),param.toArray(),NoteVO.class);
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
 



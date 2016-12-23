package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_Employee_check_daily;
import com.kj133.entity.vo.Employee_check_dailyVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class Employee_check_dailyBO {
   /**
    * Ա�������ձ�
    * */
	private Logger log=Logger.getLogger(Employee_check_dailyBO.class);
    public Employee_check_dailyBO(){
    	
    }
    
    @SuppressWarnings("unchecked")
	public List getList(String time,Search_Employee_check_daily check,String userId)throws Exception {
    	List relist=null;
    	List param=new ArrayList();
    	Engine engine=null;
    	StringBuffer sb=new StringBuffer();
    	sb.append(" select qtable.*  from( ");
    	sb.append(" select a.* ");
    	sb.append(" ,case when b.cardMode='����' then b.cardMode "); 
    	sb.append(" when a.[group]='���쵼' then a.[group] "); 
    	sb.append(" when occupation='������Ա' then occupation "); 
    	sb.append(" when (occupation='����Ա') or (occupation='�߼�Ա') then occupation "); 
    	sb.append(" when occupation='�����쵼' then occupation  ");
    	sb.append(" when occupation='����˾��' then occupation "); 
    	sb.append(" when iftemp='��ʽ��' then iftemp "); 
    	sb.append(" when iftemp='����' then iftemp  ");
    	sb.append(" when ifother='��ί��' then ifother  ");
    	sb.append(" else '����' end as type  ");
    	sb.append(" from ( ");
    	sb.append(" select cardid,[name],shunxu,[group],worktype,stafferid,sum(downsum) downsum,sum(worktime) worktime,sum(zao) zao,sum(zhong) zhong, ");
    	sb.append(" sum(wan) wan,sum(qita) qita ");
    	sb.append("  from ( ");
    	sb.append(" select b.cardid,b.[name],b.shunxu,b.[group],b.worktype,a.stafferid,a.downsum,a.worktime ");
    	sb.append(" ,case when ban_ID=1 then 1 else 0 end zao ");
    	sb.append(" ,case when ban_ID=2 then 1 else 0 end zhong ");
    	sb.append(" ,case when ban_ID=3 then 1 else 0 end wan ");
    	sb.append(" ,case when (ban_ID<>1) and (ban_ID<>2) and (ban_ID<>3) then 1 else 0 end qita "); 
    	sb.append(" from finattendance a,staffer b  ");
    	sb.append(" where convert(char(10),a.kqtime,20)=? and a.stafferid=b.stafferid and a.userid=? ");
    	param.add(check.getStime());
    	param.add(userId);

    	sb.append(" ) as aa group by name,shunxu,[group],worktype,stafferid,cardid ");
    	sb.append(" ) a  ");
    	sb.append(" left join (select b.stafferid,b.occupation,b.iftemp,b.ifother,c.CardMode from staffer b left join recog c on b.cardid=c.cardid) b ");
    	sb.append(" on a.stafferid=b.stafferid ");
    	sb.append(" ) qtable,reportpopedom rp  where rp.department=qtable.[group] and rp.userid='"+userId+"' and 1=1 ");
    	if(check.getGro() != null && !check.getGro().equals("")){
        	sb.append(" and [group]= ? ");
        	param.add(check.getGro());
        }if(check.getCardid() !=null && !check.getCardid().equals("")){
        	Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(check.getCardid());
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and stafferid=? ");
				param.add(vo.getStafferid());
			}else{//û�п��Ż��û���
				sb.append(" and [name]=? ");
				param.add(check.getCardid());
			}
//        	sb.append(" and stafferid = ? ");
//        	param.add(check.getCardid());
        }if(check.getType() !=null && !check.getType().equals("")){
        	sb.append(" and type = ? ");
        	param.add(check.getType());
        }
    	sb.append(" order by shunxu ");

        
    	try{
    		//System.out.print("sql="+sb);
    		engine=EngineFactory.getEngine("test");
    	    engine.executeProcedureCall("{ call CalFinalattendance (?,?,?)}",new Object[] {time,check.getStime(),userId}); 
    	    Query query=engine.getQuery();
    	    relist=query.getResults(sb.toString(),param.toArray(),Employee_check_dailyVO.class);
    	   // System.out.println("sql="+sb);
//    	    int j=0,k=0,m=0;
    	    int z=0,zh=0,w=0,ch=0;
    	    int alltimes=0;
    	    String alltime="";
    	    String ae="";
    	    if( relist != null && relist.size() > 0){
    	    for(int i=0;i<relist.size();i++){//����banname�ֶ������ֵ����ʾ��ҳ����ΪtdΪ������ 
    	    	 Employee_check_dailyVO vo=(Employee_check_dailyVO)relist.get(i);
    	    	 vo.setCount(String.valueOf(i+1));
    	    	 z = z + vo.getZao();
    	    	 zh = zh + vo.getZhong();
    	    	 w = w + vo.getWan();
    	    	 ch = ch + vo.getDownsum();
				   int at = Integer.valueOf(vo.getWorktime()).intValue();
					int hour = at / 60;
					int min = at % 60;
					alltime = hour + "Сʱ" + min + "��";
					
					alltimes += at;   
					vo.setWorktime(alltime);
//    	    	   if( vo.getBanname().equals("��") ){
//      	        	 vo.setZaoban("��");
//      	        	 j++; 
//      	        }else if ( vo.getBanname().equals("��+")){
//      	        	 vo.setZaoban("��+");
//      	        	 j++; 
//      	        }else if( vo.getBanname().equals("��")){
//      	        	 vo.setZhongban("��");
//      	        	 k++;
//      	        }else if( vo.getBanname().equals("��+")){
//  	   	        	 vo.setZhongban("��+");
//  		        	 k++;
//  	        	 }else if( vo.getBanname().equals("��")){
//      	        	 vo.setWanban("��");
//      	        	 m++;
//  	        	 }else if( vo.getBanname().equals("��+")){
//      	        	 vo.setWanban("��+");
//      	        	 m++;
//      	         } 
    	    }
    	    //ͳ��
    	    Employee_check_dailyVO vo=new Employee_check_dailyVO();
    	    vo.setCount("�ܼ�");
    	    vo.setZao(z);
    	    vo.setZhong(zh);
    	    vo.setWan(w);
    	    vo.setDownsum(ch);
  		  int ah = alltimes / 60;
		  int am = alltimes % 60;
		  ae = ah + "Сʱ" + am + "��";
		  vo.setWorktime(ae);
//    	    vo.setZaoban(String.valueOf(j));
//    	    vo.setZhongban(String.valueOf(k));
//    	    vo.setWanban(String.valueOf(m));
    	    relist.add(vo);//����д
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
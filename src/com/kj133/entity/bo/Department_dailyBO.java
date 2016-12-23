package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import com.kj133.entity.Search_Department_daily;
import com.kj133.entity.vo.Department_dailyVO;
import com.kj133.util.Global;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
public class Department_dailyBO {

	private static final Logger log=Logger.getLogger(Department_dailyBO.class);
    public Department_dailyBO(){
    	
    }
    @SuppressWarnings("unchecked")
	public List getList(Search_Department_daily dep,String userid)throws Exception{
    	List relist=null;
    	Engine engine=null;
    	Global go = new Global();
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	/*sb.append(" select c.*,isnull(shunxu.shunxu,9999) shunxu,c.Worktime/(case when (zao+zhong+wan+qita)=0 then 1 else (zao+zhong+wan+qita) end) counttime,������ countp,1.0*cishu/downsum pcdcount from (");
    	sb.append(" select [group],Count(*) downsum,sum(worktime) worktime,sum(zao) zao,sum(zhong) zhong,sum(wan) wan,sum(qita) qita,sum(����) cishu from(");
    	sb.append(" select [group],stafferid,sum(worktime) worktime,sum(zao) zao,sum(zhong) zhong,sum(wan) wan,sum(qita) qita,sum(����) ���� from ( ");
    	sb.append(" select [group],sum(worktime) worktime,stafferid,sum(����) ����, ");
        sb.append(" case when ban_ID=1 then Count(stafferid) else 0 end as zao, ");
        sb.append(" case when ban_ID=2 then Count(stafferid) else 0 end as zhong, ");
        sb.append(" case when ban_ID=3 then Count(stafferid) else 0 end as wan, ");
        sb.append(" case when ban_ID<>1 and ban_ID<>2 and ban_ID<>3 then Count(stafferid) else 0 end as qita ");
        sb.append(" from ( ");
    			
        sb.append(" select [group],stafferid,cardid,ban_ID,ban_name,sum(worktime) worktime,sum(����) ���� from ( ");
        sb.append(" select aa.*,ban_ID,bantype.ban_name,1 ���� from( ");
        sb.append(" select e.*,g.[group] from ( ");
        sb.append(" select  stafferid,cardid,bantypeid,kqtime,downtime,case when datediff(mi,downtime,uptime)<0 then 0 else datediff(mi,downtime,uptime) end as worktime from ");  
        sb.append(" (select w.*,isnull(s.bantypeid,0) bantypeid from workattendanceex w left  join workovertime s on w.stafferid=s.stafferid) w where "); 
    			
    			
        sb.append(" convert(varchar(10),kqtime,20)=? ");
        param.add(dep.getStime());
        sb.append(" ) e,(select cardid,cardmode from recog where cardmode<>'����') f,(select stafferid,[group] from staffer where [group]<>'�ι���Ա' ");
//        System.out.println("--------group="+dep.getGro());
        if( dep.getGro() !=null && !dep.getGro().equals("")){
    	sb.append(" and [group] = ? ");
    	param.add(dep.getGro());
        }
        
        sb.append(" ) g where e.cardid=f.cardid and e.stafferid=g.stafferid  ");
        sb.append(" ) aa,bantype where aa.bantypeid=bantype.bantypeid and  ");
        sb.append(" ((start_stime < end_stime and substring(convert(char,downtime,108),1,5)>=start_stime and substring(convert(char,downtime,108),1,5)<end_stime) or ");
        sb.append(" (start_stime > end_stime and (substring(convert(char,downtime,108),1,5)>=start_stime or substring(convert(char,downtime,108),1,5)<end_stime))) ");
        sb.append(" ) h group by [group],stafferid,cardid,ban_ID,ban_name,convert(varchar(10),kqtime,20) ");
    			
    			
        sb.append(" ) a group by [group],stafferid,ban_ID,ban_name,���� ");
        sb.append(" ) aaa group by [group],stafferid ");
        sb.append(" ) b group by [group] ");
        sb.append(" ) c  ");
        sb.append(" left join (select [group],count(*) ������ from staffer a,recog b where a.Cardid=b.Cardid group by a.[group]) staffer on c.[group]=staffer.[group] ");
        sb.append(" left join shunxu on shunxu.[group]=c.[group] order by isnull(shunxu.shunxu,9999)  ");*/
    	
			sb.append(" select aaa.[group],������ countp,�뾮���� downsum,�Ͼ����� upsum,���� cishu,�Ͼ����� upcishu,worktime,ƽ��ʱ�� counttime,�˾��뾮���� pcdcount,ban01 zao,ban02 zhong ,ban03 wan from( ");
			sb.append(" select c.*,isnull(shunxu.shunxu,9999) shunxu,c.Worktime/ ");
			sb.append(" (case when (Ban01+Ban02+Ban03)=0 then 1 else  ( Ban01+Ban02+Ban03) end) ƽ��ʱ��, ");
			sb.append(" ������,1.0*����/�뾮���� �˾��뾮���� from ( ");
			sb.append(" select [group],Count(*) �뾮����,sum(�Ͼ�����) �Ͼ�����,sum(worktime) worktime, ");
			sb.append(" sum(Ban01) as 'Ban01', sum(Ban02) as 'Ban02', sum(Ban03) as 'Ban03', ");
			sb.append(" sum(����) ����,sum(up����) as �Ͼ����� from( ");
			sb.append(" select [group],stafferid,sum(worktime) worktime, ");
			sb.append(" sum(Ban01) as 'Ban01', sum(Ban02) as 'Ban02', sum(Ban03) as 'Ban03',");
			sb.append(" sum(����) ����,sum(up����) as up���� ,  �Ͼ����� from (");
			sb.append(" select [group],sum(worktime) worktime,stafferid,sum(����) ����,sum(up����) as up���� ,  �Ͼ����� ");
			sb.append(" ,case when Ban_ID = 1 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban01' ,case when Ban_ID = 2 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban02' ,case when Ban_ID = 3 and BanTypeID = 0 then Count(stafferid) else 0 end 'Ban03' ");
			sb.append(" from ( ");
			sb.append(" select [group],stafferid,cardid,ban_ID,ban_name,sum(worktime) worktime,sum(����) ����,sum(up����) up����,case when sum(up����) > 0 then 1 else 0 end �Ͼ�����,bantypeid from ( ");
			sb.append(" select aa.*,ban_ID,bantype.ban_name,1 ����,case when isnull(uptime,0) =0 then 0 else 1 end up���� from( ");
			sb.append(" select e.*,g.[group] from ( ");
			sb.append(" select  stafferid,cardid,bantypeid,kqtime,downtime,uptime,case when datediff(mi,downtime,uptime)<0 then 0 else datediff(mi,downtime,uptime) end as worktime from ");
			sb.append(" (select w.*,isnull(s.bantypeid,0) bantypeid from workattendanceex w left  join workovertime s on w.stafferid=s.stafferid) w where ");
			sb.append(" convert(varchar(10),kqtime,20)=? ");
			param.add(dep.getStime());
			sb.append(" ) e,(select cardid,cardmode from recog where cardmode<>'����') f,(select stafferid,[group] from staffer where [group]<>'�ι���Ա') g ");
			sb.append(" where e.cardid=f.cardid and e.stafferid=g.stafferid ");
			sb.append(" ) aa,bantype where aa.bantypeid=bantype.bantypeid and ");
			sb.append(" ((start_stime < end_stime and substring(convert(char,downtime,108),1,5)>=start_stime and substring(convert(char,downtime,108),1,5)<end_stime) or ");
			sb.append(" (start_stime > end_stime and (substring(convert(char,downtime,108),1,5)>=start_stime or substring(convert(char,downtime,108),1,5)<end_stime))) ");
			sb.append(" ) h group by [group],stafferid,cardid,ban_ID,ban_name,convert(varchar(10),kqtime,20),bantypeid ");
			sb.append(" ) a group by [group],stafferid,ban_ID,ban_name,����,up����,bantypeid,�Ͼ����� ");
			sb.append(" ) aaa group by [group],stafferid,�Ͼ����� ");
			sb.append(" ) b group by [group] ");
			sb.append(" ) c ");
			sb.append(" left join (select [group],count(*) ������ from staffer a,recog b where a.Cardid=b.Cardid group by a.[group]) staffer on c.[group]=staffer.[group] ");
			sb.append(" left join shunxu on shunxu.[group]=c.[group] ");
			sb.append(" ) aaa,reportpopedom rp  where rp.department=aaa.[group] and rp.userid='"+userid+"' order by aaa.[group] ");
			
		

    	try{
        	engine=EngineFactory.getEngine("test");
        	Query query=engine.getQuery();
            relist=query.getResults(sb.toString(),param.toArray(),Department_dailyVO.class);
            int countp = 0;//������
            int downsum = 0;  //�뾮����
            int cishu = 0; //�뾮����
            int upsum = 0;
            int upcishu = 0;
            int zao = 0,zhong =0,wan = 0,qita = 0;
            String worktime = "";   // ��ʱ��
            String counttime = "";  // ƽ��ʱ��
            double pcdcount = 0.0;   //  �˾��뾮����

            int counttimes = 0;
            int alltimes = 0;
            String ae = "";
            String ce = "";
            int cc = 0;

            if( relist != null && relist.size() > 0){
            	for(int i=0;i<relist.size();i++){
            		Department_dailyVO vo=(Department_dailyVO)relist.get(i);
            		vo.setCount(String.valueOf(i+1));//���
            		cc = Integer.valueOf(String.valueOf(i+1));
            		countp = countp + vo.getCountp();
            		downsum = downsum + vo.getDownsum();
            		upsum = upsum + vo.getUpsum();
            		upcishu = upcishu + vo.getUpcishu();
            		cishu = cishu + vo.getCishu();
            		zao = zao + vo.getZao();
            		zhong = zhong + vo.getZhong();
            		wan = wan + vo.getWan();
//            		qita = qita + vo.getQita();
            		
            		pcdcount = pcdcount + vo.getPcdcount();
            		
 				   int at = Integer.valueOf(vo.getWorktime()== null ? 0 : Integer.parseInt(vo.getWorktime()));
					int hour = at / 60;
					int min = at % 60;
					worktime = hour + "Сʱ" + min + "��";
					
					alltimes += at;   
					vo.setWorktime(worktime);
					
					int ct = Integer.valueOf(vo.getCounttime()== null ? 0 : Integer.parseInt(vo.getCounttime()));
					int hours = ct / 60;
					int mins = ct % 60;
					counttime = hours + "Сʱ" + mins + "��";
					counttimes += ct ;
					vo.setCounttime(counttime);

            	}
             Department_dailyVO vo=new Department_dailyVO();
             vo.setCount("�ϼ�");

             vo.setCountp(countp);
             vo.setUpcishu(upcishu);
             vo.setUpsum(upsum);
             vo.setDownsum(downsum);
             vo.setCishu(cishu);
             vo.setZao(zao);
             vo.setZhong(zhong);
             vo.setWan(wan);
//             vo.setQita(qita);
     		  int ah = alltimes / 60;
    		  int am = alltimes % 60;
    		  ae = ah + "Сʱ" + am + "��";
    		  vo.setWorktime(ae);
    		  
    		  int cs = counttimes / cc ;
    		  int cth = cs / 60;
    		  int ctm = cs % 60;
    		  ce = cth + "Сʱ" + ctm + "��";
    		  vo.setCounttime(ce);
    		 double pc =  pcdcount / cc ;
    		 vo.setPcdcount(Double.valueOf(go.RoundFracToString(pc, 3)).doubleValue());
    		  
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

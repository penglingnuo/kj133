package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;   
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.Depdownmonth;
import com.kj133.entity.Search_DownWell_menology;
import com.kj133.util.Global;
public class Downwell_menologyBO {
  /**
   * 部门下井月报
   * */
	private static final Logger log=Logger.getLogger(Downwell_menologyBO.class);
	public Downwell_menologyBO(){
		
	}
	
	/**
	 * 部门下井
	 * 获取结果集合
	 * */
	 @SuppressWarnings("unchecked")
	public List getList(Search_DownWell_menology well,String name)throws Exception{
		 DecimalFormat format=new DecimalFormat("#0.00");   
		 List relist=null;
	     Global bo=new Global();
		 List param=new ArrayList();
		 Engine engine=null;
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select  depname,peoplecount,downtimesa,downtimesb,downdaysa,downdaysb, ");
		 sb.append(" isnull (substring(rtrim(downintervala/60),1,8)+'小时'+substring(rtrim(downintervala%60),1,8)+'分','0小时0分' )as  downintervala, ");
		 sb.append(" isnull (substring(rtrim(downintervalb/60),1,8)+'小时'+substring(rtrim(downintervalb%60),1,8)+'分','0小时0分') as downintervalb, ");
		 sb.append(" substring(rtrim(avgintervala/60),1,8)+'小时'+substring(rtrim(avgintervala%60),1,8)+'分' as avgintervala, ");
		 sb.append(" substring(rtrim(avgintervalb/60),1,8)+'小时'+substring(rtrim(avgintervalb%60),1,8)+'分' as avgintervalb, ");
		 sb.append(" convert(decimal(38,2),avgtimesa)as avgtimesa,convert(decimal(38,2),avgtimesb)as avgtimesb,below2timesa,below2timesb,yeartimes  from  depdownmonth ddm,reportpopedom rp  where rp.department=ddm.[depname] and rp.userid='"+name+"' and  ddm.userid= ? ");
		 param.add(name);
		 
		int day=bo.getDaysOfMonth(well.getStime());
		String first=well.getStime()+"-01";
		String second=well.getStime()+"-"+String.valueOf(day);
		
	
		 if( well.getGro() != null && !well.getGro().equals("")){
			 sb.append(" and depname= ? ");
			 param.add(well.getGro());
		 }
		 try{
			 sb.append(" order by peoplecount "); 
			 engine=EngineFactory.getEngine("test");
             engine.executeProcedureCall("{call CalDepdownMonth (?,?,?) }",new Object[]{first,second,name});
			 Query query=engine.getQuery();
		     relist=query.getResults(sb.toString(),param.toArray(),Depdownmonth.class);
		     int aa = 0;
		     int bb = 0;
		     int ah=0;
		     int as=0;
		     int aaa=0;
		     int bh=0;
		     int bs=0;
		     int bbb=0;
		     int people=0;
		     int downtimea=0;
		     int downtimeb=0;
		     int downdaysa=0;
		     int downdaysb=0;
		     int yeartimes=0;
		     int below2timesa=0;
		     int below2timesb=0;
		     //double avgtimesa=0;
		     //double avgtimesb=0;
		     
		     int downintervalah=0;
		     int downintervalas=0;
		     String alldownintervala="";//上月入井时长
		     int downintervalbh=0;
		     int downintervalbs=0;
		     String alldownintervalb="";//本月入井时长
//             int avgintervalah=0;
//             int avgintervalas=0;
//             String allavgintervalaha="";//上月人均时长
//             int avgintervalbh=0;
//             int avgintervalbs=0;
//             String allavgintervalahb="";//本月人均时长
		     if( relist != null && relist.size()> 0){
		    	 for(int i=0;i<relist.size();i++){
		    		 Depdownmonth de=(Depdownmonth)relist.get(i);
		    		 de.setCount(String.valueOf(i+1));//序号
		    		 
		    		 people=people+de.getPeoplecount();//总人数
		    		 downtimea=downtimea+de.getDowntimesa();//上月入井次数
		    		 downtimeb=downtimeb+de.getDowntimesb();//本月入井次数
		    		 downdaysa=downdaysa+de.getDowndaysa();//上月入井天数
		    	     downdaysb=downdaysb+de.getDowndaysb();//本月入井天数
		    	     

		    	     int a=de.getDownintervala().indexOf("小时");
		    		 int hour=Integer.parseInt(de.getDownintervala().substring(0,a));
		    		 int seco=Integer.parseInt(de.getDownintervala().substring(a+2,de.getDownintervala().length()-1));
		    		 downintervalah=downintervalah+hour;
		    		 downintervalas=downintervalas+seco;//上月入井时长
		    		 
		    		 int b=de.getDownintervalb().indexOf("小时");
		    		 int hourb=Integer.parseInt(de.getDownintervalb().substring(0,b));
		    		 int secob=Integer.parseInt(de.getDownintervalb().substring(b+2,de.getDownintervalb().length()-1));
		    		 downintervalbh=downintervalbh+hourb;
		    		 downintervalbs=downintervalbs+secob;//本月入井时长
		    		 
//		    		 int c=de.getAvgintervala().indexOf("小时");
//		    		 int hourc=Integer.parseInt(de.getAvgintervala().substring(0,c));
//		    		 int secoc=Integer.parseInt(de.getAvgintervala().substring(c+2,de.getAvgintervala().length()-1));
//		    		 avgintervalah=avgintervalah+hourc;
//		    		 avgintervalas=avgintervalas+secoc;//上月人均时长
		    		 
		    		 
//		    		 int d=de.getAvgintervalb().indexOf("小时");
//		    		 int hourd=Integer.parseInt(de.getAvgintervalb().substring(0,d));
//		    		 int secod=Integer.parseInt(de.getAvgintervalb().substring(d+2,de.getAvgintervalb().length()-1));
//		    		 avgintervalbh=avgintervalbh+hourd;
//		    		 avgintervalbs=avgintervalbs+secod;//上月人均时长
		    		 
		    	     //avgtimesa=avgtimesa+Double.parseDouble(de.getAvgtimesa());//上月平均入井次数
		    	     //avgtimesb=avgtimesb+Double.parseDouble(de.getAvgtimesb());//本月平均入井次数
		    	     below2timesa=below2timesa+de.getBelow2timesa();//上月低于2小时入井数
		    	     below2timesb =below2timesb+de.getBelow2timesb();//本月低于2小时入井数
		    	     yeartimes=yeartimes+de.getYeartimes();//本年度总次数
		    	 }
		     }
		     //上月人均时长
		     aa = downintervalah*60+downintervalas;
		     double ee = aa/people;
		     
		     aaa = Double.valueOf(ee).intValue();
			   ah = aaa / 60;
			   as = aaa % 60;
			String  atimes = ah + "小时" + as + "分";	
			
			//本月人均时长
			bb = downintervalbh*60+downintervalbs;
			double cc = bb/people;
			
			bbb = Double.valueOf(cc).intValue();
			bh = bbb / 60;
			bs = bbb % 60;
			String  btimes = bh + "小时" + bs + "分";	
			
            alldownintervala=String.valueOf(downintervalah+downintervalas/60)+"小时"+downintervalas%60+"分";
            alldownintervalb=String.valueOf(downintervalbh+downintervalbs/60)+"小时"+downintervalbs%60+"分";
//            allavgintervalaha=String.valueOf(avgintervalah+avgintervalas/60)+"小时"+avgintervalas%60+"分";
//            allavgintervalahb=String.valueOf(avgintervalbh+avgintervalbs/60)+"小时"+avgintervalbs%60+"分";
//            aa = Integer.valueOf(alldownintervala).intValue();
            

            Depdownmonth de =new  Depdownmonth ();
            de.setCount("合计");
            de.setPeoplecount(people);
            de.setDowntimesa(downtimea);
            de.setDowntimesb(downtimeb);
            de.setDowndaysa(downdaysa);
            de.setDowndaysb(downdaysb);
            de.setDownintervala(alldownintervala);
            de.setDownintervalb(alldownintervalb);
            de.setAvgintervala(atimes);
            de.setAvgintervalb(btimes);
            //de.setAvgtimesa(format.format(avgtimesa)); 不是平均入井次数的累加
            //de.setAvgtimesb(format.format(avgtimesb));
            double p=Double.parseDouble(String.valueOf(people));
            double a=Double.parseDouble(String.valueOf(downtimea));
            double b=Double.parseDouble(String.valueOf(downtimeb));
            de.setAvgtimesa(format.format(a/p));//平均入井次数（本月）
            de.setAvgtimesb(format.format(b/p));//上月
            de.setBelow2timesa(below2timesa);
            de.setBelow2timesb(below2timesb);
            de.setYeartimes(yeartimes);
            relist.add(de);
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
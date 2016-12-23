package com.kj133.entity.bo;

/**
 * 入井次数月报
 */
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;


import com.kj133.entity.Search_downWellCount_menology;
import com.kj133.entity.vo.DownWellCount_menologyVO;
import com.kj133.entity.vo.SuggestEmployeeVO;

import com.kj133.util.Global;

public class DownWellCount_menologyBO {
	private static final Logger log=Logger.getLogger(DownWellCount_menologyBO.class);
	public DownWellCount_menologyBO(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List getList(Search_downWellCount_menology emplo,String name)throws Exception{
		Engine engine=null;
		List relist=null;
		List relist2=null;//合计
		List resultlist = new ArrayList();
		List param=new ArrayList();
		List param2=new ArrayList();//合计
		StringBuffer sb=new StringBuffer();
		StringBuffer sb2=new StringBuffer();//合计
	
		Global bo=new Global();
		int day=bo.getDaysOfMonth(emplo.getStime());
		String first=emplo.getStime()+"-01";//2007-01-01
		String before=bo.getDay(first, -1);
//		String before=bo.getDay("2007-01-01", -1);
		String second=emplo.getStime()+"-"+String.valueOf(day);//2007-01-31
		
//		sb.append(" select a.stafferid,a.cardid,a.staffername,a.worktype,a.staffergroup,a.worktime,a.downsum,a.pavtime,八点班 ban01,四点班 ban02,零点班 ban03,[1] as a,[2] as b,[3] as c,[4] as d,[5] as e,[6] as f,[7] as g,[8] as h,[9] as i,[10] as j,[11] as k,[12] as l,[13] as m,[14] as n,[15] as o,[16] as p,[17] as q,[18] as r,[19] as s,[20] as t,[21] as u,[22] as v,[23] as w,[24] as x,[25] as y,[26] as z,[27] as aa,[28] as bb,[29] as cc,[30] as dd,[31] as ee ");
		sb.append(" select a.stafferid,a.cardid,a.staffername,a.worktype,a.staffergroup,a.worktime,a.downsum,a.pavtime, ");
		
		sb.append(" case when 八点班=0 then null else 八点班  end ban01, ");
		sb.append(" case when 四点班=0 then null else 四点班  end ban02, ");
		sb.append(" case when 零点班=0 then null else 零点班  end ban03, ");
		sb.append(" case when [1]=0 then null else [1]  end a, ");
		sb.append(" case when [2]=0 then null else [2]  end b, ");
		sb.append(" case when [3]=0 then null else [3]  end c, ");
		sb.append(" case when [4]=0 then null else [4]  end d, ");
		sb.append(" case when [5]=0 then null else [5]  end e, ");
		sb.append(" case when [6]=0 then null else [6]  end f, ");
		sb.append(" case when [7]=0 then null else [7]  end g, ");
		sb.append(" case when [8]=0 then null else [8]  end h, ");
		sb.append(" case when [9]=0 then null else [9]  end i, ");
		sb.append(" case when [10]=0 then null else [10]  end j, ");
		sb.append(" case when [11]=0 then null else [11]  end k, ");
		sb.append(" case when [12]=0 then null else [12]  end l, ");
		sb.append(" case when [13]=0 then null else [13]  end m, ");
		sb.append(" case when [14]=0 then null else [14]  end n, ");
		sb.append(" case when [15]=0 then null else [15]  end o, ");
		sb.append(" case when [16]=0 then null else [16]  end p, ");
		sb.append(" case when [17]=0 then null else [17]  end q, ");
		sb.append(" case when [18]=0 then null else [18]  end r, ");
		sb.append(" case when [19]=0 then null else [19]  end s, ");
		sb.append(" case when [20]=0 then null else [20]  end t, ");
		sb.append(" case when [21]=0 then null else [21]  end u, ");
		sb.append(" case when [22]=0 then null else [22]  end v, ");
		sb.append(" case when [23]=0 then null else [23]  end w, ");
		sb.append(" case when [24]=0 then null else [24]  end x, ");
		sb.append(" case when [25]=0 then null else [25]  end y, ");
		sb.append(" case when [26]=0 then null else [26]  end z, ");
		sb.append(" case when [27]=0 then null else [27]  end aa, ");
		sb.append(" case when [28]=0 then null else [28]  end bb, ");
		sb.append(" case when [29]=0 then null else [29]  end cc, ");
		sb.append(" case when [30]=0 then null else [30]  end dd, ");
		sb.append(" case when [31]=0 then null else [31]  end ee ");
		sb.append(" ,case when c.cardMode='车辆' then c.cardMode ");
		sb.append(" when staffergroup='矿领导' then staffergroup ");
		sb.append(" when occupation='机关人员' then occupation ");
		sb.append(" when (occupation='安监员') or (occupation='瓦检员') then occupation ");
		sb.append(" when occupation='区队领导' then occupation ");
		sb.append(" when occupation='车辆司机' then occupation ");
		sb.append(" when iftemp='正式工' then iftemp ");
		sb.append(" when iftemp='劳务工' then iftemp ");
		sb.append(" when ifother='外委工' then ifother ");
		sb.append(" else '其他' end as type ");
		sb.append("  from ( ");
//		sb.append(" select  stafferid,substring(rtrim(sum(worktime)/60),1,8)+'小时'+substring(rtrim(sum(worktime)%60),1,8)+'分' worktime,sum(downsum) downsum,substring(rtrim(sum(worktime)/sum(downsum)/60),1,8)+'小时'+substring(rtrim(sum(worktime)/sum(downsum)%60),1,8)+'分' pavtime,cardid,staffername,staffergroup,worktype, ");
		sb.append(" select  stafferid,sum(worktime) worktime,sum(downsum) downsum,substring(rtrim(sum(worktime)/sum(downsum)/60),1,8)+'小时'+substring(rtrim(sum(worktime)/sum(downsum)%60),1,8)+'分' pavtime,cardid,staffername,staffergroup,worktype, ");
		sb.append(" sum(case when [1]='' then 0 else 1  end) as [1], ");
		sb.append(" sum(case when [2]='' then 0 else 1  end) as [2], ");
		sb.append(" sum(case when [3]='' then 0 else 1  end) as [3], ");
		sb.append(" sum(case when [4]='' then 0 else 1  end) as [4], ");
		sb.append(" sum(case when [5]='' then 0 else 1  end) as [5], ");
		sb.append(" sum(case when [6]='' then 0 else 1  end) as [6], ");
		sb.append(" sum(case when [7]='' then 0 else 1  end) as [7], ");
		sb.append(" sum(case when [8]='' then 0 else 1  end) as [8], ");
		sb.append(" sum(case when [9]='' then 0 else 1  end) as [9], ");
		sb.append(" sum(case when [10]='' then 0 else 1  end) as [10], ");
		sb.append(" sum(case when [11]='' then 0 else 1  end) as [11], ");
		sb.append(" sum(case when [12]='' then 0 else 1  end) as [12], ");
		sb.append(" sum(case when [13]='' then 0 else 1  end) as [13], ");
		sb.append(" sum(case when [14]='' then 0 else 1  end) as [14], ");
		sb.append(" sum(case when [15]='' then 0 else 1  end) as [15], ");
		sb.append(" sum(case when [16]='' then 0 else 1  end) as [16], ");
		sb.append(" sum(case when [17]='' then 0 else 1  end) as [17], ");
		sb.append(" sum(case when [18]='' then 0 else 1  end) as [18], ");
		sb.append(" sum(case when [19]='' then 0 else 1  end) as [19], ");
		sb.append(" sum(case when [20]='' then 0 else 1  end) as [20], ");
		sb.append(" sum(case when [21]='' then 0 else 1  end) as [21], ");
		sb.append(" sum(case when [22]='' then 0 else 1  end) as [22], ");
		sb.append(" sum(case when [23]='' then 0 else 1  end) as [23], ");
		sb.append(" sum(case when [24]='' then 0 else 1  end) as [24], ");
		sb.append(" sum(case when [25]='' then 0 else 1  end) as [25], ");
		sb.append(" sum(case when [26]='' then 0 else 1  end) as [26], ");
		sb.append(" sum(case when [27]='' then 0 else 1  end) as [27], ");
		sb.append(" sum(case when [28]='' then 0 else 1  end) as [28], ");
		sb.append(" sum(case when [29]='' then 0 else 1  end) as [29], ");
		sb.append(" sum(case when [30]='' then 0 else 1  end) as [30], ");
		sb.append(" sum(case when [31]='' then 0 else 1  end) as [31], ");
		sb.append(" sum(case when ban_id =1 then 1 else 0 end ) as '八点班', "); 
		sb.append(" sum(case when ban_id =2 then 1 else 0 end ) as '四点班',  ");
		sb.append(" sum(case when ban_id =3 then 1 else 0 end ) as  '零点班' ");
		//sb.append(" from monthattendance where Userid=?  group by stafferid,cardid,staffername,staffergroup,worktype ");
		sb.append(" from monthattendance ma ,reportpopedom rp  where rp.department=ma.staffergroup and rp.userid='"+name+"' and  ma.Userid=?  group by stafferid,cardid,staffername,staffergroup,worktype ");
		param.add(name);
		sb.append("  ) as a ");
		sb.append(" left join staffer b on a.stafferid=b.stafferid ");
		sb.append(" left join recog c on a.cardid=c.cardid");
		sb.append(" where 1=1 ");
		if( emplo.getType() !=null && !emplo.getType().equals("")){
			sb.append(" and a.worktype= ? ");
			param.add(emplo.getType());
		}if( emplo.getGro() !=null && !emplo.getGro().equals("")){
			sb.append(" and a.staffergroup= ? ");
			param.add(emplo.getGro());
		}if( emplo.getStafferid() != null && !emplo.getStafferid().equals("")){
			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(emplo.getStafferid());
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and b.stafferid=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and b.[name]=? ");
				param.add(emplo.getStafferid());
			}
//			sb.append(" and a.stafferid= ? ");
//		    param.add(emplo.getStafferid());
		}		
		sb.append(" order by staffergroup ");

		try{
			
			engine=EngineFactory.getEngine("test");
			engine.executeProcedureCall("{ call CalFinalattendance (?,?,?) }",new Object[]{before,second,name});
			engine.executeProcedureCall("{ call CalFinalmonth3 (?,?,?) }",new Object[] {before,second,name});
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),DownWellCount_menologyVO.class);
			if(relist !=null && relist.size()>0){
				String alltime = "";
				int alltimes = 0;
				int downsum = 0;
				int ban01 = 0;
				int ban02 = 0;
				int ban03 = 0;
				
				if( relist != null && relist.size() >0 ){
					for(int mm=0;mm<relist.size();mm++){
						
					
					   DownWellCount_menologyVO vo=(DownWellCount_menologyVO)relist.get(mm);
					   vo.setCount(String.valueOf(mm+1));//序号，他是一0开头的，所以要+1
					   int at = Integer.valueOf(vo.getWorktime()== null ? 0 : Integer.valueOf(vo.getWorktime()));//时长
					   int ds = Integer.valueOf(vo.getDownsum());
					   
					   int b1 = Integer.valueOf(vo.getBan01()== null ? 0 : Integer.valueOf(vo.getBan01()));
					   int b2 = Integer.valueOf(vo.getBan02()== null ? 0 : Integer.valueOf(vo.getBan02()));
					   int b3 = Integer.valueOf(vo.getBan03()== null ? 0 : Integer.valueOf(vo.getBan03()));
					   int hour = at / 60;
						int min = at % 60;
						alltime = hour + "小时" + min + "分";
						
						alltimes += at;
						downsum +=ds;
						ban01 +=b1;
						ban02 +=b2;
						ban03 +=b3;
//					   int worktime = Integer.valueOf(vo.getWorktime());
//					   int downsum = Integer.valueOf(vo.getDownsum());
//					   double pavtime = Double.valueOf(vo.getPavtime());
					   
					  
					  

//					   allcount=allcount+vo.getSum8();
//					   int at = Integer.valueOf(vo.getSum7()).intValue();
//						int hour = at / 60;
//						int min = at % 60;
//						alltime = hour + "小时" + min + "分";
//						
//						alltimes += at;
//						
//						int ct = Integer.valueOf(vo.getSum6()).intValue();
//						int hs = ct / 60; 
//						int ms = ct % 60;
//						counttime = hs + "小时" + ms + "分";
//						
//						vo.setSum7(alltime);
//						vo.setSum6(counttime);
					   
//					   vo.setDownsum(String.valueOf(downsum));
						vo.setWorktime(alltime);
					   resultlist.add(vo);
					}
				}
				DownWellCount_menologyVO vo=new DownWellCount_menologyVO();
	          vo.setCount("合计");
	          int wx = alltimes / 60;
	          int wf = alltimes % 60;
	          String wh = wx + "小时" + wf + "分";
	          vo.setWorktime(wh);
	          vo.setDownsum(String.valueOf(downsum));
	          vo.setBan01(String.valueOf(ban01));
	          vo.setBan02(String.valueOf(ban02));
	          vo.setBan03(String.valueOf(ban03));

	          resultlist.add(vo);
	/*         sb.append(" select a.stafferid,a.cardid,a.staffername,a.worktype,a.staffergroup,a.ban_id,a.bantypeid,a.worktime,a.downsum,a.pavtime,[1] as a,[2] as b,[3] as c,[4] as d,[5] as e,[6] as f,[7] as g,[8] as h,[9] as i,[10] as j,[11] as k,[12] as l,[13] as m,[14] as n,[15] as o,[16] as p,[17] as q,[18] as r,[19] as s,[20] as t,[21] as u,[22] as v,[23] as w,[24] as x,[25] as y,[26] as z,[27] as aa,[28] as bb,[29] as cc,[30] as dd,[31] as ee ");
	         sb.append(" ,case when c.cardMode='车辆' then c.cardMode ");
	         sb.append(" when staffergroup='矿领导' then staffergroup ");
	         sb.append(" when occupation='机关人员' then occupation ");
	         sb.append(" when (occupation='安监员') or (occupation='瓦检员') then occupation ");
	         sb.append(" when occupation='区队领导' then occupation ");
	         sb.append(" when occupation='车辆司机' then occupation ");
	         sb.append(" when iftemp='正式工' then iftemp ");
	         sb.append(" when iftemp='劳务工' then iftemp ");
	         sb.append(" when ifother='外委工' then ifother ");
	         sb.append(" else '其他' end as type ");
	         sb.append("  from (select * from monthattendance where Userid=?) as a ");
	         param.add(name);
	         sb.append(" left join staffer b on a.stafferid=b.stafferid ");
	         sb.append(" left join recog c on a.cardid=c.cardid");
	         sb.append(" where 1=1 ");
	         if( emplo.getType() !=null && !emplo.getType().equals("")){
	        	 sb.append(" and a.worktype= ? ");
	        	 param.add(emplo.getType());
	         }if( emplo.getGro() !=null && !emplo.getGro().equals("")){
	        	 sb.append(" and a.staffergroup= ? ");
	        	 param.add(emplo.getGro());
	         }if( emplo.getStafferid() != null && !emplo.getStafferid().equals("")){
	        	 Global  gobal=new Global();
	        	 List list=gobal.SuggestEmployees(emplo.getStafferid());
	        	 if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
	        		 SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
	        		 sb.append(" and b.stafferid=? ");
	        		 param.add(vo.getStafferid());
	        	 }else{//没有卡号或用户名
	        		 sb.append(" and b.[name]=? ");
	        		 param.add(emplo.getStafferid());
	        	 }
//				sb.append(" and a.stafferid= ? ");
//			    param.add(emplo.getStafferid());
	         }		
	         sb.append(" order by staffergroup ");
	         
	         try{
	        	 
	        	 engine=EngineFactory.getEngine("test");
	        	 engine.executeProcedureCall("{ call CalFinalattendance (?,?,?) }",new Object[]{before,second,name});
	        	 engine.executeProcedureCall("{ call CalFinalmonth3 (?,?,?) }",new Object[] {before,second,name});
	        	 Query query=engine.getQuery();
	        	 relist=query.getResults(sb.toString(),param.toArray(),DownWellCount_menologyVO.class);
	        	 
	        	 int zaocount=0;
	        	 int zhongcount=0;
	        	 int wancount=0;
	        	 int allcount =0;
	        	 String counttime="";
	        	 String alltime="";
	        	 String ae = "";
	        	 String a ="";
	        	 String  b="";
	        	 String  c="";
	        	 String  d="";
	        	 String  e="";
	        	 String  f="";
	        	 String  g="";
	        	 String  h="";
	        	 String  i="";
	        	 String  j="";
	        	 String  k="";
	        	 String  l="";
	        	 String  m="";
	        	 String  n="";
	        	 String  o="";
	        	 String  p="";
	        	 String  q="";
	        	 String  r="";
	        	 String  s="";
	        	 String  t="";
	        	 String  u="";
	        	 String  v="";
	        	 String  w="";
	        	 String  x="";
	        	 String  y="";
	        	 String  z="";
	        	 String  aa="";
	        	 String  bb="";
	        	 String  cc="";
	        	 String  dd="";
	        	 String  ee="";
	        	 String  ff="";
	        	 
	        	 
	        	 int counttimes = 0;
	        	 int alltimes =0;
	        	 if( relist != null && relist.size() >0 ){
	        		 int a11=1;
	        		 for(int mm=0;mm<relist.size();mm++){
	        			 int a1 =0;
	        			 int  b1=0;
	        			 int  c1=0;
	        			 int  d1=0;
	        			 int  e1=0;
	        			 int  f1=0;
	        			 int  g1=0;
	        			 int  h1=0;
	        			 int  i1=0;
	        			 int  j1=0;
	        			 int  k1=0;
	        			 int  l1=0;
	        			 int  m1=0;
	        			 int  n1=0;
	        			 int  o1=0;
	        			 int  p1=0;
	        			 int  q1=0;
	        			 int  r1=0;
	        			 int  s1=0;
	        			 int  t1=0;
	        			 int  u1=0;
	        			 int  v1=0;
	        			 int  w1=0;
	        			 int  x1=0;
	        			 int  y1=0;
	        			 int  z1=0;
	        			 int  aa1=0;
	        			 int  bb1=0;
	        			 int  cc1=0;
	        			 int  dd1=0;
	        			 int  ee1=0;
	        			 int  ff1=0;
	        			 List relist1 = relist;
	        			 DownWellCount_menologyVO vo=(DownWellCount_menologyVO)relist.get(mm);
	        			 vo.setCount(String.valueOf(mm+1));//序号，他是一0开头的，所以要+1
	        			 int worktime = Integer.valueOf(vo.getWorktime());
	        			 int downsum = Integer.valueOf(vo.getDownsum());
	        			 double pavtime = Double.valueOf(vo.getPavtime());
	        			 
	        			 if(!vo.getA().equals("")){
	        				 a1 =a1+a11;
	        			 }
	        			 if(!vo.getD().equals("")){
	        				 d1 =d1+a11;
	        			 }
	        			 
	        			 for(int nn=mm+1;nn<relist1.size();nn++){
	        				 DownWellCount_menologyVO vo1=(DownWellCount_menologyVO)relist1.get(nn);
	        				 if(vo.getStafferid().equals(vo1.getStafferid())){
	        					 worktime+= Integer.valueOf(vo1.getWorktime());
	        					 downsum+=Integer.valueOf(vo1.getDownsum());
	        					 pavtime+=Double.valueOf(vo1.getPavtime());
	        					 if(!vo1.getA().equals("")){
	        						 ++a1;
	        					 }
	        					 if(!vo1.getD().equals("")){
	        						 ++d1;
	        					 }
	        					 relist.remove(nn);
	        					 nn--;
	        				 }
	        			 }
	        			 a +=vo.getA();
	        			 b +=vo.getB();
	        			 c +=vo.getC();
	        			 d +=vo.getD();
	        			 e +=vo.getE();
	        			 f +=vo.getF();
	        			 g +=vo.getG();
	        			 h +=vo.getH();
	        			 i +=vo.getI();
	        			 j +=vo.getJ();
	        			 k +=vo.getK();
	        			 l +=vo.getL();
	        			 m +=vo.getM();
	        			 n +=vo.getN();
	        			 o +=vo.getO();
	        			 p +=vo.getP();
	        			 q +=vo.getQ();
	        			 r +=vo.getR();
	        			 s +=vo.getS();
	        			 t +=vo.getT();
	        			 u +=vo.getU();
	        			 v +=vo.getV();
	        			 w +=vo.getW();
	        			 x +=vo.getX();
	        			 y +=vo.getY();
	        			 z +=vo.getZ();
	        			 aa +=vo.getAa();
	        			 bb +=vo.getBb();
	        			 cc +=vo.getCc();
	        			 dd +=vo.getDd();
	        			 ee +=vo.getEe();
	        			 ff +=vo.getFf();
	        			 
	        			 
//					   allcount=allcount+vo.getSum8();
//					   int at = Integer.valueOf(vo.getSum7()).intValue();
//						int hour = at / 60;
//						int min = at % 60;
//						alltime = hour + "小时" + min + "分";
//						
//						alltimes += at;
//						
//						int ct = Integer.valueOf(vo.getSum6()).intValue();
//						int hs = ct / 60; 
//						int ms = ct % 60;
//						counttime = hs + "小时" + ms + "分";
//						
//						vo.setSum7(alltime);
//						vo.setSum6(counttime);
	        			 vo.setD(String.valueOf(d1));
	        			 vo.setA(String.valueOf(a1));
	        			 vo.setDownsum(String.valueOf(downsum));
	        			 resultlist.add(vo);
	        		 }
	        	 }
	        	 DownWellCount_menologyVO vo=new DownWellCount_menologyVO();
	        	 vo.setCount("合计");
	        	 
	        	 
	        	 
	        	 
	        	 vo.setA(a);
	        	 vo.setB(b);
	        	 vo.setC(c);
	        	 vo.setD(d);
	        	 vo.setE(e);
	        	 vo.setF(f);
	        	 vo.setG(g);
	        	 vo.setH(h);
	        	 vo.setI(i);
	        	 vo.setJ(j);
	        	 vo.setK(k);
	        	 vo.setL(l);
	        	 vo.setM(m);
	        	 vo.setN(n);
	        	 vo.setO(o);
	        	 vo.setP(p);
	        	 vo.setQ(q);
	        	 vo.setR(r);
	        	 vo.setS(s);
	        	 vo.setT(t);
	        	 vo.setU(u);
	        	 vo.setV(v);
	        	 vo.setW(w);
	        	 vo.setX(x);
	        	 vo.setY(y);
	        	 vo.setZ(z);
	        	 vo.setAa(aa);
	        	 vo.setBb(bb);
	        	 vo.setCc(cc);
	        	 vo.setDd(dd);
	        	 vo.setEe(ee);
	        	 vo.setFf(ff);
	        	 
//			  int ah = alltimes / 60;
//			  int am = alltimes % 60;
//			  ae = ah + "小时" + am + "分";
//	         vo.setSum1(zaocount);
//	         vo.setSum2(zhongcount);
//	         vo.setSum3(wancount);
//	         vo.setSum7(ae);
////	         vo.setSum6(counttime);
//	         vo.setSum8(allcount);
	        	 relist.add(vo);
	*/         
			}
			
         
         
        
		 engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
		    throw e;
		}finally{
			engine.closeEngine();
		}
		return resultlist;
	}
	
	 
  
}

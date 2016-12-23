package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_Employeet_menology;
import com.kj133.entity.vo.DownWellCountVO;
import com.kj133.entity.vo.Employee_menologyVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class Employee_menologyBO {
	private static final Logger log=Logger.getLogger(Employee_menologyBO.class);
	public Employee_menologyBO(){
		
	}
	
		@SuppressWarnings("unchecked")//新增虚分页
		public List getList(Search_Employeet_menology emplo,String name)throws Exception{
			Engine engine=null;
			List relist=null;
			List param=new ArrayList();
			StringBuffer sb=new StringBuffer();
		
			Global bo=new Global();
			int day=bo.getDaysOfMonth(emplo.getStime());
			String first=emplo.getStime()+"-01";//2007-01-01
			String before=bo.getDay(first, -1);
	//		String before=bo.getDay("2007-01-01", -1);
			String second=emplo.getStime()+"-"+String.valueOf(day);//2007-01-31
			


			sb.append(" select qtable.* from( ");
			sb.append(" select a.stafferid,a.cardid,a.staffername,a.worktype,a.staffergroup,a.ban_id,a.bantypeid,a.worktime,a.downsum,a.pavtime,[1] as a,[2] as b,[3] as c,[4] as d,[5] as e, ");
			sb.append(" [6] as f,[7] as g,[8] as h,[9] as i,[10] as j,[11] as k,[12] as l,[13] as m,[14] as n,[15] as o,[16] as p,  ");
			sb.append(" [17] as q,[18] as r,[19] as s,[20] as t,[21] as u,[22] as v,[23] as w,[24] as x,[25] as y,[26] as z,[27] as aa,  ");
//			sb.append(" [28] as bb,[29] as cc,[30] as dd,[31] as ee,sum1,sum2,sum3,sum4,sum5,b.shunxu, ");
			sb.append(" [28] as bb,[29] as cc,[30] as dd,[31] as ee, ");
			sb.append(" case when c.cardMode='车辆' then c.cardMode ");
			sb.append(" when staffergroup='矿领导' then staffergroup ");
			sb.append(" when occupation='机关人员' then occupation ");
			sb.append(" when (occupation='安监员') or (occupation='瓦检员') then occupation ");
			sb.append(" when occupation='区队领导' then occupation ");
			sb.append(" when occupation='车辆司机' then occupation ");
			sb.append(" when iftemp='正式工' then iftemp ");
			sb.append(" when iftemp='劳务工' then iftemp ");
			sb.append(" when ifother='外委工' then ifother ");
			sb.append(" else '其他' end as type ");
			sb.append(" from (select * from monthattendance where Userid= ?) as a left join staffer b on a.stafferid=b.stafferid  ");
			param.add(name);
			sb.append(" left join recog c on a.cardid=c.cardid ");
			sb.append(" ) qtable,reportpopedom rp  where rp.department=qtable.[staffergroup] and rp.userid='"+name+"'  and 1=1   ");
			if( emplo.getGro() !=null && !emplo.getGro().equals("")){
				sb.append(" and staffergroup= ? ");
				param.add(emplo.getGro());
		    }if( emplo.getStafferid() != null && !emplo.getStafferid().equals("")){
		    	Global  gobal=new Global();
	 		    List list=gobal.SuggestEmployees(emplo.getStafferid());
				if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sb.append(" and stafferid=? ");
					param.add(vo.getStafferid());
				}else{//没有卡号或用户名
					sb.append(" and staffername=? ");
					param.add(emplo.getStafferid());
				}
//				sb.append(" and  stafferid= ? ");
//			    param.add(emplo.getStafferid());
		    }if(emplo.getType() !=null && !emplo.getType().equals("")){
				sb.append(" and type = ? ");
				param.add(emplo.getType());
            }
		    sb.append(" order by staffergroup ");
		
			
			 
			try{
				
				engine=EngineFactory.getEngine("test");

				engine.executeProcedureCall("{ call CalFinalattendance (?,?,?) }",new Object[]{before,second,name});
//				engine.executeProcedureCall("{ call CalFinalmonth (?,?,?) }",new Object[] {first,second,name});
				engine.executeProcedureCall("{ call CalFinalmonth3 (?,?,?) }",new Object[] {before,second,name});
				Query query=engine.getQuery();
				//String sql = sb.toString();
				relist=query.getResults(sb.toString(),param.toArray(),Employee_menologyVO.class);
				
				//Pagination pagination = new Pagination();
				//pagination.setCount(200);
				//pagination.setTotalCount(relist.size());
				//relist=query.getResults(sb.toString(),param.toArray(),Employee_menologyVO.class,pagination);
				//relist=query.getResults(sql,param.toArray(),DownWellCountVO.class,pagination);
				if( relist != null && relist.size() >0 ){
					for(int mm=0;mm<relist.size();mm++){
					   List relist1 = relist;
					   int worktime = 0;
					   int downsum = 0;
					   int pavtime = 0;
					   int ban01 = 0;
					   int ban02 = 0;
					   int ban03 = 0;
					   int heji = 0;
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
					   Employee_menologyVO vo=(Employee_menologyVO)relist.get(mm);
					   vo.setCount(String.valueOf(mm+1));//序号，他是一0开头的，所以要+1
					   worktime = Integer.valueOf(vo.getWorktime());
					   downsum = Integer.valueOf(vo.getDownsum());
					   a=vo.getA();
					   b=vo.getB();
					   c=vo.getC();
					   d=vo.getD();
					   e=vo.getE();
					   f=vo.getF();
					   g=vo.getG();
					   h=vo.getH();
					   i=vo.getI();
					   j=vo.getJ();
					   k=vo.getK();
					   l=vo.getL();
					   m=vo.getM();
					   n=vo.getN();
					   o=vo.getO();
					   p=vo.getP();
					   q=vo.getQ();
					   r=vo.getR();
					   s=vo.getS();
					   t=vo.getT();
					   u=vo.getU();
					   v=vo.getV();
					   w=vo.getW();
					   x=vo.getX();
					   y=vo.getY();
					   z=vo.getZ();
					   aa=vo.getAa();
					   bb=vo.getBb();
					   cc=vo.getCc();
					   dd=vo.getDd();
					   ee=vo.getEe();
					   if(vo.getBan_id().equals("1")){
						   ban01=1;
					   }if(vo.getBan_id().equals("2")){
						   ban02=1;
					   }if(vo.getBan_id().equals("3")){
						   ban03=1;
					   }
					   if(!a.equals("")){
						   a=a.substring(0,1);
					   }
					   if(!b.equals("")){
						   b=b.substring(0,1);
					   }
					   if(!c.equals("")){
						   c=c.substring(0,1);
					   }
					   if(!d.equals("")){
						   d=d.substring(0,1);
					   }
					   if(!e.equals("")){
						   e=e.substring(0,1);
					   }
					   if(!f.equals("")){
						   f=f.substring(0,1);
					   }
					   if(!g.equals("")){
						   g=g.substring(0,1);
					   }
					   if(!h.equals("")){
						   h=h.substring(0,1);
					   }
					   if(!i.equals("")){
						   i=i.substring(0,1);
					   }
					   if(!j.equals("")){
						   j=j.substring(0,1);
					   }
					   if(!k.equals("")){
						   k=k.substring(0,1);
					   }
					   if(!l.equals("")){
						   l=l.substring(0,1);
					   }
					   if(!m.equals("")){
						   m=m.substring(0,1);
					   }
					   if(!n.equals("")){
						   n=n.substring(0,1);
					   }
					   if(!o.equals("")){
						   o=o.substring(0,1);
					   }
					   if(!p.equals("")){
						   p=p.substring(0,1);
					   }
					   if(!q.equals("")){
						   q=q.substring(0,1);
					   }
					   if(!r.equals("")){
						   r=r.substring(0,1);
					   }
					   if(!s.equals("")){
						   s=s.substring(0,1);
					   }
					   if(!t.equals("")){
						   t=t.substring(0,1);
					   }
					   if(!u.equals("")){
						   u=u.substring(0,1);
					   }
					   if(!v.equals("")){
						   v=v.substring(0,1);
					   }
					   if(!w.equals("")){
						   w=w.substring(0,1);
					   }
					   if(!x.equals("")){
						   x=x.substring(0,1);
					   }
					   if(!y.equals("")){
						   y=y.substring(0,1);
					   }
					   if(!z.equals("")){
						   z=z.substring(0,1);
					   }
					   if(!aa.equals("")){
						   aa=aa.substring(0,1);
					   }
					   if(!bb.equals("")){
						   bb=bb.substring(0,1);
					   }
					   if(!cc.equals("")){
						   cc=cc.substring(0,1);
					   }
					   if(!dd.equals("")){
						   dd=dd.substring(0,1);
					   }
					   if(!ee.equals("")){
						   ee=ee.substring(0,1);
					   }
					   for(int nn=mm+1;nn<relist1.size();nn++){
						   Employee_menologyVO vo1=(Employee_menologyVO)relist1.get(nn);
	        				 if(vo.getStafferid().equals(vo1.getStafferid())){
	        					 worktime+= Integer.valueOf(vo1.getWorktime());
	        					 downsum+=Integer.valueOf(vo1.getDownsum());
	        					 
	        					 String a1=vo1.getA();
	        					 String b1=vo1.getB();
	        					 String c1=vo1.getC();
	        					 String d1=vo1.getD();
	        					 String e1=vo1.getE();
	        					 String f1=vo1.getF();
	        					 String g1=vo1.getG();
	        					 String h1=vo1.getH();
	        					 String i1=vo1.getI();
	        					 String j1=vo1.getJ();
	        					 String k1=vo1.getK();
	        					 String l1=vo1.getL();
	        					 String m1=vo1.getM();
	        					 String n1=vo1.getN();
	        					 String o1=vo1.getO();
	        					 String p1=vo1.getP();
	        					 String q1=vo1.getQ();
	        					 String r1=vo1.getR();
	        					 String s1=vo1.getS();
	        					 String t1=vo1.getT();
	        					 String u1=vo1.getU();
	        					 String v1=vo1.getV();
	        					 String w1=vo1.getW();
	        					 String x1=vo1.getX();
	        					 String y1=vo1.getY();
	        					 String z1=vo1.getZ();
	        					 String aa1=vo1.getAa();
	        					 String bb1=vo1.getBb();
	        					 String cc1=vo1.getCc();
	        					 String dd1=vo1.getDd();
	        					 String ee1=vo1.getEe();
	        					 if(vo1.getBan_id().equals("1")){
	        						 ban01+=1;
	        					 }if(vo1.getBan_id().equals("2")){
	        						 ban02+=1;
	        					 }if(vo1.getBan_id().equals("3")){
	        						 ban03+=1;
	        					 }
	        					 if(!a1.equals("")){
	        						 a1=a1.substring(0,1);
	        					 }
	        					 a+=a1;
	        					 if(!b1.equals("")){
	        						 b1=b1.substring(0,1);
	        					 }
	        					 b+=b1;
	        					 if(!c1.equals("")){
	        						 c1=c1.substring(0,1);
	        					 }
	        					 c+=c1;
	        					 if(!d1.equals("")){
	        						 d1=d1.substring(0,1);
	        					 }
	        					 d+=d1;
	        					 if(!e1.equals("")){
	        						 e1=e1.substring(0,1);
	        					 }
	        					 e+=e1;
	        					 if(!f1.equals("")){
	        						 f1=f1.substring(0,1);
	        					 }
	        					 f+=f1;
	        					 if(!g1.equals("")){
	        						 g1=g1.substring(0,1);
	        					 }
	        					 g+=g1;
	        					 if(!h1.equals("")){
	        						 h1=h1.substring(0,1);
	        					 }
	        					 h+=h1;
	        					 if(!i1.equals("")){
	        						 i1=i1.substring(0,1);
	        					 }
	        					 i+=i1;
	        					 if(!j1.equals("")){
	        						 j1=j1.substring(0,1);
	        					 }
	        					 j+=j1;
	        					 if(!k1.equals("")){
	        						 k1=k1.substring(0,1);
	        					 }
	        					 k+=k1;
	        					 if(!l1.equals("")){
	        						 l1=l1.substring(0,1);
	        					 }
	        					 l+=l1;
	        					 if(!m1.equals("")){
	        						 m1=m1.substring(0,1);
	        					 }
	        					 m+=m1;
	        					 if(!n1.equals("")){
	        						 n1=n1.substring(0,1);
	        					 }
	        					 n+=n1;
	        					 if(!o1.equals("")){
	        						 o1=o1.substring(0,1);
	        					 }
	        					 o+=o1;
	        					 if(!p1.equals("")){
	        						 p1=p1.substring(0,1);
	        					 }
	        					 p+=p1;
	        					 if(!q1.equals("")){
	        						 q1=q1.substring(0,1);
	        					 }
	        					 q+=q1;
	        					 if(!r1.equals("")){
	        						 r1=r1.substring(0,1);
	        					 }
	        					 r+=r1;
	        					 if(!s1.equals("")){
	        						 s1=s1.substring(0,1);
	        					 }
	        					 s+=s1;
	        					 if(!t1.equals("")){
	        						 t1=t1.substring(0,1);
	        					 }
	        					 t+=t1;
	        					 if(!u1.equals("")){
	        						 u1=u1.substring(0,1);
	        					 }
	        					 u+=u1;
	        					 if(!v1.equals("")){
	        						 v1=v1.substring(0,1);
	        					 }
	        					 v+=v1;
	        					 if(!w1.equals("")){
	        						 w1=w1.substring(0,1);
	        					 }
	        					 w+=w1;
	        					 if(!x1.equals("")){
	        						 x1=x1.substring(0,1);
	        					 }
	        					 x+=x1;
	        					 if(!y1.equals("")){
	        						 y1=y1.substring(0,1);
	        					 }
	        					 y+=y1;
	        					 if(!z1.equals("")){
	        						 z1=z1.substring(0,1);
	        					 }
	        					 z+=z1;
	        					 if(!aa1.equals("")){
	        						 aa1=aa1.substring(0,1);
	        					 }
	        					 aa+=aa1;
	        					 if(!bb1.equals("")){
	        						 bb1=bb1.substring(0,1);
	        					 }
	        					 bb+=bb1;
	        					 if(!cc1.equals("")){
	        						 cc1=cc1.substring(0,1);
	        					 }
	        					 cc+=cc1;
	        					 if(!dd1.equals("")){
	        						 dd1=dd1.substring(0,1);
	        					 }
	        					 dd+=dd1;
	        					 if(!ee1.equals("")){
	        						 ee1=ee1.substring(0,1);
	        					 }
	        					 ee+=ee1;
	        					 
	        					 relist.remove(nn);
	        					 nn--;
	        				 }
	        			 }
					   vo.setWorktime(String.valueOf(worktime));
					   vo.setDownsum(String.valueOf(downsum));
					   vo.setPavtime(worktime/downsum/60+"小时"+worktime/downsum%60+"分");
					   Integer.valueOf(vo.getBan01()== null ? 0 : Integer.valueOf(vo.getBan01()));
					   
					   vo.setBan01(String.valueOf(ban01== 0 ? "" : String.valueOf(ban01)));
					   vo.setBan02(String.valueOf(ban02== 0 ? "" : String.valueOf(ban02)));
					   vo.setBan03(String.valueOf(ban03== 0 ? "" : String.valueOf(ban03)));
					   if(!a.equals("")){
						   heji+=1;
					   }if(!b.equals("")){
						   heji+=1;
					   }if(!c.equals("")){
						   heji+=1;
  					   }if(!d.equals("")){
						   heji+=1;
					   }
  					 
  					 if(!e.equals("")){
  						heji+=1;
  					 }
  					 if(!f.equals("")){
  						heji+=1;
  					 }
  					 if(!g.equals("")){
  						heji+=1;
  					 }
  					 if(!h.equals("")){
  						heji+=1;
  					 }
  					 if(!i.equals("")){
  						heji+=1;
  					 }
  					 if(!j.equals("")){
  						heji+=1;
  					 }
  					 if(!k.equals("")){
  						heji+=1;
  					 }
  					 if(!l.equals("")){
  						heji+=1;
  					 }
  					 if(!m.equals("")){
  						heji+=1;
  					 }
  					 if(!n.equals("")){
  						heji+=1;
  					 }
  					 if(!o.equals("")){
  						heji+=1;
  					 }
  					 if(!p.equals("")){
  						heji+=1;
  					 }
  					 if(!q.equals("")){
  						heji+=1;
  					 }
  					 if(!r.equals("")){
  						heji+=1;
  					 }
  					 if(!s.equals("")){
  						heji+=1;
  					 }
  					 if(!t.equals("")){
  						heji+=1;
  					 }
  					 if(!u.equals("")){
  						heji+=1;
  					 }
  					 if(!v.equals("")){
  						heji+=1;
  					 }
  					 if(!w.equals("")){
  						heji+=1;
  					 }
  					 if(!x.equals("")){
  						heji+=1;
  					 }
  					 if(!y.equals("")){
  						heji+=1;
  					 }
  					 if(!z.equals("")){
  						heji+=1;
  					 }
  					 if(!aa.equals("")){
  						heji+=1;
  					 }
  					 if(!bb.equals("")){
  						heji+=1;
  					 }
  					 if(!cc.equals("")){
  						heji+=1;
  					 }
  					 if(!dd.equals("")){
  						heji+=1;
  					 }
  					 if(!ee.equals("")){
  						heji+=1;
  					 }
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
					   vo.setHeji(String.valueOf(heji));

					}
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

package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_car_check_menology;
import com.kj133.entity.vo.Car_check_menologyVO;
import com.kj133.entity.vo.Employee_menologyVO;
import com.kj133.util.Global;

/**
 * 车辆考勤月览表
 * */


public class Car_check_menologyBO {
	private static final Logger log=Logger.getLogger(Car_check_menologyBO.class);
	public Car_check_menologyBO(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List getList(Search_car_check_menology car_check_menology,String name)throws Exception{
		Engine engine=null;
		List relist=null;
		List relist2= new ArrayList();;//合计
		List param=new ArrayList();
		List param2=new ArrayList();//合计
		StringBuffer sb=new StringBuffer();
		StringBuffer sb2=new StringBuffer();//合计
	
		Global bo=new Global();
		int day=bo.getDaysOfMonth(car_check_menology.getStime());
		String first=car_check_menology.getStime()+"-01";//2007-01-01
		String before=bo.getDay(first, -1);
		String second=car_check_menology.getStime()+"-"+String.valueOf(day);//2007-01-31
	 
		sb.append(" select stafferid,ban_id,bantypeid,staffername,cardid,worktype,[1] as a,[2] as b,[3] as c,[4] as d,[5] as e,[6] as f,[7] as g,[8] as h,[9] as i,[10] as j,[11] as k,[12] as l,[13] as m,[14] as n,[15] as o,[16] as p,[17] as q,[18] as r,[19] as s,[20] as t,[21] as u,[22] as v,[23] as w,[24] as x,[25] as y,[26] as z,[27] as aa,[28] as bb,[29] as cc,[30] as dd,[31] as ee from (");
		sb.append(" select a.*,b.shunxu,b.occupation,d.cardmode from (select * from monthattendance where Userid=? ) as a ");
		param.add(name);
		sb.append(" left join staffer b on a.stafferid=b.stafferid ");
		sb.append(" left join recog d on a.cardid=d.cardid");
		sb.append(" ) aaa  where cardMode='车辆' ");
		if( car_check_menology.getCartype() !=null && !car_check_menology.getCartype().equals("")){
			sb.append(" and worktype=? ");
			param.add(car_check_menology.getCartype());
		}
		sb.append(" order by worktype");
		try{
			engine=EngineFactory.getEngine("test");
			engine.executeProcedureCall("{ call CalFinalattendancecar (?,?,?) }",new Object[]{before,second,name});
//			engine.executeProcedureCall("{ call CalFinalmonthcar (?,?) }",new Object[] {first,name});
			engine.executeProcedureCall("{ call CalFinalmonth3 (?,?,?) }",new Object[] {first,second,name});
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Car_check_menologyVO.class);
			
			if( relist != null && relist.size() >0 ){
				int a2 =0;
				int  b2=0;
				int  c2=0;
				int  d2=0;
				int  e2=0;
				int  f2=0;
				int  g2=0;
				int  h2=0;
				int  i2=0;
				int  j2=0;
				int  k2=0;
				int  l2=0;
				int  m2=0;
				int  n2=0;
				int  o2=0;
				int  p2=0;
				int  q2=0;
				int  r2=0;
				int  s2=0;
				int  t2=0;
				int  u2=0;
				int  v2=0;
				int  w2=0;
				int  x2=0;
				int  y2=0;
				int  z2=0;
				int  aa2=0;
				int  bb2=0;
				int  cc2=0;
				int  dd2=0;
				int  ee2=0;
				int  ff2=0;
				
				for(int mm=0;mm<relist.size();mm++){
				   List relist1 = relist;
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
				   Car_check_menologyVO vo=(Car_check_menologyVO)relist.get(mm);
				   vo.setCount(String.valueOf(mm+1));//序号，他是一0开头的，所以要+1
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
					   Car_check_menologyVO vo1=(Car_check_menologyVO)relist1.get(nn);
        				 if(vo.getStafferid().equals(vo1.getStafferid())){
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
				   Integer.valueOf(vo.getBan01()== null ? 0 : Integer.valueOf(vo.getBan01()));
				   
				   vo.setBan01(String.valueOf(ban01== 0 ? "" : String.valueOf(ban01)));
				   vo.setBan02(String.valueOf(ban02== 0 ? "" : String.valueOf(ban02)));
				   vo.setBan03(String.valueOf(ban03== 0 ? "" : String.valueOf(ban03)));
				   if(!a.equals("")){
					   heji+=1;
					   a2 +=1;
				   }if(!b.equals("")){
					   heji += 1;
					   b2 +=1;
				   }if(!c.equals("")){
					   heji +=1;
					   c2 += 1;
				   }if(!d.equals("")){
					   heji+=1;
					   d2 += 1;
				   }if(!e.equals("")){
						heji+=1;
						e2 += 1;
				   }
				   if(!f.equals("")){
					   heji+=1;
					   f2 += 1;
				   }
				   if(!g.equals("")){
					   heji+=1;
					   g2 += 1;
				   }
				   if(!h.equals("")){
					   heji+=1;
					   h2 += 1;
				   }
				   if(!i.equals("")){
					   heji+=1;
					   i2 += 1;
				   }
				   if(!j.equals("")){
					   heji+=1;
					   j2 += 1;
				   }
				   if(!k.equals("")){
					   heji+=1;
					   k2 += 1;
				   }
				   if(!l.equals("")){
					   heji+=1;
					   l2 += 1;
				   }
				   if(!m.equals("")){
					   heji+=1;
					   m2 += 1;
				   }
				   if(!n.equals("")){
					   heji+=1;
					   n2 += 1;
				   }
				   if(!o.equals("")){
					   heji+=1;
					   o2 += 1;
				   }
				   if(!p.equals("")){
					   heji+=1;
					   p2 += 1;
				   }
				   if(!q.equals("")){
					   heji+=1;
					   q2 += 1;
				   }
				   if(!r.equals("")){
					   heji+=1;
					   r2 += 1;
				   }
				   if(!s.equals("")){
					   heji+=1;
					   s2 += 1;
				   }
				   if(!t.equals("")){
					   heji+=1;
					   t2 += 1;
				   }
				   if(!u.equals("")){
					   heji+=1;
					   u2 += 1;
				   }
				   if(!v.equals("")){
					   heji+=1;
					   v2 += 1;
				   }
				   if(!w.equals("")){
					   heji+=1;
					   w2 += 1;
				   }
				   if(!x.equals("")){
					   heji+=1;
					   x2 += 1;
				   }
				   if(!y.equals("")){
					   heji+=1;
					   y2 += 1;
				   }
				   if(!z.equals("")){
					   heji+=1;
					   z2 += 1;
				   }
				   if(!aa.equals("")){
					   heji+=1;
					   aa2 += 1;
				   }
				   if(!bb.equals("")){
					   heji+=1;
					   bb2 += 1;
				   }
				   if(!cc.equals("")){
					   heji+=1;
					   cc2 += 1;
				   }
				   if(!dd.equals("")){
					   heji+=1;
					   dd2 += 1;
				   }
				   if(!ee.equals("")){
					   heji+=1;
					   ee2 += 1;
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
				Car_check_menologyVO cv = new Car_check_menologyVO();
				cv.setCount("合计");
				cv.setA(String.valueOf(a2));
				cv.setB(String.valueOf(b2));
				cv.setC(String.valueOf(c2));
				cv.setD(String.valueOf(d2));
				cv.setE(e2+"");
				cv.setF(f2+"");
				cv.setG(g2+"");
				cv.setH(h2+"");
				cv.setI(i2+"");
				cv.setJ(j2+"");
				cv.setK(k2+"");
				cv.setL(l2+"");
				cv.setM(m2+"");
				cv.setN(n2+"");
				cv.setO(o2+"");
				cv.setP(p2+"");
				cv.setQ(q2+"");
				cv.setR(r2+"");
				cv.setS(s2+"");
				cv.setT(t2+"");
				cv.setU(u2+"");
				cv.setV(v2+"");
				cv.setW(w2+"");
				cv.setX(x2+"");
				cv.setY(y2+"");
				cv.setZ(z2+"");
				cv.setAa(aa2+"");
				cv.setBb(bb2+"");
				cv.setCc(cc2+"");
				cv.setDd(dd2+"");
				cv.setEe(ee2+"");
				relist.add(cv);
				
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

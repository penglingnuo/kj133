package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.DTeee;
import com.kj133.entity.Ouser;
import com.kj133.entity.Recog;
import com.kj133.entity.Staffer;
import com.kj133.entity.Wordlib;
import com.kj133.entity.Wordlib1;
import com.kj133.entity.vo.PageRangeTopleftVO;

public class WordlibBO {
	
	private Logger log=Logger.getLogger(this.getClass());
    public WordlibBO(){
    	
    }
    
    /**
     * 部门(带权限)
     * */
    @SuppressWarnings("unchecked")
     public List yaoqiaoDept(String userid)throws Exception{
     	List relist=null;
     	List param=new ArrayList();
     	StringBuffer sb=new StringBuffer();
     	sb.append(" select distinct s.department wordvalue from staffer s ,reportpopedom r where r.userid=? and s.department=r.department ");
     	param.add(userid);
     	Engine engine=null;
     	 try{
     		 engine=EngineFactory.getEngine("test");
      		 Query query=engine.getQuery();
      	     relist=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
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
     * 获取部门()从staffer表中add by yzh
     * */
   	  public List deptSta(String userid)throws Exception {
   	    	List list=null;    	
   	    	Engine engine=null;
   	    	List param=new ArrayList();
   	    	StringBuffer  sb=new StringBuffer();
   	    	sb.append(" select distinct(st.department) wordvalue from staffer st, reportpopedom rp  where rp.department=st.department and len(st.department)>1 and rp.userid='"+userid+"' ");
   	    	try{
   	    		engine=EngineFactory.getEngine("test");
   	    		Query query=engine.getQuery();
   	    		list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
   	    		engine.commit();
   	    	}catch(Exception e){
   	    		engine.rollback();
   	    		log.error(e);
   	    		throw e;
   	    	}finally{
   	    		engine.closeEngine();
   	    	}
   	    	return list;
   	    }
    
    /**
     * 获取班组
     * */
     @SuppressWarnings("unchecked")
     public List group(Ouser user)throws Exception {
     	List list=null;    	
     	Engine engine=null;
     	List param=new ArrayList();
     	StringBuffer  sb=new StringBuffer();
     	sb.append(" select distinct [group] wordvalue from staffer s ,reportpopedom r where r.userid=? and s.department=r.department ");
     	param.add(user.getUserid());
     	try{
     		engine=EngineFactory.getEngine("test");
     		Query query=engine.getQuery();
     	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
     	    engine.commit();
     	    }catch(Exception e){
     		engine.rollback();
     		log.error(e);
     		throw e;
     	}finally{
     		engine.closeEngine();
     	}
     	return list;
     }
    
    /**
    * 获取工种(职务类型)
    * */
    @SuppressWarnings("unchecked")
	public List workType()throws Exception {
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	sb.append(" select recordid,wordvalue from Wordlib where wordname='工种' and wordvalue <>'' ");
//    	sb.append("select recordid,wordvalue from Wordlib where wordname='工种' and wordvalue <>'' group by wordvalue,recordid");
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
    	    engine.commit();
    	    }catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    }
    /**
     * 获取员工
     * */
    @SuppressWarnings("unchecked")
	 public void getstaffer1()throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
//		 sb.append(" delete workovertime where stafferid in (select stafferid from ( ");
//		 sb.append(" select * from (select stafferid,pinyin,[name],cardid,[group],worktype from staffer) b ");
//		 sb.append(" ) a ) ");
		 sb.append(" delete workovertime from (select stafferid from staffer ) a where workovertime.stafferid=a.stafferid ");
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
	 }
    
    /**
     * 获取员工
     * */
    @SuppressWarnings("unchecked")
    public void getstaffer(String worktype)throws Exception {
    	Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
//		 sb.append(" delete workovertime where stafferid in (select stafferid from ( ");
//		 sb.append(" select * from (select stafferid,pinyin,[name],cardid,[group],worktype from staffer) b ");
//		 sb.append(" where worktype =? ");
//		 param.add(worktype);
//		 sb.append(" ) a ) ");
		 sb.append(" delete workovertime from (select stafferid from staffer where worktype =?) a where workovertime.stafferid=a.stafferid ");
		 param.add(worktype);
		 
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
    }
    
    @SuppressWarnings("unchecked")
    public List carType()throws Exception {
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	sb.append(" select DISTINCT worktype from staffer where [group]='车辆' and worktype <>'' ");
//    	sb.append("select recordid,wordvalue from Wordlib where wordname='工种' and wordvalue <>'' group by wordvalue,recordid");
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		list=query.getResults(sb.toString(),param.toArray(),Staffer.class);
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    }
     
    /**
     * 
     * @return  班次的名字
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List banName()throws Exception {
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	sb.append(" select ban_name as banname from bantype ");
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
    	    engine.commit();
    	    }catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    }
    
    /**
     * 
     * @return  班次的名字new
     * @throws Exception
     */
    public List banNameNew()throws Exception {
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	sb.append(" select ban_name as banname,ban_id as banid from bantype ");
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
    	    engine.commit();
    	    }catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    }
    
    /**
     * 
     * @return  班制的名字new
     * @throws Exception
     */
    public List banZhiNameNew()throws Exception {
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	sb.append(" select bantypename,bantypeid from mainbantype ");
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
    	    engine.commit();
    	    }catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    }
    
    /*
     * 
     * @return  得到地点类型：ID：名称
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List initarea()throws Exception{
		List relist=null;
		List resultlist = new ArrayList();
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
		sb.append(" select '分站' as type,shortname,cardreaderid as [ID] from cardreader where cardreaderid<>0 union all select '定位器',shortname,locatorid from locator ");
		try{

			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),PageRangeTopleftVO.class);

		
			if(relist != null&&relist.size()>0){
				
				
				for(int i =0;i<relist.size();i++){
					
					PageRangeTopleftVO vo = (PageRangeTopleftVO) relist.get(i);
					String type = vo.getType();
					String id = vo.getId();
					String shortname = vo.getShortname();
					String zuhe = type+":"+id+":"+shortname;
					vo.setZuhe(zuhe);
					resultlist.add(vo);

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
		return resultlist;
	}
    
    /**
     * 得到区域类型
     */
    public List areatype()throws Exception{
    	List list=null;    	
    	Engine engine=null;
     	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	sb.append(" select DISTINCT areatype from v_areainfo where areatype <>'' ");
//    	sb.append(" select areatype from v_areainfo where areatype <>'' ");
    	   try{
	    		engine=EngineFactory.getEngine("test");
	    		Query query=engine.getQuery();
	    	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
	    	    engine.commit();
    	    }catch(Exception e){
	    		engine.rollback();
	    		log.error(e);
	    		throw e;
    	
    }finally{
		engine.closeEngine();
	}
	return list;
	
    }
    
    /**
     * 得到区域名称
     */
    public List areaname()throws Exception{
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	
		sb.append(" select areaname from v_areainfo where areaname <>''");
		
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    		
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    	
    }
    /**
     * 得到区域名称1
     */
    public List areaname1()throws Exception{
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	
    	sb.append(" select areaname from areainfo where AreaOrder='一级区域' and areaname <>''");
    	
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    		
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    	
    }
    /**
     * 得到区域id
     */
    public List areaid()throws Exception{
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	
    	sb.append(" select areaid from v_areainfo ");
    	
//    	sb.append(" select areaname from v_areainfo where areaname <>''");
    	
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    		
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    	
    }
    public List areaid_areaname()throws Exception{
    	List list=null;    	
    	List alist=new ArrayList();    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	
    	sb.append(" select areaid,areaname from v_areainfo ");
    	
//    	sb.append(" select areaname from v_areainfo where areaname <>''");
    	
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		list=query.getResults(sb.toString(),param.toArray(),Wordlib1.class);
    		if(list != null&&list.size()>0){
    			for(int i =0;i<list.size();i++){
					List relist1 = list;
					Wordlib1 vo = (Wordlib1) list.get(i);
					String areaid = vo.getAreaid();
					String areaname = vo.getAreaname();
					String id_name = areaid+":"+areaname;
					vo.setId_name(id_name);

					alist.add(vo);

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
    	return alist;
    	
    }
    
    
    /**
     * 组别
     * */
    public List gro(String userid)throws Exception {
    	List list=null;    	
    	Engine engine=null;
     	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	sb.append(" select recordid,wordvalue from Wordlib ,reportpopedom rp  where rp.department=wordvalue and rp.userid='"+userid+"' and  (wordname='组别' or WordName='部门')  and wordvalue <>'参观人员' and wordvalue <>'车辆' and wordvalue <>'' ");
    	   try{
	    		engine=EngineFactory.getEngine("test");
	    		Query query=engine.getQuery();
	    	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
	    	    engine.commit();
    	    }catch(Exception e){
	    		engine.rollback();
	    		log.error(e);
	    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    }
    public List grolist(String userid)throws Exception {
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
    	sb.append(" select recordid,wordvalue from Wordlib ,reportpopedom rp  where rp.department=wordvalue and rp.userid='"+userid+"' and (wordname='组别' or WordName='部门')  and wordvalue <>'' ");
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    }
    /**
     * 日志管理中用户号：
     * */
    public List userid()throws Exception {
    	List list=null;    	
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer  sb=new StringBuffer();
//    	sb.append(" select recordid,wordvalue from Wordlib where wordname='组别' and wordvalue <>'' ");
    	sb.append(" select userid,oname from ouser where userid <>'' ");
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		list=query.getResults(sb.toString(),param.toArray(),Ouser.class);
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    	return list;
    }
   /**
    * 部门
    * */
    public List dept()throws Exception{
    	List relist=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	sb.append(" select  recordid,wordvalue from wordlib where wordname='部门' and wordvalue <>'' ");
    	Engine engine=null;
    	 try{
    		 engine=EngineFactory.getEngine("test");
     		 Query query=engine.getQuery();
     	     relist=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
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
     * 职称
     * */
     public List distinction()throws Exception{
     	List relist=null;
     	List param=new ArrayList();
     	StringBuffer sb=new StringBuffer();
     	sb.append(" select recordid,wordvalue from wordlib where wordname='职称' and wordvalue <>'' ");
     	Engine engine=null;
     	 try{
     		 engine=EngineFactory.getEngine("test");
      		 Query query=engine.getQuery();
      	     relist=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
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
      *职务
      * */
      public List headship()throws Exception{
      	List relist=null;
      	List param=new ArrayList();
      	StringBuffer sb=new StringBuffer();
      	sb.append(" select recordid,wordvalue from wordlib where wordname='职务' and wordvalue <>'' ");
      	Engine engine=null;
      	 try{
      		 engine=EngineFactory.getEngine("test");
       		 Query query=engine.getQuery();
       	     relist=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
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
     *  动态tree
     * */
    public List getTree()throws Exception {
    	List relist=null;
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	sb.append(" select   odepartment.departmentid as id ,odepartment.dname as name ,'0' as pid , '/kj133/add_department.do?method=initializtion'+'&departmentid='+odepartment.departmentid+'&dname='+odepartment.dname+'&depict='+odepartment.Ddescription  as link  from bs_odepartment odepartment " );
    	sb.append(" union "); 
    	//sb.append(" select ogroup.groupid + 'ogroupid' as id,ogroup.gname as name ,ogroup.departmentid  as pid , '/kj133/add_group.do?method=initializtion'+'&groupid='+ogroup.groupid+'&gname='+ogroup.gname +'&gdepict='+ogroup.Gdescription+'&departmentid='+ogroup.Departmentid  as link  from  ogroup   " );
    	//sb.append(" union "); 
    	sb.append("  select  ouser.userid as id,ouser.oname as name,ouser.departmentid as pid ,'/kj133/add_user.do?method=initializtion'+'&uid='+ouser.UserID+'&uname='+ouser.oname+'&password='+ouser.opassword+'&createtime='+convert(varchar(100),ouser.createtime,25)+'&departmentid='+ouser.departmentid  as link   from bs_ouser ouser order by pid  ");
	   	 try{
	   		    engine=EngineFactory.getEngine("test");
	    		Query query=engine.getQuery();
	    	    relist=query.getResults(sb.toString(),param.toArray(),DTeee.class);
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
     *  动态tree
     * */
    public List getTreeold()throws Exception {
    	List relist=null;
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	sb.append(" select   odepartment.departmentid as id ,odepartment.dname as name ,'0' as pid , '/kj133/add_department.do?method=initializtion'+'&departmentid='+odepartment.departmentid+'&dname='+odepartment.dname+'&depict='+odepartment.Ddescription  as link  from odepartment " );
    	sb.append(" union "); 
    	sb.append(" select ogroup.groupid + 'ogroupid' as id,ogroup.gname as name ,ogroup.departmentid  as pid , '/kj133/add_group.do?method=initializtion'+'&groupid='+ogroup.groupid+'&gname='+ogroup.gname +'&gdepict='+ogroup.Gdescription+'&departmentid='+ogroup.Departmentid  as link  from  ogroup   " );
    	sb.append(" union "); 
    	sb.append(" select  ouser.userid as id,ouser.oname as name,ouser.groupid+ 'ogroupid' as pid ,'/kj133/add_user.do?method=initializtion'+'&uid='+ouser.UserID+'&uname='+ouser.oname+'&password='+ouser.opassword+'&createtime='+convert(varchar(100),ouser.createtime,25)+'&departmentid='+ouser.departmentid +'&groupid='+ouser.groupid as link   from bs_ouser ouser order by pid");
	   	 try{
	   		    engine=EngineFactory.getEngine("test");
	    		Query query=engine.getQuery();
	    	    relist=query.getResults(sb.toString(),param.toArray(),DTeee.class);
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
     *  动态tree
     * */
    public List getTree1()throws Exception {
    	List relist=null;
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	sb.append(" select   maplist.mapid as id ,maplist.mapname as name ,'0' as pid , '/kj133/add_department.do?method=initializtion'  as link  from maplist " );
    	sb.append(" union "); 
    	sb.append(" select  ogroup.groupid as id,ogroup.gname as name ,ogroup.departmentid  as pid , '/kj133/add_group.do?method=initializtion'+'&groupid='+ogroup.groupid+'&gname='+ogroup.gname +'&gdepict='+ogroup.Gdescription+'&departmentid='+ogroup.Departmentid  as link  from  ogroup order by pid  " );
//    	sb.append(" union "); 
//    	sb.append(" select  ouser.userid as id,ouser.oname as name,ouser.groupid as pid ,'/kj133/add_user.do?method=initializtion'+'&uid='+ouser.UserID+'&uname='+ouser.oname+'&password='+ouser.opassword+'&createtime='+convert(varchar(100),ouser.createtime,25)+'&departmentid='+ouser.departmentid +'&groupid='+ouser.groupid as link   from ouser order by pid");
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		relist=query.getResults(sb.toString(),param.toArray(),DTeee.class);
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
     * 选择没有员工信息的卡号
     * */
    public List getCardId()throws Exception {
    	List relist=null;
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	sb.append(" select cardid from recog where cardid <>'' and  cardid not in ( select cardid  from staffer  where cardid <>'' and cardid is not null )  " );
	   	try{
   		    engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    	    relist=query.getResults(sb.toString(),param.toArray(),Recog.class);
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
      * Map(小时)
      * */
     @SuppressWarnings("unchecked")
	public Map hour()throws Exception{
    	 Map map=new LinkedHashMap();
    	 for(int i=0;i<=23;i++){
     		map.put(String.valueOf(i),String.valueOf(i));
     	}
    	 return map;
     }
     
     /**
      * Map(分钟)
      * */
     @SuppressWarnings("unchecked")
	public Map minute()throws Exception {
    	 Map map=new LinkedHashMap();
    	 for(int i=0;i<=59;i++){
             map.put(String.valueOf(i),String.valueOf(i)); 
    	 }
    	 return map;
     }
     
     /**
      * 
      * @return  班次的名字new
      * @throws Exception
      */
     public List banNameNewBy(String banname)throws Exception {
     	List list=null;    	
     	Engine engine=null;
     	List param=new ArrayList();
     	StringBuffer  sb=new StringBuffer();
     	sb.append(" select ban_id as banid from bantype where ban_name = '" + banname+ "' ");

     	try{
     		engine=EngineFactory.getEngine("test");
     		Query query=engine.getQuery();
     		//System.out.println("sb="+sb.toString());
     	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
     	    engine.commit();
     	    }catch(Exception e){
     		engine.rollback();
     		log.error(e);
     		throw e;
     	}finally{
     		engine.closeEngine();
     	}
     	return list;
     }
     
     /**
      * 
      * @return  班制的名字new
      * @throws Exception
      */
     public List banZhiNameNewBy(String bantypename)throws Exception {
     	List list=null;    	
     	Engine engine=null;
     	List param=new ArrayList();
     	StringBuffer  sb=new StringBuffer();
     	sb.append(" select bantypeid from mainbantype where bantypename='" + bantypename+ "'");

     	try{
     		engine=EngineFactory.getEngine("test");
     		Query query=engine.getQuery();
     	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
     	    engine.commit();
     	    }catch(Exception e){
     		engine.rollback();
     		log.error(e);
     		throw e;
     	}finally{
     		engine.closeEngine();
     	}
     	return list;
     }
     /**
      * 
      * @return  根据员工编号查询定位卡号
      * @throws Exception
      */
     public List getCardId(String stafferid)throws Exception {
     	List list=null;    	
     	Engine engine=null;
     	List param=new ArrayList();
     	StringBuffer  sb=new StringBuffer();
     	sb.append(" select top 1 cardid from staffer where stafferid='" + stafferid+ "'");

     	try{
     		engine=EngineFactory.getEngine("test");
     		Query query=engine.getQuery();
     	    list=query.getResults(sb.toString(),param.toArray(),Wordlib.class);
     	    engine.commit();
     	    }catch(Exception e){
     		engine.rollback();
     		log.error(e);
     		throw e;
     	}finally{
     		engine.closeEngine();
     	}
     	return list;
     }
} 
 
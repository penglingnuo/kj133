package com.kj133.entity.bo;

 
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Onscene;
import com.kj133.entity.Search_OnLoad;
import com.kj133.entity.vo.On_Load_ParticularVO;
 
public class On_load_leftBO {
	 
   private final Logger log=Logger.getLogger(this.getClass());
	public On_load_leftBO(){
		 
	 }
   @SuppressWarnings("unchecked")
public List init(Search_OnLoad onload,String name)throws Exception{
	   List relist=null;
	   List param=new ArrayList();
       StringBuffer sb=new StringBuffer();
	   Engine engine=null;
	   sb.append("  select * from (select stime,etime,cardreaderid,crname ,Onscenecount,mapid," );
	   sb.append(" mapname  from Onscene where operator= ? ) as QueryTable order by cardreaderid ");
	   param.add(name);
	   String stime=onload.getStime();  
	   String etime=onload.getEtime();  
	   try{
		 engine=EngineFactory.getEngine("test");
		 engine.executeProcedureCall("{ call calOnscene (?,?,?)}",new Object[] {name,stime,etime}); 
		 Query query=engine.getQuery();
		 relist=query.getResults(sb.toString(),param.toArray(),Onscene.class);
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
     * œÍœ∏–≈œ¢
     * */
   @SuppressWarnings("unchecked")
public List particular(String dname,String id)throws Exception{
	   List relist=null;
	   Engine engine=null;
	   StringBuffer sb=new StringBuffer();
	   List param=new ArrayList();
	   sb.append("  select * from (select a.stafferID as sid,b.[name] as name,stime as  ");
	   sb.append(" stime,etime as etime,cardreaderid as cardreaderid from Onscenedata as a,staffer ");
	   sb.append(" as b where a.stafferID=b.stafferID and operator= ? ) as QueryTable  where cardreaderid= ? ");
	   param.add(dname);
	   param.add(id);
	   try{
		   sb.append(" order by QueryTable.stime ");
		   engine=EngineFactory.getEngine("test");
		   Query query=engine.getQuery();
		   relist=query.getResults(sb.toString(),param.toArray(),On_Load_ParticularVO.class);
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

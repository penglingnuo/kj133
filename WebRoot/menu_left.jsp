
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>menu_left.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
     <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
     <link rel="StyleSheet" href="js/dtree.css" type="text/css" />
	 <script type="text/javascript" src="js/dtree.js"></script>
	 <style TYPE="text/css">
			<!--
			A:link{text-decoration:none}
			A:visited{text-decoration:none}
			A:hover {color: #CCCCFF;text-decoration:none}
			 -->
	  </style>
  </head>
  
  <body>
  
    <div class="dtree">
	<p><a href="javascript: d.openAll();">全部打开</a> | <a href="javascript: d.closeAll();">全部关闭</a></p>
      
	<script type="text/javascript">
		<!--
        d = new dTree('d');
		d.add(0,-1,'KJ133人员定位');
		d.add(1,0,'用户管理');
		   d.add(2,1,'帐户管理','/kj133/accountManager.jsp','','content','img/empty.gif');
		   d.add(3,1,'修改密码','/kj133/updatePassword.jsp','','content','img/empty.gif');
	    d.add(4,0,'实时信息')
	       d.add(5,4,'地图管理','/kj133/mapManager.jsp','','content','img/empty.gif');
	       d.add(6,4,'编辑员工','/kj133/employee.do?method=init','','content','img/empty.gif');
	       d.add(7,4,'编辑卡','/kj133/editCard_index.jsp','','content','img/empty.gif');
	       d.add(8,4,'编辑定位器','/kj133/localizer.do?method=initialization','','content','img/empty.gif');
	       d.add(9,4,'编辑分站','/kj133/editViewReader.do?method=initialization','','content','img/empty.gif');
	       d.add(10,4,'分站历史查询','/kj133/viewReaderHistory.do?method=initialization','','content','img/empty.gif');
	       d.add(11,4,'定位器历史查询','/kj133/viewLocatorHistory.do?method=initialization','','content','img/empty.gif');
	       d.add(38,4,'巡检路线设置','/kj133/routDetail.jsp','','content','img/empty.gif');
	    d.add(12,0,'数据查询')
	       d.add(13,12,'验卡记录查询','/kj133/shuaka.do?method=getWordlib','','content','img/empty.gif');
           d.add(14,12,'井下考勤','/kj133/downwell.do?method=getWordlib','','content','img/empty.gif');	       
	       d.add(15,12,'入井次数统计','/kj133/downWellCount.do?method=getWordlib','','content','img/empty.gif');
           d.add(16,12,'活动区域查询','/kj133/moveArea_left.do?method=initialization','','content','img/empty.gif');
           d.add(17,12,'在场信息查询','/kj133/on_load_idnex.jsp','','content','img/empty.gif');
           d.add(18,12,'历史记录查询','/kj133/historyNote.do?method=initialization','','content','img/empty.gif');
           d.add(19,12,'明细记录查询','/kj133/note.do?method=getWordlib','','content','img/empty.gif');
           d.add(20,12,'温度查询','/kj133/temperature.do?method=initialization','','content','img/empty.gif');
           d.add(21,12,'区域停留信息','/kj133/areaStick.do?method=getWordlib','','content','img/empty.gif');
           d.add(40,12,'部门时点查询','/kj133/department_time.do?method=init','','content','img/empty.gif');
           d.add(41,12,'部门时段查询','/kj133/department_day.do?method=init','','content','img/empty.gif');
        d.add(23,0,'人员定位')
           d.add(24,23,'卡所有报警','/kj133/all_help.do?method=getWorkType','','content','img/empty.gif');
           d.add(25,23,'卡电池报警','/kj133/card_batteries.do?method=getWorkType','','content','img/empty.gif');
           d.add(26,23,'定位器电池报警','/kj133/locator_batteries.do?method=iniaialization','','content','img/empty.gif');
           d.add(27,23,'呼救历史查询','/kj133/call_report.do?method=getGroup','','content','img/empty.gif');
           d.add(28,23,'定位器无信号报警','/kj133/noSignal.do?method=initialization','','content','img/empty.gif'); 
		   d.add(29,23,'分站报警查询','/kj133/standHelp.do?method=initialization','','content','img/empty.gif');
		   d.add(30,23,'下井超时报警','/kj133/overTime.do?method=initialization','','content','img/empty.gif');
		d.add(31,0,'考勤报表')
		   d.add(32,31,'班次设置','/kj133/bantype.do?method=initialization','','content','img/empty.gif');
		   d.add(33,31,'员工考勤日报','/kj133/employee_check_daily.do?method=initialization','','content','img/empty.gif');
		   d.add(34,31,'部门考勤日报','/kj133/department_daily.do?method=initialization','','content','img/empty.gif');
		   d.add(35,31,'员工考勤月报','/kj133/employee_menology.do?method=initialization','','content','img/empty.gif');
		   d.add(36,31,'部门考勤月报','/kj133/department_menology.do?method=initialization','','content','img/empty.gif');
		   d.add(37,31,'部门下井月报','/kj133/downwell_menology.do?method=initialization','','content','img/empty.gif');
		document.write(d);
		//--> 
	</script>
</div>
  </body>
</html:html>

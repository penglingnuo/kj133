
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<%@ page import="java.util.*,         
                 java.awt.*,
                 com.kj133.util.Global,
                 com.kj133.entity.vo.*,
                 org.jfree.chart.*,
                 org.jfree.chart.plot.PlotOrientation,
                 org.jfree.chart.servlet.ServletUtilities,
                 org.jfree.data.category.CategoryDataset,
                 org.jfree.data.general.DatasetUtilities,
                 org.jfree.chart.plot.CategoryPlot,
                 org.jfree.chart.axis.*,
                 org.jfree.chart.renderer.category.BarRenderer3D,
                 org.jfree.chart.labels.StandardCategoryItemLabelGenerator,
                 org.jfree.chart.renderer.category.LineAndShapeRenderer,
                 org.jfree.chart.labels.ItemLabelPosition,
                 org.jfree.data.category.DefaultCategoryDataset,
                 org.jfree.ui.ApplicationFrame,
                 org.jfree.ui.RefineryUtilities,
                 org.jfree.chart.axis.AxisLocation"%>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
 
<%
  DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
  java.util.List list = (ArrayList)request.getAttribute("relist");
  Global global = new Global();
  //*******这儿动态循环
  if (list != null && list.size() > 0) {
  String stime = (String)request.getAttribute("stime");
  String etime = (String)request.getAttribute("etime");
  String sstime = stime.substring(0,10);
  String eetime = etime.substring(0,10);
    String times = "";
    for (int i = 0; i < list.size(); i++){
        String times1= "";
        Manamount_queryVO vo=(Manamount_queryVO)list.get(i);
        String  pcount= vo.getPeoplecount();
        
        String  tss = vo.getRecordtime();
        
        times1 = times;
        times  = tss.substring(0,10);
              
        if(times1.equals("")){
            if(i == 0){
            	if(sstime.equals(times)){
            		linedataset.addValue(Integer.parseInt(pcount),"",times);
            	}else{
            		int aaa = global.getDaysBetween(sstime,times);
            		int bbb = 0;
            		linedataset.addValue(0,"",sstime);
	          	    for(int y=0;y<aaa-1;y++){
		               bbb = y+1;
		               String times4 = global.getDay(sstime,+bbb);
			           linedataset.addValue(0,"",times4);
		           
	           		}
	          	    linedataset.addValue( Integer.parseInt(pcount),"",times);
            	}
            }
            
        }else{
        	if(times1.equals(times)){
            	linedataset.addValue( Integer.parseInt(pcount),"",times);
        	}
        
    		if(times1.equals(global.getDay(times, -1))){
            	linedataset.addValue( Integer.parseInt(pcount),"",times);
	        }
			if(!times1.equals(global.getDay(times, -1))){
	           int a = global.getDaysBetween(times1,times);
	           for(int j=0;j<a-1;j++){
	               int b = j+1;
	               String times2 = global.getDay(times1,+b);
		           linedataset.addValue(0,"",times2);
		           
	           }
	           linedataset.addValue( Integer.parseInt(pcount),"",times);
	        }
	        if(i == list.size()-1){
	        	if(!times.equals(eetime)){
	        		int aa = global.getDaysBetween(times,eetime);
		            for(int x=0;x<aa;x++){
		               int bb = x+1;
		               String times3 = global.getDay(times1,+bb);
			           linedataset.addValue(0,"",times3);
		           }
	        	}
	        }
       } 
 	}
 }
  
  //  linedataset.addValue(200.0,"",String.valueOf(list.get(0)))
	
 //这儿是坐标上的字体,自己试试....
JFreeChart jfreechart = ChartFactory.createLineChart("全矿人数曲线图", // chart title
    "时间", // domain axis label
    "人数", // range axis label
    linedataset, // data
    PlotOrientation.VERTICAL, // orientation
    false, // include legend
    false, // tooltips
    false // urls
    );

jfreechart.setBackgroundPaint(Color.white);

CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
categoryplot.setBackgroundPaint(Color.white);
categoryplot.setRangeGridlinePaint(Color.green);
categoryplot.setDomainGridlinesVisible(true);  // x轴 // 分类轴网格是否可见 
categoryplot.setRangeGridlinesVisible(true);  // y轴 //数据轴网格是否可见 

CategoryPlot plot = jfreechart.getCategoryPlot();
CategoryAxis domainAxis = plot.getDomainAxis();
domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的45度倾斜
NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
numberaxis.setAutoRangeIncludesZero(false);
//获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer)categoryplot.getRenderer();

lineandshaperenderer.setShapesVisible(false); //series 点（即数据点）可见
lineandshaperenderer.setSeriesStroke(0, new BasicStroke(1.0F, 1, 1, 1.0F, new float[] {10F, 1F}, 0.0F));
//定义series为”First”的（即series1）点之间的连线 ，这里是虚线，默认是直线

  String filename = ServletUtilities.saveChartAsPNG(jfreechart, 1100, 600, null, session);
  String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
%>
<html>
<head>
	
	<script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
	<%--时间--%>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<link href="Css/calendar-blue.css" rel="stylesheet">
	

	<script language="javascript" src="js/StafferORName.js"></script>

<title></title>
<script language="javascript">
  function printtu(printpage){
    var headstr = "<html><head><title></title></head><body>";
    var footstr = "</body>";
    var newstr = document.all.item(printpage).innerHTML;
    var oldstr = document.body.innerHTML;
    document.body.innerHTML = headstr+newstr+footstr;
    window.print();
    document.body.innerHTML = oldstr;
    return false;
  }
 
    function check(){
      
	  var  stime=document.all['manamount_query.stime'].value;
	  var  etime=document.all['manamount_query.etime'].value;
	  var  aname=document.all['manamount_query.areaname'].value;
	  var stime1 = new Date(stime.substring(0,4),stime.substring(5,7),stime.substring(8,10),stime.substring(11,13),stime.substring(14,16),stime.substring(17,19)); 
      var etime1 = new Date(etime.substring(0,4),etime.substring(5,7),etime.substring(8,10),etime.substring(11,13),etime.substring(14,16),etime.substring(17,19)); 
      
      var DyMilli = 1000 * 60 * 60 * 24 

      var days = Math.round((etime1.getTime()-stime1.getTime()) / DyMilli);
	  
	  if( stime >= etime ){
	       alert('起始时间不能大于或等于截止时间');
	      
	       return false;
        }if(aname==""){
         alert('区域不能为空');
         return false;
       }if(days>31){
         alert('起始时间与截止时间不能超过31天');
         return false;
       }
           return true;
          
       }  
          
</script>
</head>
<body topmargin="0" leftmargin="0" background="Image/right.gif">
  <html:form action="manamount_query?method=init"  onsubmit="return check()">
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    
    <tr>
      <td align="center" valign="top">
          <table width="98%" border="0" cellspacing="2" cellpadding="0">
              <tr>
                <td valign="top">
                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td valign="top">
                      <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                          <tr>
                           <td>折线图</td>
                          </tr>
                        </table></td>
                    </tr>
                       
                          <tr>
                            <td  valign="top">
                        <br/>
              <div id="gzycyqk">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                    <tr>
                      <td align="center">
                        <img src="<%= graphURL %>"  border=0 usemap="#<%= filename %>">
                      </td>
                    </tr>
                    <tr>
                     <td align="center" height="25" valign="bottom">
                          统计时间
                     </td>
                   </tr>
                   
                </table>
                </div>
                </td>
                 </tr>
                        
              
                   <tr>
                      <td height="35" valign="middle">
                        <table width="550" border="0" cellspacing="0" cellpadding="0">
                <tr>
				
					<td align="left">起始日期：</td>		
<%--					<html:text property="manamount_query.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('manamount_query.stime')})"/>	--%>
					<td align="left">
					<html:text property="manamount_query.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('manamount_query.stime')})"/>	
					&nbsp;&nbsp;
					</td>
				<td align="left">截止日期：</td>		
					<td align="left">
					<html:text property="manamount_query.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('manamount_query.etime')})"/>			
					&nbsp;&nbsp;
					</td>
				
				
				    <td align="left">区 域：</td>	   
				    <td align="left">         
					<html:select property="manamount_query.areaname">
		                <html:option value=""></html:option>
						<html:options collection="areaname_list" property="areaname"
							labelProperty="areaname" />
					</html:select>
					&nbsp;&nbsp;
				    </td>
					<td align="left">
					<html:submit>查 询</html:submit>
					</td>
			</tr>
                </table>
                     </td>
                    </tr>
                  </table>
               </td>
          </tr>
       </table>
     </td>
   </tr>
 </table>
</html:form>
</body>
</html>


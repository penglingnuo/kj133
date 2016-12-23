package com.kj133.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class ReportCompile {

	 public static void main(String[] args)
	  {
	    try
	    {
	      System.out.println("Compiling report...");
	      JasperCompileManager.compileReportToFile("jxmlToJasper/downWell.jrxml");
		  // //生成一个jasper文件
	      System.out.println("Done!");
	    }
	    catch (JRException e)
	    {
	      e.printStackTrace();
	    }
	  }
}

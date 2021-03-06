/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.kj133.action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.kj133.entity.CardReaderState;
import com.kj133.entity.Ouser;
import com.kj133.entity.Search_TrackShow;
import com.kj133.entity.TrackShow;
import com.kj133.entity.bo.TrackBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.TrackVO;

/** 
 * MyEclipse Struts
 * Creation date: 07-23-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class TrackAction extends DispatchAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request 定位跟踪
	 * @param response
	 * @return ActionForward
	 */

	private final Logger log = Logger.getLogger(this.getClass());

	private DataInputStream in;

	private DataOutputStream out;

	private String keyWords;//关键字

	private Long length;//长度

	private int readerCount;//分站总数

	private Long readerID;//分站ID

	private String temperature;//温度

	private String status;//分站状态

	private Long cardCount;//卡的总数

	private String cID;

	private Long cardID;//卡ID

	private Long locatorID;//定位器ID

	private Integer state;//报警状态

	private String antenna;//天线号

	private String ip = null;

	@SuppressWarnings("unchecked")
	public ActionForward trackQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordlibBO lib = new WordlibBO();
		DynaActionForm dy = (DynaActionForm) form;
		try {
			Search_TrackShow tshow = (Search_TrackShow) dy.getMap().get("search_trackshow");
			byte[] send = new byte[24];
			send[0] = 22; //单个字节的最高位是127
			send[1] = (byte) 240;//240转换成二进制1111 0000(82，其中8是高位，但是在电脑里面2是高位)
			send[2] = 17;
			send[3] = 60;//之所先先写，17是因为，keyWords是word类型，他读的时候是从后往前面读
			send[4] = 74; //'j' ascii码
			send[5] = 97;
			send[6] = 99;
			send[7] = 107;

			//			        ip="192.168.0.3";  //192.168.0.3 
			ip = "192.168.1.21"; //192.168.0.3 
			Socket socket = new Socket(ip, 34567);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			out.write(send);
			out.flush();

			int c;
			List result_list = new ArrayList();
			TrackBO bo = new TrackBO();
			List staffer_List = bo.getStaffer();//员工信息
			List cardreader_List = bo.getCardReader();//分站
			// List locator_List=bo.getLocator();//定位器
			List state_List = bo.getState();//状态信息
			List cardreaderList = new ArrayList();//分站基本信息
			TrackShow show = null;
			byte[] receive = new byte[65535]; //定义一个长度为9000000的字节数组，因为不知道要读字节的长度是多少，所以是尽量的写大
			while ((c = in.read(receive, 0, 65535)) >= 0) //从0开始读，读到9000000,如果太大的话，那么就会抛出异常 Caused by: java.lang.OutOfMemoryError: Java heap space
			{
				for (int i = 0; i < c; i++) {

					keyWords = Integer.toHexString(receive[1])+ Integer.toHexString(receive[0]);//关键字  
					int receive3 = add256(Integer.parseInt(String.valueOf(receive[3])));
					int receive2 = add256(Integer.parseInt(String.valueOf(receive[2])));
					String byteLength = Integer.toHexString(receive3)+ Integer.toHexString(receive2);
					length = Long.parseLong(byteLength, 16); //长度

					
					readerCount = Integer.parseInt(String.valueOf(receive[4]));//分站数

					//String time=Integer.toHexString(receive[5]); //时间

					int sum = 13;
					for (int k = 0; k < readerCount; k++) {//分站循环		            			     
						String rID = Integer.toHexString(receive[sum + 1])+ Integer.toHexString(receive[sum]);
						readerID = Long.parseLong(rID, 16);//分站ID

						temperature = String.valueOf(receive[sum + 2]); // 温度

						status = String.valueOf(receive[sum + 3]); //分站状态

						String card = Integer.toHexString(add256(Integer.parseInt(String.valueOf(receive[sum + 5]))))+ Integer.toHexString(add256(Integer.parseInt(String.valueOf(receive[sum + 4]))));
						cardCount = Long.parseLong(card, 16); //卡总数

						//---------------当前分站人数
						TrackVO cardReaderState = (TrackVO) cardreader_List.get(Integer.parseInt(String.valueOf(readerID)));//分站状态
						CardReaderState cardreadrstate = new CardReaderState(cardReaderState.getId(), cardReaderState.getShortname(), temperature, String.valueOf(cardCount));
						cardreaderList.add(cardreadrstate);

						sum = sum + 6;
						
						for (int j = 0; j < cardCount; j++) {//按卡总数循环
							
							Integer cardOne = Integer.parseInt(String
									.valueOf(receive[sum]));
							if (cardOne < 0) {//只要有一个数为负数，转换就有错误，比如 42 -9 =》102245552 那么就应该把-9转换成-9+256
								
								cardOne = cardOne + 256; //143
								
								if (cardOne < 10) //03 48 转换后是3和48,所以需要加"0"
								{
									cID = Integer.toHexString(receive[sum + 1])+ "0"+ Integer.toHexString(cardOne);
								} else {
									cID = Integer.toHexString(receive[sum + 1])+ Integer.toHexString(cardOne);
									//十进制转换成十六进制
									//368f
								}
							} else {
								if (cardOne < 16)//15转换成16进制是F
								{
									cID = Integer.toHexString(receive[sum + 1])+ "0"+ Integer.toHexString(cardOne);
								} else {
									cID = Integer.toHexString(receive[sum + 1])+ Integer.toHexString(receive[sum]);
								}
							}
							cardID = Long.parseLong(cID, 16); //卡号  16进制的368f转换成10禁止 	                	   
							String locaID = Integer.toHexString(add256(Integer.parseInt(String.valueOf(receive[sum + 3]))))+ Integer.toHexString(add256(Integer.parseInt(String.valueOf(receive[sum + 2]))));
							locatorID = Long.parseLong(locaID, 16); //定位器号
							state = add256(Integer.parseInt(String.valueOf(receive[sum + 4]))); //卡状态

							antenna = String.valueOf(receive[sum + 5]); //天线号

							// System.out.println("  卡的最后信号时间== "+String.valueOf(receive[sum+6])+String.valueOf(receive[sum+7])+String.valueOf(receive[sum+8])+String.valueOf(receive[sum+9]));
							// System.out.println("  最后记录分站时间== "+String.valueOf(receive[sum+10])+String.valueOf(receive[sum+11])+String.valueOf(receive[sum+12])+String.valueOf(receive[sum+13]));
							// System.out.println("  入井时间== "+String.valueOf(receive[sum+14])+String.valueOf(receive[sum+15])+String.valueOf(receive[sum+16])+String.valueOf(receive[sum+17]));
							sum = sum + 20;
							
							TrackVO vo = (TrackVO) staffer_List.get(Integer.parseInt(String.valueOf(cardID)));//员工
							TrackVO cardreader_vo = (TrackVO) cardreader_List.get(Integer.parseInt(String.valueOf(readerID)));//分站
							//TrackVO locator_vo=(TrackVO)locator_List.get(Integer.parseInt(String.valueOf(locatorID)));
							TrackVO state_vo = (TrackVO) state_List.get(state);//状态信息

							String gro = tshow.getGro();//班组
							//System.out.println("gro="+gro);
							String worktype = tshow.getWorktype();//工种
							//System.out.println("worktype="+worktype);
							String sid = tshow.getSid();//员工号
							//System.out.println("sid="+sid);
							String cid = tshow.getCid();//分站号
							//System.out.println("cid="+cid);
							String name = tshow.getUsername();//姓名
							//System.out.println("name="+name);
							if ((gro == null || "".equals(gro))
									&& (worktype == null || "".equals(worktype))
									&& (cid == null || "".equals(cid))
									&& (sid == null || "".equals(sid))
									&& (name == null || "".equals(name))) {

								gro = "1";
								worktype = "1";
								cid = "1";
								sid = "1";
								name = "1";
								show = new TrackShow(vo.getWorktype(), vo
										.getId(), vo.getUsername(),
										vo.getGro(), cardreader_vo.getId(),
										cardreader_vo.getShortname(), null,
										null, antenna, state_vo.getInfo());
								result_list.add(show);
								request.setAttribute("downCount", result_list
										.size());

							} else {
								show = new TrackShow(vo.getWorktype(), vo
										.getId(), vo.getUsername(),
										vo.getGro(), cardreader_vo.getId(),
										cardreader_vo.getShortname(), null,
										null, antenna, state_vo.getInfo());

								if (gro != null && !gro.equals("")) {//班组不为空
									if (show.getGro().equals(gro)) {
										TrackShow groNotNull = new TrackShow(vo
												.getWorktype(), vo.getId(), vo
												.getUsername(), vo.getGro(),
												cardreader_vo.getId(),
												cardreader_vo.getShortname(),
												null, null, antenna, state_vo
														.getInfo());
										result_list.add(groNotNull);
										
									}
							    
								} else if (worktype != null
										&& !worktype.equals("")) {//工种不为空
									if (show.getWorktype().equals(worktype)) {
										TrackShow worktypeNotNull = new TrackShow(
												vo.getWorktype(), vo.getId(),
												vo.getUsername(), vo.getGro(),
												cardreader_vo.getId(),
												cardreader_vo.getShortname(),
												null, null, antenna, state_vo
														.getInfo());
										result_list.add(worktypeNotNull);

									}
								} else if (cid != null && !cid.equals("")) {//分站不为空
									if (show.getCardreaderid().equals(cid)) {
										TrackShow cidNotNull = new TrackShow(vo
												.getWorktype(), vo.getId(), vo
												.getUsername(), vo.getGro(),
												cardreader_vo.getId(),
												cardreader_vo.getShortname(),
												null, null, antenna, state_vo
														.getInfo());
										result_list.add(cidNotNull);
										
									}
								} else if (sid != null && !sid.equals("")) {//员工号不为空
									if (show.getCardid().equals(sid)) {
										TrackShow sidNotNull = new TrackShow(vo
												.getWorktype(), vo.getId(), vo
												.getUsername(), vo.getGro(),
												cardreader_vo.getId(),
												cardreader_vo.getShortname(),
												null, null, antenna, state_vo
														.getInfo());
										result_list.add(sidNotNull);

									}
								} else if (name != null && !name.equals("")) {//姓名不为空
									if (show.getUsername().equals(name)) {
										TrackShow sidNotNull = new TrackShow(vo
												.getWorktype(), vo.getId(), vo
												.getUsername(), vo.getGro(),
												cardreader_vo.getId(),
												cardreader_vo.getShortname(),
												null, null, antenna, state_vo
														.getInfo());
										result_list.add(sidNotNull);

									}
								} else {
									System.out.println("");
								}
							}
						}
					}
					/*response.setContentType("text/xml;charset=UTF-8");
					 response.getWriter().write(parseToXML(result_list));
					 response.getWriter().flush();
					 response.getWriter().close();  */
					 Ouser user=(Ouser)request.getSession().getAttribute("user");
					List gro = lib.gro(user.getUserid());
					List type = lib.workType();
					request.setAttribute("gro_list", gro);
					request.setAttribute("workType_list", type);
					request.setAttribute("trackShow", result_list);
					if (result_list.size() > 0) {
						request.setAttribute("downCount", result_list.size());
					} else {
						request.setAttribute("downCount", "0");
						ActionMessages messages = new ActionMessages();
						messages.add(ActionMessages.GLOBAL_MESSAGE,
								new ActionMessage("no.count"));
						this.saveMessages(request, messages);
					}
					request.setAttribute("readerList", cardreaderList);//把分站的信息发送到页面上
					return mapping.findForward("trackShow");//trackShow
				}
			}
		} catch (Exception e) {
			log.error("--- 采集机连接错误：人员定位 ---", e);
			request.setAttribute("ipAddress", ip);
			return mapping.findForward("srror");
		}
		return null;
	}

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dy = (DynaActionForm) form;
		WordlibBO lib = new WordlibBO();
		try {

			Search_TrackShow tshow = (Search_TrackShow) dy.getMap().get(
					"search_trackshow");
			Ouser user=(Ouser)request.getSession().getAttribute("user");
			List gro = lib.gro(user.getUserid());
			List type = lib.workType();
			request.setAttribute("gro_list", gro);
			request.setAttribute("workType_list", type);
			dy.set("search_trackshow", tshow);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("trackShow");
	}

	public int add256(int b) throws Exception {//一个字节的最高位是127
		int a = 0;
		try {
			if (b < 0) {
				a = b + 256;
			} else {
				a = b;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public String parseToXML(List list) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<track>");
		for (int i = 0; i < list.size(); i++) {
			TrackShow show = (TrackShow) list.get(i);
			sb.append("<track>");
			sb.append("<worktype>" + show.getWorktype() + "</worktype>");//工种
			sb.append("<cardid>" + show.getCardid() + "</cardid>");//卡号
			sb.append("<username>" + show.getUsername() + "</username>");//姓名
			sb.append("<gro>" + show.getGro() + "</gro>");//班组
			sb.append("<cardreaderid>" + show.getCardreaderid()
					+ "</cardreaderid>");//所属分站
			sb.append("<shortname>" + show.getShortname() + "</shortname>");//分站名称
			sb.append("<locatorid>" + show.getLocatorid() + "</locatorid>");//所属定位器
			sb.append("<lname>" + show.getLname() + "</lname>");//定位器名称
			sb.append("<antenna>" + show.getAntenna() + "</antenna>");//天线    
			sb.append("<state>" + show.getState() + "</state>");//状态信息
			
			sb.append("</track>");
		}
		sb.append("</track>");
		return sb.toString();
	}
}

/*List staList=new ArrayList();//统计班组和人数
 staList.add(vo);
 List Group = new ArrayList();
 Group.add(vo);//加了他，否则进不去 for循环

 for(int sta=0;sta<staList.size();sta++){
 boolean flag = false;	
 TrackVO stavo=(TrackVO) staList.get(sta);
 
 for(int pp=0;pp<Group.size();pp++){
 
 TrackVO group_ = (TrackVO) Group.get(pp);
 System.out.println(group_.getGro());
 if(group_.getGro().toString().equals(stavo.getGro().toString())){
 group_.setCardid(group_.getCardid()+1);//这个是统计数量
 System.out.println( group_.getCardid());
 flag = true;
 break;
 }
 }
 if(!flag){
 TrackVO group_s = new TrackVO();
 group_s.setGro(group_s.getGro());
 group_s.setCardid("1");
 Group.add(group_s);
 } 
 }
 
 for(int mm=0;mm<Group.size();mm++){
 TrackVO group_sm=(TrackVO ) Group.get(mm);
 System.out.println(group_sm.getGro()+" 465 "+group_sm.getCardid());
 
 }*/

/*
 问题是在这里List list=new ArrayList(); 
 list必须是静态变量，你每次都new一个新的  
 肯定只有一条数据 
 new就是每一次执行都会重新分配内存空间 
 private static List list = new ArrayList();
 放到action头定义试试  放到  public class xxx { 下面 
 */

/**
 *    response.setContentType("text/xml");
 PrintWriter out=response.getWriter();
 out.println(parseToXML(result_list)); 
 */

/**
 * 关键字    word类型（2个字节）
 * 数据长度   word类型
 * 分站的总数 1个字节
 * 8个字节是时间类型
 * 按分站总数循环
 *   {
 *     分站id 2个字节
 *     分站温度 1个字节
 *     分站状态 1个字节
 *     卡的总数 2个字节
 *        按卡数循环 
 *          { 
 *            2个字节  卡号
 *            2个字节  定位器号
 *            1个字节  卡状态
 *            2个字节   工作异常
 *            1个字节  天线号
 *            卡的最后信号时间  4个字节
 *            最后记录分站时间  4个字节
 *            入井时间  4个字节
 *          }
 *   }
 * */

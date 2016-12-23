//var html  = "<DIV id=eMeng style='BORDER-RIGHT: #455690 1px solid; BORDER-TOP: #a6b4cf 1px solid; Z-INDEX:99999; LEFT: -5px; VISIBILITY: hidden; BORDER-LEFT: #a6b4cf 1px solid; WIDTH: 255px; BORDER-BOTTOM: #455690 1px solid; POSITION: absolute; TOP: 0px; HEIGHT: 154px; BACKGROUND-COLOR: #c9d3f3'> ";
//    html += "	<TABLE style='BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid' cellSpacing=0 cellPadding=0 width='100%' bgColor=#cfdef4 border=0> ";
//    html += "		<TBODY> ";
//    html += "			<TR> ";
//    html += "		        <TD style='FONT-SIZE: 12px; BACKGROUND-IMAGE: url(msgTopBg.gif); COLOR: #0f2c8c' width=30 height=24></TD> ";
//    html += "		        <TD style='FONT-WEIGHT: normal; FONT-SIZE: 12px; BACKGROUND-IMAGE: url(msgTopBg.gif); COLOR: #1f336b; PADDING-TOP: 4px;PADDING-left: 4px' vAlign=center width='100%'> 退出提示：</TD> ";
//    html += "		        <TD style='BACKGROUND-IMAGE: url(msgTopBg.gif); PADDING-TOP: 2px;PADDING-right:2px' vAlign=center align=right width=19><span title=关闭 style='CURSOR: hand;color:red;font-size:12px;font-weight:bold;margin-right:4px;' onclick=closeDiv() >×</span> ";
//    html += "		        </TD> ";
//    html += "		    </TR> ";
//    html += "		    <TR> ";
//    html += "		        <TD style='PADDING-RIGHT: 1px; BACKGROUND-IMAGE: url(1msgBottomBg.jpg); PADDING-BOTTOM: 1px' colSpan=3 height=90> ";
//    html += "			        <DIV style='BORDER-RIGHT: #b9c9ef 1px solid; PADDING-RIGHT: 13px; BORDER-TOP: #728eb8 1px solid; PADDING-LEFT: 13px; FONT-SIZE: 12px; PADDING-BOTTOM: 13px; BORDER-LEFT: #728eb8 1px solid; WIDTH: 100%; COLOR: #1f336b; PADDING-TOP: 18px; BORDER-BOTTOM: #b9c9ef 1px solid; HEIGHT: 100%'> ";
//    html += "			            <p>对不起！您的账号现在另一台电脑登录。请您重新登录后，修改您的密码。确保您的账号安全。 </p> ";
//    html += "			        </DIV> ";
//    html += "		        </TD> ";
//    html += "		    </TR> ";
//    html += "	    </TBODY> ";
//    html += "	</TABLE> ";
//    html += "</DIV>";

window.onload = getMsg;
window.onresize = resizeDiv;
window.onerror = function(){}
//短信提示使用(asilas添加)
var divTop,divLeft,divWidth,divHeight,docHeight,docWidth,objTimer,i = 0;
function getMsg(){
	try{
		divTop = parseInt(document.getElementById("eMeng").style.top,10)
		divLeft = parseInt(document.getElementById("eMeng").style.left,10)
		divHeight = parseInt(document.getElementById("eMeng").offsetHeight,10)
		divWidth = parseInt(document.getElementById("eMeng").offsetWidth,10)
		docWidth = document.body.clientWidth;
		docHeight = document.body.clientHeight;
		document.getElementById("eMeng").style.top = parseInt(document.body.scrollTop,10) + docHeight + 10;// divHeight
		document.getElementById("eMeng").style.left = parseInt(document.body.scrollLeft,10) + docWidth - divWidth
		document.getElementById("eMeng").style.visibility="visible"
		objTimer = window.setInterval("moveDiv()",10)
	}
	catch(e){}
}
function resizeDiv(){
	i+=1
	if(i>500) closeDiv()
	try{
		divHeight = parseInt(document.getElementById("eMeng").offsetHeight,10)
		divWidth = parseInt(document.getElementById("eMeng").offsetWidth,10)
		docWidth = document.body.clientWidth;
		docHeight = document.body.clientHeight;
		document.getElementById("eMeng").style.top = docHeight - divHeight + parseInt(document.body.scrollTop,10)
		document.getElementById("eMeng").style.left = docWidth - divWidth + parseInt(document.body.scrollLeft,10)
	}
	catch(e){}
}
function moveDiv(){
	try{
		if(parseInt(document.getElementById("eMeng").style.top,10) <= (docHeight - divHeight + parseInt(document.body.scrollTop,10))){
		window.clearInterval(objTimer)
		objTimer = window.setInterval("resizeDiv()",1)
	}
	divTop = parseInt(document.getElementById("eMeng").style.top,10)
	document.getElementById("eMeng").style.top = divTop - 1
	}
	catch(e){}
}
function closeDiv(){
	document.getElementById('eMeng').style.visibility='hidden';
	if(objTimer) window.clearInterval(objTimer)
}
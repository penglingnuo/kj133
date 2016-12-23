/**
*初始化页面的Form加载事件
*/
var searchType = 1;

$(function(){
	$("#searchButton").bind("click", function(){
		$.ajax({
			type: "POST",
			url: "workperson.do?method=searchContent&tempId=" + Math.random() * 1000000,
			data: "c=" + $("#keyWord").val() + "&s=" + searchType,
			error: function() {alert("Loading Error");},
			beforeSend: function(){
				
			},
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				if(searchType == 1) {
					findDataByCardid(json);
				}else if(searchType == 2) {
					findDataByStafferId(json);
				}else if(searchType == 3) {
					findDataByStafferId(json);
				}else if(searchType == 4) {
					findDataByCardReader(json);
				}
			}
		}); 
	});
});

//根绝卡号找数据
function findDataByCardid(json) {
	$("#dataTable tr[r='o']").remove();
				
	$(json.list).each(function(i, n){
		var row = $("#dataTemplate").clone();
		row.find("#1").text(n.crname);
		row.find("#2").text(n.intime);
		row.find("#3").text(n.outtime);
		
		row.attr("r", "o");
		row.removeAttr("id");
		row.show();
		$("#dataTable").append(row);
	});
}

//根据用户号找数据
function findDataByStafferId(json) {
	$("#dataTable tr[r='o']").remove();
	
	if(json.list == "未绑定") {
		var row = $("#dataTemplate").clone();
		row.find("#1").html("&nbsp;");
		row.find("#2").text("未绑定");
		row.find("#3").html("&nbsp;");
		
		row.attr("r", "o");
		row.show();
		$("#dataTable").append(row);
	}else {
		$(json.list).each(function(i, n){
			var row = $("#dataTemplate").clone();
			row.find("#1").text(n.crname);
			row.find("#2").text(n.intime);
			row.find("#3").text(n.outtime);
			
			row.attr("r", "o");
			row.show();
			$("#dataTable").append(row);
		});
	}
}

//根据基站查找数据
function findDataByCardReader(json) {
	$("#dataTableByCardreader tr[r='o']").remove();
	
	$(json.list).each(function(i, n){
		var row = $("#dataTemplate1").clone();
		row.find("#1").text(n.cardid);
		row.find("#2").text(n.starttime);
		row.find("#3").text(n.endtime);
		
		row.attr("r", "o");
		row.show();
		$("#dataTableByCardreader").append(row);
	});
}
/**
 * 分界线
 * 以上是对搜索的方法
 * 以下是对输入框的方法
 * */

$(function(){
//	$("#keyWord").bind("keyup", function(){
//		var index = $("#select option[selected]").val();
//		
//		if(index == 1){
//			findByCardid();
//		}else if(index == 2) {
//			findByStafferId();
//		}else if(index == 3) {
//			findByPinYin();
//		}else if(index == 4) {
//			findByCardReader();
//		}else if(index == 5) {
//			findByLocator();
//		}
//	});
});

function findByCardid() {
	$.ajax({
		type: "POST",
		url: "suggest.do?method=suggestName&name=" + $("#keyWord").val(),
		error: function() {alert("获取数据失败!");},
		dataType: "xml",
		beforeSend : function(){
		},
		success: function(msg){
			$("#cardname_table_body tr").remove();
			
			$(msg.getElementsByTagName("student")).each(function(i, n){
				var nextNode = n.getElementsByTagName("cardname").context.text;
                row = document.createElement("tr");
                cell = document.createElement("td");
                
                cell.onmouseout = function() {this.className='mouseOver';};
                cell.onmouseover = function() {this.className='mouseOut';};
                cell.setAttribute("bgcolor", "#FFFAFA");
                cell.setAttribute("border", "0");
                cell.onclick = function() { populateName(this); } ;                             

                txtNode = document.createTextNode(nextNode);
                cell.appendChild(txtNode);
                row.appendChild(cell);
                
                $("#cardname_table_body").append(row);
			});
		}
	});
}

function findByStafferId() {
//	alert("findByStafferId");
}

function findByPinYin() {
//	alert("findByPinYin");
}

function findByCardReader() {
//	alert("findByCardReader");
}

function findByLocator() {
//	alert("findByLocator");
}

function selectChange(obj) {
	searchType = obj.value;
	
	if(searchType == 4) {
		$("#dataTableByCardreader").show();
		$("#dataTable").hide();
	}else {
		$("#dataTableByCardreader").hide();
		$("#dataTable").show();
	}
}
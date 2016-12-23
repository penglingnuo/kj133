$(function(){
	$("#searchButton").bind("click", function(){
	
		$("#dataTable tr[r='o']").remove();
		
		var tempRow = $("#loadingGif").clone();
		$("#dataTable").append(tempRow);
	
		$.ajax({
			type: "POST",
			url: "workperson.do?method=searchContent&tempId=" + Math.random() * 1000000,
			data: "c=" + $("#stafferSelect").val(),
			error: function() {alert("Loading Error");},
			beforeSend: function(){
				
			},
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				tempRow.remove();
				
				$(json.list).each(function(i, n){
					var row = $("#dataTemplate").clone();
					row.find("#1").html(n.staffername);
					row.find("#2").html(n.group);
					row.find("#3").html(n.cardid);
					row.find("#4").html(n.downtime);
					row.find("#5").html(n.betweentime);
					row.find("#6").html(n.crname);
					row.find("#7").html(n.lname);
					row.find("#8").html(n.intime);
					row.find("#9").html(n.betweentimein);
					row.find("#10").html(n.state);
				
					row.bind("click", function(){
						var url = "telezone/map.jsp?mapid="+n.mapid+"&type=1&cardid=" + n.cardid;
						parent.rightFrame.setCardID(n.cardid);
						
						//if(n.staffername == "未绑定") {
						//	url = url + "?mapid="+n.mapid+"&type=1&cardid=" + n.cardid;
						//}else {
						//	url = url + "?mapid="+n.mapid+"&type=1&cardid=" + n.cardid;
						//}
						
						if(n.cwebx == "0" && n.lwebx != "0") {
							url = url + "&x="+n.lx+"&y="+n.ly;
						}else {
							url = url + "&x="+n.crx+"&y="+n.cry;
						}
						
						parent.frames["content"].location.href = url;
					});
				
					if(i % 2 == 0) {
						row.css("background-color","#DBE2F5");
					}
					row.css("cursor", "hand");
					row.hover(function(){
						row.css("background-color","#C5D6FC");
					},function(){
						if(i % 2 == 0) {
							row.css("background-color","#DBE2F5");
						}else {
							row.css("background-color","");
						}
					});
					
					row.show();
					row.attr("r", "o");
					$("#dataTable").append(row);
				});
			}
		}); 
	});
});
$(function(){
	$("#addWorkAttendanceEx").click(function(){
		var cardid = $("#stafferSelect").val();
		var staffer = $("#stafferSelect option[selected='true']").attr("svalue");
		var starttime = $("#starttime").val();
		var endtime = $("#endtime").val();
		var downcardreader = $("#downccardreader").val();
		var downlocator = $("#downlocator").val();
		var upcardreader = $("#upcardreader").val();
		var uplocator = $("#uplocator").val();

		var postdata = "c="+cardid+"&s="+staffer+"&st="+starttime+"&et="+endtime+"&dc="+downcardreader+"&dl"+downlocator+"&uc="+upcardreader+"&ul="+uplocator;
		
		$.ajax({
			type: "POST",
			url: "common.do?method=addWorkattendanceEx&tempId=" + Math.random() * 1000000,
			data: postdata,
			error: function() {alert("Loading Error");},
			beforeSend: function() {
				if($("#stafferSelect option").length === 0) {
					alert("请选择人员");
					return false;
				}
				
				if(starttime.length === 0) {
					alert("开始时间不能为空");
					return false;
				}
				
				if(starttime > endtime) {
					alert("开始时间大于结束时间");
					return false;
				}
			},
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				if(json.flag == '1') {
					alert("修改成功");
					window.location.reload();
				}
			}
		});
	});
});

$(function(){
	$("#keyWord").bind("propertychange", function(){
		$.ajax({
			type: "POST",
			url: "common.do?method=staffer&tempId=" + Math.random() * 1000000,
			data: "c=" + $("#keyWord").val(),
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				$("#stafferSelect option").remove();
				
				$(json.staffer).each(function(i, n){
					$("#stafferSelect").append("<option value='"+ n.cardId +"' svalue = '" + n.stafferid + "'>" + n.name + " - "+ n.department + "</option>"); 
				});
			}
		});
	});
});
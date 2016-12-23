$(function(){
	$("#keyWord").bind("click", function(){
		$.ajax({
			type: "POST",
			url: "common.do?method=staffer&tempId=" + Math.random() * 1000000,
			data: "c=" + $("#keyWord").val(),
			success: function(msg){
				
			}
		}); 
	});
});
$(function(){
	$("#keyword").bind("propertychange", function(){
		$.ajax({
			type: "POST",
			url: "common.do?method=staffer&tempId=" + Math.random() * 1000000,
			data: "c=" + $("#keyword").val(),
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				$("#stafferSelect option").remove();
				
				$(json.staffer).each(function(i, n){
					$("#stafferSelect").append("<option value='"+ n.cardId +"'>" + n.name + " - "+ n.department + "</option>"); 
				});
			}
		});
	});
});
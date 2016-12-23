var ExportXls = (function($){
	var $_EX_iframeId = "_EX_IFRAME_";
	
	function $_EX_createIframe() {
		var iframe = $("body > iframe[name="+$_EX_iframeId+"]");
		if(!iframe.size())
			$("body").append("<iframe name='"+$_EX_iframeId+"' style='display:none;'></iframe>");
	}
	
	return function(form, url){
		$_EX_createIframe();
		
		if(typeof(url) == 'undefined') {
			url = form;
			$("body form").each(function(){
				if(!$(this).parents("iframe").size()) {
					form = this;
				}
			});
		}
		
		if(!form)
			throw new Error("can't found the form to provide parameters");
		
		var f = $(form).clone();
		
		f.attr("action", url);
		f.attr("target", $_EX_iframeId);
		f.css("display", "none");
		f.appendTo($("body"));
		
		f.submit();
		f.remove();
	};
})(jQuery);
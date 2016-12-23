
function controlNotNull(control, controlShowName)
{
	if(control.type == "select-one") {
		if(!validateNotNull(control.options[control.options.selectedIndex].value)) {
			alert("???" + controlShowName + "!");
			control.focus();
			return false;
		}
	}else{
		control.value = control.value.replace(/(^\s*)|(\s*$)/g, "");
		if(!validateNotNull(control.value)) {
			alert(controlShowName + "\u4e0d\u80fd\u4e3a\u7a7a");
			if(control.type != "hidden") {
				control.focus();
			}
			return false;
		}
	}
	return true;
}

function controlIsInt(control, controlShowName) {
	control.value = control.value.replace(/(^\s*)|(\s*$)/g, "");
	if(!validateIsInt(control.value)) {
		alert("\u8bf7\u8f93\u5165\u6570\u5b57"+"!");
		if(control.type != "hidden") {
				control.focus();
		}
		return false;
	}
	return true;
}

function validateNotNull(strObj) {
	strObj = strObj.replace(/(^\s*)|(\s*$)/g, "");
	if(strObj.length > 0){
		return true;
	}else{
		return false;
	}
}

function validateIsInt(strObj) {
	if(!validateNotNull(strObj)){
		return true;
	}
	reg = /^[1-9][0-9]{0,}$/;
	return reg.test(strObj);
}
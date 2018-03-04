//lhgDialog弹出框
function open_dialog(title,url,width,height,zIndex){
	if(!width) {
		width = 600;
	}
	if(!height) {
		height = 400;
	}
	if(!zIndex) {
		zIndex = 1976;
	}
 	$.dialog({
   		id     : "edit_form",
   		title  : title,
   		max    : false, 
   		min    : false,
   		lock   : true,
   		esc    : false, // 禁止按 ESC关闭
   		width  : width,
   		height : height,
   		fit    : true,
   		zIndex : zIndex,
   		content: "url:" + url
   	});
};

//菜单名称生成拼音码
function getPinyinCode(moduleName,key) {
	if(moduleName != null && moduleName != "") {
		$.ajax({
        	type: 'post',
			url : 'moduleController.do?getPinyinCode',
           	data : "moduleName=" + moduleName,
           	dataType : 'json',
	        success: function(data) {
	           if (data && data.result.success == true) {
	                   // $('#moduleList').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	                   $('#' + key).val(data.pinyinCode);
	           } else {
	                   $.messager.alert('错误提示',data.result.msg,'error');
	           }
	        },
	        error : function(data) {
	           alert("error");
	        }
       });
	}
};

//日期格式化
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
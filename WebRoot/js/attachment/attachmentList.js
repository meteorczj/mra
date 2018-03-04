
$(function() {
	//加载数据字典列表
	$('#attachmentList').datagrid({
	    nowrap: true,  
	    autoRowHeight: false,
	    fitColumns : true,
	    nowrap :true,
	    striped: true,
		iconCls:'icon-bumen',
		url:'attachmentController/datagrid.do?case_id=' + case_id,
		idField:'id',
		loadMsg:'',
		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : true,
		fit:true,//设置自适应高度和宽度
	    columns:[[
	        {title:'id',field:'id',width:1,hidden:true},
	        {field:'ck',checkbox:true,width:2},
	        {field:'file_name',title:'文件名',width:60},  
	        {field:'file_type',title:'文件格式',width:30},
	        {field:'file_path',title:'文件路径',width:80},
	        {field:'attachment',title:'操作',width:30,align:'center',
	        	formatter: function(value,row,index){
	        		return "<a class='color:blue;text-decoration:underline;' href='attachmentController/download.do?file_name=" + row.file_name + "&file_path=" + row.file_path + "'>下载</a>"
	        	}
	        }
    	]],
	    pagination:true,  
	    rownumbers:true,
		pageSize:10,
		loadFilter: function(data){
			if (data.pageInfo){
				return data.pageInfo.map;
			} else {
				return data;
			}
		},
		toolbar:[{
			id:'btnadd',
			text:'上传',
			iconCls:'icon-add',
			handler:function(){
				attachment_upload();
			}
		},'-',{
			id:'tndelete',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				attachment_delete();
			}
		}],
		onLoadSuccess:function(data){
			$('#attachmentList').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#attachmentList').datagrid('clearChecked');
		}
	});
	
	var p = $('#attachmentList').datagrid('getPager');  
	$(p).pagination({
		displayMsg:'当前显示从{from}到{to}共{total}记录',
	    onBeforeRefresh:function(){  
	        //$('#attachmentList').datagrid('reload');
	    }  
	});
	
	/**
	 * 附件上传页面
	 */
	function attachment_upload() {
		//open_dialog("附件信息",'attachmentController/uploadList.do?case_id=' + case_id, 510, 550, 1980);
		var id = "upload";
		var title = "附件信息";
		var url = 'attachmentController/uploadList.do?case_id=' + case_id;
		var width = 510;
		var height = 550;
		var zIndex = 1980;
		$.dialog({
	   		id     : id,
	   		title  : title,
	   		max    : false, 
	   		min    : false,
	   		lock   : true,
	   		esc    : false, // 禁止按 ESC关闭
	   		width  : width,
	   		height : height,
	   		fit    : true,
	   		zIndex : zIndex,
	   		parent : api,
	   		content: "url:" + url,
	   		close : function() {
	   			api.content.$("#attachmentList").datagrid("reload");
	   		}
	   	});
	}
	
	/**
	 * 批量删除 
	 */
	function attachment_delete() {
		var rows =  $('#attachmentList').datagrid("getChecked");
		
		if(rows.length == 1) {
			$.messager.confirm('提示','确定要删除吗?',function(result){
		        if (result){
		        	$.ajax({
						type: 'post',
						url : 'attachmentController/delete.do',
						dataType : 'json',
						data : {id : rows[0].id, file_path : rows[0].file_path},
					    success: function(data) {
		        			if (data && data.result.success == true) {
		            			$('#attachmentList').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		            		} else {
		            			$.messager.alert("提示",data.result.msg,"error");
		            		}
					    }
					});
	        	}
	    	});
		} else {
			$.messager.alert("提示","请选择一条记录!","info");
		}
	}
	
});
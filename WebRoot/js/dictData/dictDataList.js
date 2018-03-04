
$(function() {
	//加载数据字典列表
	$('#dictDataList').datagrid({
	    nowrap: true,  
	    autoRowHeight: false,
	    fitColumns : true,
	    nowrap :true,
	    striped: true,
		iconCls:'icon-bumen',
		//url:'dictDataController.do?datagrid',
		idField:'id',
		loadMsg:'',
		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : true,
		fit:true,//设置自适应高度和宽度
	    columns:[[
	        {title:'id',field:'id',width:1,hidden:true},
	        {field:'ck',checkbox:true,width:2},
	        {field:'code',title:'数据字典编码',width:70},  
	        {field:'name',title:'数据字典名称',width:150}
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
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				dictData_add();
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				//deleteDictTypeRow();
			}
		},'-',{
			id:'btnedit',
			text:'更新',
			iconCls:'icon-edit',
			handler:function(){
				dictData_edit();
			}
		}],
		onLoadSuccess:function(data){
			$('#dictDataList').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#dictDataList').datagrid('clearChecked');
		}
	});
	
	var p = $('#dictDataList').datagrid('getPager');  
	$(p).pagination({
		displayMsg:'当前显示从{from}到{to}共{total}记录',
	    onBeforeRefresh:function(){  
	        //$('#dictDataList').datagrid('reload');
	    }  
	});
	
	//数据字典新增
	function dictData_add() {
		open_dialog("新增字典信息",'dictDataController.do?addorupdate&group_id=' + dictTypeId + "&group_code=" + dictTypeCode);
	}

	//数据字典编辑
	function dictData_edit() {
		var rows = $('#dictDataList').datagrid('getChecked');
		if(rows.length == 1) {
			open_dialog("编辑字典信息",'dictDataController.do?addorupdate&id=' + rows[0].id + "&group_code=" + rows[0].code);
		} else {
			//$.messager.alert('提示',"请选择一条记录!",'info');
			$.dialog.tips("请选择一条记录!",1.5,'tips.gif');
		}
	}
	
});
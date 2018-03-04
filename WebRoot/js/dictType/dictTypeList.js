
var dictTypeId = "";
var dictTypeCode = "";

$(function() {
	//加载数据字典分类列表
	$('#dictTypeList').datagrid({
	    nowrap: true,  
	    autoRowHeight: false,
	    fitColumns : true,
	    nowrap :true,
	    striped: true,
		iconCls:'icon-bumen',
		url:'dictTypeController.do?datagrid',
		idField:'id',
		loadMsg:'',
		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : true,
		fit:true,//设置自适应高度和宽度
	    columns:[[
	        {title:'id',field:'id',width:1,hidden:true},
	        {field:'ck',checkbox:true,width:2},
	        {field:'code',title:'分类编码',width:70},  
	        {field:'name',title:'分类名称',width:100},
	        {field:'view',title:'操作',width:50,align:'center',
	        	formatter: function(value, row, index){
	        		return "<a href='javascript:void(0);' onclick='dictData_list(\"" + row.id + "\",\"" + row.code + "\");'>[查看字典]</a>"
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
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				dictType_add();
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
				dictType_edit();
			}
		}],
		onLoadSuccess:function(data){
			$('#dictTypeList').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#dictTypeList').datagrid('clearChecked');
		},
		onDblClickRow:function(index,row) {
			dictType_edit();
		}
		/*onClickRow:function(rowIndex,rowData) {
			dictTypeId = rowData.id;
			dictTypeCode = rowData.code;
			
			$('#dictDataList').datagrid({
				url:'dictDataController.do?datagrid',
				loadMsg : '数据加载中...',
				queryParams: {
					group_id: dictTypeId
				}
			})
		}*/
	});
	
	var p = $('#dictTypeList').datagrid('getPager');  
	$(p).pagination({
		displayMsg:'当前显示从{from}到{to}共{total}记录',
	    onBeforeRefresh:function(){  
	        //$('#dictTypeList').datagrid('reload');
	    }  
	});
	
	//菜单新增
	function dictType_add() {
		open_dialog("新增菜单信息",'dictTypeController.do?addorupdate');
	}

	//菜单编辑
	function dictType_edit() {
		var rows = $('#dictTypeList').datagrid('getChecked');
		if(rows.length == 1) {
			open_dialog("编辑菜单信息",'dictTypeController.do?addorupdate&id=' + rows[0].id);
		} else {
			//$.messager.alert('提示',"请选择一条记录!",'info');
			$.dialog.tips("请选择一条记录!",1.5,'tips.gif');
		}
	}
	
});

//查看数据字典
function dictData_list(id,code) {
	dictTypeId = id;
	dictTypeCode = code;
	
	$('#dictDataList').datagrid({
		url:'dictDataController.do?datagrid',
		loadMsg : '数据加载中...',
		queryParams: {
			group_id: dictTypeId
		}
	});
};

var parentId = "";//定义全局的父菜单id

$(function() {
	//加载菜单树
	$('#moduleTreeList').tree({
			checkbox : false,
			lines:true,
			url : 'moduleController.do?getModuleTree',
			onLoadSuccess : function(node) {
				// expandAll();
			},
			onClick: function(node){
				parentId = node.id
				var leaf = $('#areaTreeList').tree("isLeaf",node.target);
				if(!leaf) {
					$('#moduleList').datagrid("uncheckAll");//由于使用checkbox做为更新选择的判断，所以加载时，先去除所选checkbox
					$('#moduleList').datagrid({
						url:'moduleController.do?datagrid',
						loadMsg : '数据加载中...',
						queryParams: {
							parent: parentId,
						}
					})
				}
			}
		});
		
	function expandAll() {
		var node = $('#moduleTreeList').tree('getSelected');
		if (node) {
			$('#moduleTreeList').tree('expandAll', node.target);
		} else {
			$('#moduleTreeList').tree('expandAll');
		}
	};
	
	$("#module-panel").panel({
		tools : [{
			iconCls:'icon-reload',
			handler:function() {
				$('#moduleTreeList').tree('reload');
			}
		}]
	});
	
	//加载菜单列表
	$('#moduleList').datagrid({
	    nowrap: true,  
	    autoRowHeight: false,
	    fitColumns : true,
	    nowrap :true,
	    striped: true,
		iconCls:'icon-bumen',
		url:'moduleController.do?datagrid',
		idField:'id',
		loadMsg:'',
		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : true,
		fit:true,//设置自适应高度和宽度
	    columns:[[
	        {title:'id',field:'id',width:1,hidden:true},
	        {field:'ck',checkbox:true,width:2},
	        {field:'title',title:'菜单名称',width:70},  
	        {field:'url',title:'菜单地址',width:150},
	        {field:'index',title:'菜单顺序',width:50},
	        {field:'depth',title:'菜单等级',width:50,
	        	formatter: function(value,row,index){
					if (row.depth == 0){
						return "一级菜单";
					} else if(row.depth == 1) {
						return "二级菜单";
					} else if(row.depth == 2) {
						return "三级菜单";
					}
				}
	        },
	        {field:'isGroup',title:'页子节点',width:50,
	        	formatter: function(value,row,index){
					if (row.isGroup == 0){
						return "否";
					} else {
						return "是";
					}
				}
	        },
	        {field:'isInvoked',title:'是否启用',width:50,
	        	formatter: function(value,row,index){
					if (row.isInvoked == 0){
						return "停用";
					} else {
						return "启用";
					}
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
				module_add();
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				//deleteModuleRow();
			}
		},'-',{
			id:'btnedit',
			text:'更新',
			iconCls:'icon-edit',
			handler:function(){
				module_edit();
			}
		}],
		onLoadSuccess:function(data){
			$('#moduleList').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#moduleList').datagrid('clearChecked');
		}
	});
	
	var p = $('#moduleList').datagrid('getPager');  
	$(p).pagination({
		displayMsg:'当前显示从{from}到{to}共{total}记录',
	    onBeforeRefresh:function(){  
	        //$('#moduleList').datagrid('reload');
	    }  
	});
	
});

//数据字典分类新增
function module_add() {
	open_dialog("新增分类信息",'moduleController.do?addorupdate&parent=' + parentId);
}

//数据字典分类编辑
function module_edit() {
	var rows = $('#moduleList').datagrid('getChecked');
	if(rows.length == 1) {
		open_dialog("编辑分类信息",'moduleController.do?addorupdate&id=' + rows[0].id);
	} else {
		//$.messager.alert('提示',"请选择一条记录!",'info');
		$.dialog.tips("请选择一条记录!",1.5,'tips.gif');
	}
}


$(function() {
	//加载病历列表
	$('#caseList').datagrid({
	    nowrap: true,  
	    autoRowHeight: false,
	    fitColumns : false,
	    nowrap :true,
	    striped: true,
		iconCls:'icon-bumen',
		url:'caseController.do?datagrid',
		idField:'id',
		loadMsg:'',
		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : true,
		fit:true,//设置自适应高度和宽度
	    columns:[[
	        {title:'id',field:'id',width:1,hidden:true},
	        {field:'ck',checkbox:true,width:2},
	        {field:'hosp_num',title:'住院号',width:70,align:'center'},  
	        {field:'name',title:'姓名',width:60,align:'center'},
	        {field:'sex',title:'性别',width:40,align:'center',
	        	formatter: function(value,row,index){
	        		if(row.sex == 1) {
	        			return '男'
	        		} else {
	        			return '女'
	        		}
	        	}
	        },
	        {field:'age',title:'年龄',width:40,align:'center'},
	        {field:'address',title:'地址',width:150,align:'left'},
	        {field:'inhosp_date',title:'入院日期',width:65,align:'center',
	        	formatter: function(value,row,index){
	        		if (row.inhosp_date){
						var date = new Date(row.inhosp_date);
						return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
					} else {
						return value;
					}
	        	}
	        },
	        {field:'sick_date',title:'发病时间',width:65,align:'center',
	        	formatter: function(value,row,index){
	        		if (row.sick_date){
						var date = new Date(row.sick_date);
						return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
					} else {
						return value;
					}
	        	}
	        },
	        {field:'hosp_days',title:'住院天数',width:60,align:'right'},
	        {field:'charge_money',title:'总费用',width:70,align:'right'},
	        {field:'is_lung',title:'肺栓塞',width:50,align:'center',
	        	formatter: function(value,row,index){
	        		if(row.is_lung == 0) {
	        			return '否';
	        		} else if(row.is_lung == 1) {
	        			return '是';
	        		}
	        	}
	        },
	        {field:'is_cta',title:'肺动脉CTA',width:60,align:'center',
	        	formatter: function(value,row,index){
	        		if(row.is_cta == 0) {
	        			return '否';
	        		} else if(row.is_cta == 1) {
	        			return '是';
	        		}
	        	}
	        },
	        {field:'thrombus_part',title:'血栓部位',width:150,align:'left'},
	        {field:'thrombus_type',title:'血栓类型',width:150,align:'left'},
	        {field:'thrombus_reason',title:'血栓原因',width:200,align:'left'}
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
				case_add();
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				case_delete();
			}
		},'-',{
			id:'btnedit',
			text:'更新',
			iconCls:'icon-edit',
			handler:function(){
				case_edit();
			}
		},'-',{
			id:'btnsearch',
			text:'检索',
			iconCls:'icon-search',
			handler:function(){
				case_search();
			}
		}],
		onLoadSuccess:function(data){
			$('#caseList').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#caseList').datagrid('clearChecked');
		},
		onDblClickRow:function(index,row) {
			case_edit();
		}
	});
	
	var p = $('#caseList').datagrid('getPager');  
	$(p).pagination({
		displayMsg:'当前显示从{from}到{to}共{total}记录',
	    onBeforeRefresh:function(){  
	        //$('#caseList').datagrid('reload');
	    }  
	});
	
	//病历新增
	function case_add() {
		open_dialog("新增病历信息",'caseController.do?addorupdate',900,500);
	}

	//病历编辑
	function case_edit() {
		var rows = $('#caseList').datagrid('getChecked');
		if(rows.length == 1) {
			open_dialog("编辑病历信息", 'caseController.do?addorupdate&id=' + rows[0].id, 900, 500);
		} else {
			$.dialog.tips("请选择一条记录!",1.5,'tips.gif');
		}
	}
	
	//病历删除，同时删除病历下附件信息
	function case_delete() {
		var rows = $('#caseList').datagrid('getChecked');
		if(rows.length == 1) {
			$.messager.confirm('提示','确定要删除吗?',function(result){
		        if (result){
		        	$.ajax({
						type: 'post',
						url : 'caseController/delete.do',
						dataType : 'json',
						data : {id:rows[0].id},
					    success: function(data) {
		        			if (data && data.result.success == true) {
		            			$('#caseList').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		            		} else {
		            			$.messager.alert("提示",data.result.msg,"error");
		            		}
					    }
					});
	        	}
	    	});
		} else {
			$.dialog.tips("请选择一条记录!",1.5,'tips.gif');
		}
	}
	
	//病历检索
	function case_search() {
		var params = $('#caseList').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#searchCaseForm').serializeArray(); //自动序列化表单元素为JSON对象
		
		$.each(fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		});
		$('#caseList').datagrid('reload',params); //设置好查询参数 reload 一下就可以了
	}
	
});
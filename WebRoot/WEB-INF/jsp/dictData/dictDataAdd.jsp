<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/context/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>

<link type="text/css" href="plug-in/Validform/css/tablefrom.css" rel="stylesheet">

<script type="text/javascript">
var api = frameElement.api, W = api.opener;

$(function() {

});

function save_dictData() {
    var r = $('#dictDataForm').form('validate');
	if(!r) {
		return false;
	}

	//提交表单 
	$('#dictDataForm').ajaxSubmit({
		url:'dictDataController/save.do', //提交给哪个执行
		type:'post',
		dataType:'json',
		success: function(data){
	        if (data && data.result.success == true) {
				api.close();
				W.$('#dictDataList').datagrid('reload');
			} else {
				$.messager.alert('错误提示',data.result.msg,'error');
			}
		}
	}); 
};
</script>
</head>

<body>
<div style="width:100%;" class="easyui-layout" fit="true">
<div region="center" class="detail-view-container">
	<div class="datagrid-toolbar">
		<a href="javascript:void(0);" id="btn-save" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="save_dictData();">保存</a>
		<a href="javascript:void(0);" id="icon-cancel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="frameElement.api.close();">关闭</a>
	</div>
	
	<form id="dictDataForm" method="post">
		<input name="id" type="hidden" value="${entity.id }">
		<input name="group_id" type="hidden" value="${entity.group_id }">
		<input name="group_code" type="hidden" value="${entity.group_code }">
		
		<table class="detail-view" cellspacing="1" cellpadding="0">
		<tbody>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 字典编码: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 200px;height: 25px;" type="text" name="code" id="code" value= "${entity.code }" required="true" validType="length[1,25]">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 字典名称: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 200px;height: 25px;" type="text" name="name" id="name" value="${entity.name }" required="true" validType="length[1,25]">
			</td>
		</tr>
	   </tbody>
	   </table>
	</form>
</div>
</div>

</body>
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
	var isGroup = '${entity.isGroup}';
	var isInvoked = '${entity.isInvoked}';
	if(isGroup != null && isGroup != "") {
		$("#isGroup").val(isGroup);
	}
	if(isInvoked != null && isInvoked != "") {
		$("#isInvoked").val(isInvoked);
	}
});

function save_module() {
    var r = $('#moduleForm').form('validate');
	if(!r) {
		return false;
	}

	//提交表单 
	$('#moduleForm').ajaxSubmit({
		url:'moduleController.do?save&type=module', //提交给哪个执行
		type:'post',
		dataType:'json',
		success: function(data){
	        if (data && data.result.success == true) {
				api.close();
				W.$('#moduleList').datagrid('reload');
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
		<a href="javascript:void(0);" id="btn-save" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="save_module();">保存</a>
		<a href="javascript:void(0);" id="icon-cancel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="frameElement.api.close();">关闭</a>
	</div>

	<form id="moduleForm" method="post" enctype="multipart/form-data">
		<input name="id" type="hidden" value="${entity.id }">
		
		<table class="detail-view" cellspacing="1" cellpadding="0">
		<tbody>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 菜单名称: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 200px;height: 25px;" type="text" name="title" id="title" value="${entity.title }" required="true" validType="length[1,15]" onblur="getPinyinCode(this.value,'pinyin_code');">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 拼音码: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 200px;height: 25px;" type="text" name="name" id="pinyin_code" value="${entity.pinyin_code }" required="true" validType="length[1,100]">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 菜单URL: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 200px;height: 25px;" type="text" name="url" id="url" value="${entity.url }" validType="length[1,128]">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 菜单图标: </label>
			</td>
			<td class="item-value">
				<input class="easyui-filebox" name="imageFile" type="file" style="width: 200px;height: 25px;">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 菜单顺序: </label>
			</td>
			<td class="item-value">
				<input class="easyui-numberbox" style="width: 200px;height: 25px;" name="index" value="${entity.index == null ? index : entity.index }" type="text" min="1" required="true">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 上级菜单: </label>
			</td>
			<td class="item-value">
				<input id="cc" class="easyui-combotree" name="parent" value="${entity.parent == null ? parentId : entity.parent }" data-options="url:'moduleController.do?getModuleTree&isRecursive=true'" style="width: 200px;height: 25px;">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 页子节点: </label>
			</td>
			<td class="item-value">
				<select name="isGroup" id="isGroup" class="select" style="width: 200px;height: 25px;">
	    			 <option value="0" selected="selected">否</option>
	    			 <option value="1">是</option>
	   			</select>
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 是否启用: </label>
			</td>
			<td class="item-value">
				<select name="isInvoked" id="isInvoked" class="select" style="width: 200px;height: 25px;">
	    			 <option value="0" >停用</option>
	    			 <option value="1" selected="selected">启用</option>
	   			</select>
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 备&nbsp;&nbsp;&nbsp;&nbsp;注: </label>
			</td>
			<td class="item-value">
				<textarea class="easyui-validatebox" style="width: 250px;" rows="3" name="remark" value="${entity.remark }" validType="length[0,255]"></textarea>
			</td>
		</tr>
	   </tbody>
	   </table>
	</form>
</div>
</div>

</body>
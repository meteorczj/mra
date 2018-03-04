<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/context/mytags.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>
<script type="text/javascript" src="js/attachment/attachmentList.js"></script>

<script type="text/javascript">
var api = frameElement.api, W = api.opener;
var case_id = '${entity.id}';

$(function() {
	var date = new Date();
	date = date.Format('yyyy/MM/dd');
	var sick_date = '${entity.sick_date}';
	if(sick_date) {
		sick_date = new Date(sick_date).Format('yyyy/MM/dd');
		$('#sick_date').datebox('setValue', sick_date);
	} else {
		$('#sick_date').datebox('setValue', date);
	}
	var inhosp_date = '${entity.inhosp_date}';
	if(inhosp_date) {
		inhosp_date = new Date(inhosp_date).Format('yyyy/MM/dd');
		$('#inhosp_date').datebox('setValue', inhosp_date);
	} else {
		$('#inhosp_date').datebox('setValue', date);
	}
	
	
	//
	$('#sex').combobox({
		url:'dictDataController/getDictDataList.do?group_code=sex',
	    valueField:'code',
	    textField:'name',
	    onSelect:function(record) {
	    	$('#sex').val(record.name);
	    }
	});
	$('#sex').combobox('setValue',1);
});

function save_case() {
    var r = $('#caseForm').form('validate');
	if(!r) {
		return false;
	}

	//提交表单 
	$('#caseForm').ajaxSubmit({
		url:'caseController/save.do', //提交给哪个执行
		type:'post',
		dataType:'json',
		success: function(data){
	        if (data && data.result.success == true) {
				api.close();
				W.$('#caseList').datagrid('reload');
			} else {
				$.messager.alert('错误提示',data.result.msg,'error');
			}
		}
	}); 
};

/**
 * 附件下载
 */
function attachment_download() {
	var rows =  $('#attachmentList').datagrid("getChecked");
	
	if(rows.length == 1) {
		window.location.herf = 'attachmentController/download.do?id=' + rows[0].id + "&file_name=" + rows[0].file_name + "&file_path=" + rows[0].file_paht;
	} else {
		$.messager.alert("提示","请选择一条记录!","info");
	}
}
</script>
</head>

<body>
<div style="width:100%;" class="easyui-layout" fit="true">
<div region="north" class="detail-view-container">
	<div class="datagrid-toolbar">
		<a href="javascript:void(0);" id="btn-save" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="save_case();">保存</a>
		<a href="javascript:void(0);" id="icon-cancel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="frameElement.api.close();">关闭</a>
	</div>
	
	<form id="caseForm" method="post" style="margin: 0px">
		<input name="id" type="hidden" value="${entity.id }">
		<input name="is_add" type="hidden" value="${is_add }">
		<table class="detail-view" cellspacing="1" cellpadding="0">
		<tbody>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 住院号: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 155px;height: 25px;" type="text" name="hosp_num" id="hosp_num" value="${entity.hosp_num }" required="true">
			</td>
			<td class="item-title">
				<label class="Validform_label"> 姓名: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 155px;height: 25px;" type="text" name="name" id="name" value="${entity.name }" required="true">
			</td>
			<td class="item-title">
				<label class="Validform_label">性别: </label>
			</td>
			<td class="item-value">
				<input class="easyui-combobox" style="width: 155px;height: 25px;" type="text" name="sex" id="sex" editable="false">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 年龄: </label>
			</td>
			<td class="item-value">
				<input class="easyui-numberbox" style="width: 155px;height: 25px;" type="text" name="age" id="age" value="${entity.age }" data-options="min:0,max:999">
			</td>
			<td class="item-title">
				<label class="Validform_label"> 地址: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 155px;height: 25px;" type="text" name="address" id="address" value="${entity.address }">
			</td>
			<td class="item-title">
				<label class="Validform_label">入院时间: </label>
			</td>
			<td class="item-value">
				<input class="easyui-datebox" style="width: 155px;height: 20px;" editable="false" 
					data-options="formatter: function(date){ return date.Format('yyyy/MM/dd');}" name="inhosp_date" id="inhosp_date"></input>
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 血栓部位: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 155px;height: 25px;" type="text" name="thrombus_part" id="thrombus_part" value="${entity.thrombus_part }">
			</td>
			<td class="item-title">
				<label class="Validform_label"> 血栓类型: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 155px;height: 25px;" type="text" name="thrombus_type" id="thrombus_type" value="${entity.thrombus_type }">
			</td>
			<td class="item-title">
				<label class="Validform_label"> 血栓原因: </label>
			</td>
			<td class="item-value">
				<input class="easyui-validatebox" style="width: 155px;height: 25px;" type="text" name="thrombus_reason" id="thrombus_reason" value="${entity.thrombus_reason }">
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 发病时间: </label>
			</td>
			<td class="item-value">
				<input class="easyui-datebox" style="width: 155px;height: 20px;" editable="false" 
					data-options="formatter: function(date){ return date.Format('yyyy/MM/dd');}" name="sick_date" id="sick_date"></input>
			</td>
			<td class="item-title">
				<label class="Validform_label"> 住院天数: </label>
			</td>
			<td class="item-value">
				<input class="easyui-numberbox" style="width: 155px;height: 25px;" type="text" name="hosp_days" id="hosp_days" value="${entity.hosp_days }" data-options="min:0,max:999999">
			</td>
			<td class="item-title">
				<label class="Validform_label"> 肺栓塞: </label>
			</td>
			<td class="item-value">
				<select id="is_lung" name="is_lung" class="easyui-combobox" style="width: 155px;height: 25px;" editable="false">
					<option value="1">是</option>
					<option value="0" selected="selected">否</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="item-title">
				<label class="Validform_label"> 肺动脉CTA: </label>
			</td>
			<td class="item-value">
				<select id="is_cta" name="is_cta" class="easyui-combobox" style="width: 155px;height: 25px;" editable="false">
					<option value="1">是</option>
					<option value="0" selected="selected">否</option>
				</select>
			</td>
			<td class="item-title">
				<label class="Validform_label"> 总费用: </label>
			</td>
			<td class="item-value">
				<input class="easyui-numberbox" style="width: 155px;height: 25px;" type="text" name="charge_money" id="charge_money" value="${entity.charge_money }" data-options="min:0,precision:2,max:99999999">
			</td>
		</tr>
		</tbody>
		</table>
	</form>
</div>
<div region="center" style="padding:1px;" id="case_center" title="附件列表">
	<table id="attachmentList" width="100%" height="100%">
		
	</table>
</div>
</div>

</body>
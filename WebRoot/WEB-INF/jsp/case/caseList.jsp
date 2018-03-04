<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/case/caseList.js"></script>
<script type="text/javascript">
$(function() {
	var nowDate = new Date();
	var end_time = nowDate.Format('yyyy-MM-dd');

	var firstDate = nowDate.setDate(1); //第一天
	firstDate = new Date(firstDate);
	var start_time = firstDate.Format('yyyy-MM-dd');
	
	$('#start_time').datebox('setValue',start_time);
	$('#end_time').datebox('setValue',end_time);
	
});
</script>

</head>

<body>
<div class="easyui-layout" fit="true">
	<div region="north" class="search-bar-container">
		<form id="searchCaseForm" method="post" style="margin: 0px">
			<table class="search-bar" cellspacing="1" cellpadding="0">
			<tbody>
			<tr>
				<td class="search-title">
					<label> 入院日期: </label>
				</td>
				<td class="search-value">
					<input class="easyui-datebox" style="width: 90px;height: 25px;" editable="false" 
						data-options="formatter: function(date){ return date.Format('yyyy-MM-dd');}" id="start_time" name="start_time"></input>
            		至
                    <input class="easyui-datebox" style="width: 90px;height: 25px;" editable="false" 
						data-options="formatter: function(date){ return date.Format('yyyy-MM-dd');}" id="end_time" name="end_time"></input>
				</td>
				
				<td class="search-title">
					<label> 病人姓名: </label>
				</td>
				<td class="search-value">
					<input style="width: 200px;height: 25px;" type="text" name="name" id="name">
				</td>
				<td class="search-title">
					<label> 血栓部位: </label>
				</td>
				<td class="search-value">
					<input style="width: 200px;height: 25px;" type="text" name="thrombus_part" id="thrombus_part">
				</td>
			</tr>
			</tbody>
			</table>
		</form>
	</div>
	
	<div region="center" style="padding:1px;" id="case_center">
		<table id="caseList">
			
		</table>
	</div>
</div>

</body>
</html>

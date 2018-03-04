<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript" src="js/dictType/dictTypeList.js"></script>
<script type="text/javascript" src="js/dictData/dictDataList.js"></script>
<body>

<div class="easyui-layout" fit="true">
	<div region="center" style="padding:1px;" id="dictType_center" title="数据字典分类">
		<table id="dictTypeList">
			
		</table>
	</div>
	
	<div region="east" style="padding:1px;width: 450px;" id="dictType_center" title="数据字典">
		<table id="dictDataList">
			
		</table>
	</div>
</div>

</body>
</html>

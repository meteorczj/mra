<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/module/moduleList.js"></script>
<body>

<div class="easyui-layout" fit="true">
	<div region="west" style="width: 160px;">
		<div class="easyui-panel" title="菜单树" style="padding: 10px;" fit="true" border="false" id="module-panel">
			<ul id="moduleTreeList"></ul>
	  	</div>
	</div>
	
	<div region="center" style="padding:1px;" id="module_center" title="菜单列表">
		<table id="moduleList" width="100%" height="100%">
			
		</table>
	</div>
</div>

</body>
</html>

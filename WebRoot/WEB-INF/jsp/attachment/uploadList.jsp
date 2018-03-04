<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/upload/upload.css"></link>

<script type="text/javascript" src="plug-in/swfupload/swfupload.js"></script>
<script type="text/javascript" src="plug-in/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="plug-in/swfupload/handlers.js"></script>
<script type="text/javascript" src="js/upload/upload.js"></script>

<script type="text/javascript">
	var case_id = '${case_id }';
</script>

</head>
<body>

<div class="easyui-layout" fit="true">
	<input type="hidden" id="ctx" value="${ctx }" />
	<div region="center" style="padding:1px;" id="upload_center">
		<div id="swfupload">
	    	<span id="spanButtonPlaceholder"></span>
			<p id="queueStatus"></p>
			<ol id="logList"></ol>
	    </div>
	</div>
</div>

</body>
</html>

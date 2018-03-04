<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<c:set var="ctx" value="${pageContext.request['contextPath']}" />

<%
String cpath = request.getContextPath();
String cbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cpath+"/";
%>

<base href="<%=cbasePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-store, must-revalidate">
<meta http-equiv="expires" content="0">

<!-- jquery js -->
<script type="text/javascript" src="plug-in/jquery/jquery-1.8.0.min.js"></script>

<!-- easyui js -->
<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
<script type="text/javascript" src="plug-in/easyui/locale/easyui-lang-zh_CN.js"></script>

<!-- plug js -->
<script type="text/javascript" src="plug-in/lhgDialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="plug-in/tools/dataformat.js"></script>
<!-- 
<script type="text/javascript" src="plug-in/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="plug-in/ajaxupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/form/jquery.form.js"></script>
<script type="text/javascript" src="plug-in/tools/syUtil.js"></script>
<script type="text/javascript" src="plug-in/tools/curdtools.js"></script>
<script type="text/javascript" src="plug-in/tools/easyuiextend.js"></script> -->

<!-- 开发用js -->
<script type="text/javascript" src="js/common.js"></script>

<!-- easyui css -->
<link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/icon.css"></link>
<!-- <link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/style.css" /> -->
<link rel="stylesheet" type="text/css" href="plug-in/accordion/css/accordion.css"></link>
<!-- <link rel="stylesheet" type="text/css" href="css/themes/default/common.css"></link> -->
<link rel="stylesheet" type="text/css" href="css/common.css"></link><!-- 新增页面样式均引用此处 -->



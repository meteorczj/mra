<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/accordion/css/icons.css" type="text/css"></link>
<link rel="stylesheet" href="plug-in/accordion/css/accordion.css" type="text/css"></link>

<script type="text/javascript" src="plug-in/accordion/js/menu.js"></script>


<div id="nav" class="easyui-accordion" fit="true" border="false">
    <c:forEach items="${parentFun}" var="cur">
    	<div title="${cur.map.functionname }" style="padding:10px;" iconCls="${cur.map.iconclas }">  
	        <ul>
	        	<c:forEach items="${childFun}" var="cur1">
	        		<c:if test="${cur1.map.parentfunctionid == cur.functionid}">
	        		<li>
	        		<div onclick="addTab('${cur1.map.functionname }','${cur1.map.functionurl }','${cur1.map.iconclas }')" title="${cur1.map.functionname }" url="${cur1.map.functionurl }" iconCls="${cur1.map.iconclas }">
	        			<a class="${cur1.map.functionname }" href="#">
	        				<span class="icon ${cur1.map.iconclas}">&nbsp;</span>
	        				<span class="nav">${cur1.map.functionname }</span>
	        			</a>
	        		</div>
	        		</li>
	        		</c:if>
	        	</c:forEach>
	        </ul>
	    </div>
    </c:forEach>
</div>

  

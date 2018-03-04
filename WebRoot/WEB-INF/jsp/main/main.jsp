<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>${title }</title>
<script type="text/javascript" src="plug-in/menutree/menu.js"></script>
<script type="text/javascript" src="plug-in/tools/curdtools.js"></script>

<link rel="shortcut icon" href="images/favicon.ico">
<style type="text/css">
a {
	color: Black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
</style>
<SCRIPT type="text/javascript">
	$(function() {
		$('#layout_east_calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});

	});
</SCRIPT>
<script>

function init(){
	tabClose();
	tabCloseEven();
}

$(function() {
	init();
	
	initSupcan();
	
	$('#moduleTree').tree({
			checkbox : false,
			url : 'moduleController.do?getModuleTree',
			onLoadSuccess : function(node, data) {
				//获得所有父节点
				var nodeArray = $('#moduleTree').tree('getRoots');
				if(nodeArray.length > 0) {
					var node = nodeArray[0];
					//展开第一个父节点
					$('#moduleTree').tree('expand', node.target);
				} else {
					expandAll();
				}
			},
			onClick: function(node){
				$(this).tree('toggle', node.target);
				var leaf = $(this).tree('isLeaf', node.target);
				if(leaf) {
					//自定义属性
					var baseUrl = node.attributes.url;
					var url = node.attributes.url;
					if(url != null) {
						addTab(node.text, url, node.id);
					} else {
						// 页面
					}
				}
			}
		});
	
	function expandAll() {
		var node = $('#moduleTree').tree('getSelected');
		if (node) {
			$('#moduleTree').tree('expandAll', node.target);
		} else {
			$('#moduleTree').tree('expandAll');
		}
	};
	
});

//初始化帮助文档
function initSupcan() {
	var agent = navigator.userAgent.toLowerCase();
	if (agent.indexOf("msie") > 0 || agent.indexOf("trident") > 0) {
		return;
	}
	if (navigator.mimeTypes["application/supcan-plugin"]) {
		return;
	}
	if (agent.indexOf("chrome") > 0) {
		//window.open("install_chrome.htm");
		$.dialog({
			id : "view",
			title : "系统帮助-添加WEB套件",
			max : false,
			min : false,
			lock : true,
			esc : false,
			width : 1000,
			height : 550,
			content : "url:plug-in/supcan/res/install_chrome.htm"
		});
	}
}
</script>
</head>
 <body class="easyui-layout" style="overflow-y: hidden" scroll="no">
  <!-- 顶部-->
  <div region="north" border="false" title="${title }" style="BACKGROUND:#E6E6FA;height: 60px; padding: 1px; overflow: hidden;">
   <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
     <td align="left" width="20px;">
      &nbsp;
     </td>
     <td align="left" nowrap="nowrap">
         <span style="color: #CC33FF">当前用户:</span><span style="color: #666633">(${userName }) <span style="color: #CC33FF">职务</span>:<span style="color: #666633">${roleName }</span>
     </td>
     <td width="500px;" align="right">
      <div>
       <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a>
       <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
      </div>
      <div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
       <div onclick="openwindow('用户信息','userController.do?userinfo')">
                个人信息
       </div>
       <div class="menu-sep"></div>
       <div onclick="add('修改密码','userController.do?changepassword')">
        修改密码
       </div>
      </div>
      <div id="layout_north_zxMenu" style="width: 100px; display: none;">
       <div onclick="exit('loginController.do?logout','确定退出该系统吗 ?',1);">
        退出系统
       </div>
      </div>
     </td>
     <td align="right" width="20px;">
      &nbsp;
     </td>
    </tr>
   </table>
  </div>
  
  <!-- 左侧-->
  <div region="west" split="true" title="导航菜单" style="width: 160px; padding: 1px;">
  	<ul id="moduleTree" class="easyui-tree"></ul>
  </div>
  
  <!-- 中间-->
  <div region="center" style="overflow: hidden;border:none">
    <div id="main_tabs" class="easyui-tabs" fit="true" border="true">
		<div title="首页" style="padding: 0px;">
			<div style="padding: 10; float: left;">
				welcome ...
			</div>

		</div>
	</div>
  </div>
  
  <!-- 
  <div id="mainPanle" region="center" style="overflow: hidden;">
   <div id="maintabs" fit="true" border="false">
    <div class="easyui-tab" title="首页" href="loginController.do?home" style="padding:2px; overflow: scroll;">
   				 
    </div>
   </div>
  </div> -->
  
	<!-- 选项卡右键 -->
	<div id="tabs_right" class="easyui-menu" style="width:150px;">
	  <div id="tabupdate">刷新</div>
	  <div class="menu-sep"></div>
	  <div id="close">关闭</div>
	  <div id="closeall">全部关闭</div>
	  <div id="closeother">除此之外全部关闭</div>
	  <div class="menu-sep"></div>
	  <div id="closeright">当前页右侧全部关闭</div>
	  <div id="closeleft">当前页左侧全部关闭</div>
	  <div class="menu-sep"></div>
	  <div id="exit">退出</div>
	</div>

 </body>
</html>
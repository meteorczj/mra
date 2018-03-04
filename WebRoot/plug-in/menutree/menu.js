
var onlyOpenTitle = "首页";//不允许关闭的标签的标题

/* 调用当前的方法：使用iframe中包含一个完整的jsp页面 */
function addTab2(subtitle, url, key) {
	$.messager.progress({
		text : '页面加载中....',
		interval : 100
	});
	
	if (!$('#main_tabs').tabs('exists', subtitle)) {
		$('#main_tabs').tabs('add', {
			id:key,
			title : subtitle,
			content : ["<iframe style='width:100%; height:100%;border:none;' frameborder='0' src='workbench.do?vn=",encodeURIComponent(url),"'></iframe>"].join(''),
			width: $('#main_tabs').width(),
			closable : true
		});
	} else {
		$('#main_tabs').tabs('select', subtitle);
		$.messager.progress('close');
	};
}

function addTab(subtitle, url, key) {
	if (!$("#main_tabs").tabs("exists", subtitle)) {
		$("#main_tabs").tabs("add", {
			id : key,
			title : subtitle,
			content : createFrame(url),
			width: $('#main_tabs').width(),
			closable : true
		});
	} else {
		$("#main_tabs").tabs('select', subtitle);
		$('#tabs_right-tabupdate').click();
	}
	tabClose();
}

function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#main_tabs').tabs('close', subtitle);
	});
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#tabs_right').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#tabs_right').data("currtab", subtitle);
		$('#main_tabs').tabs('select', subtitle);
		return false;
	});
}

//绑定右键菜单事件
function tabCloseEven() {
	$('#tabs_right').menu({
		onClick : function(item) {
			closeTab(item.id);
		}
	});
	return false;
}

function closeTab(action) {
	var alltabs = $('#main_tabs').tabs('tabs');
	var currentTab = $('#main_tabs').tabs('getSelected');
	var allTabtitle = [];
	$.each(alltabs, function(i, n) {
		allTabtitle.push($(n).panel('options').title);
	});

	switch (action) {
	case "tabupdate":
		if(currentTab.find("iframe").length > 0){
			var iframe = currentTab.find("iframe")[0];
			var src = iframe.src;
			$('#main_tabs').tabs("update", {
				tab : currentTab,
				options : {
					content : createFrame(src)
				}
			});
		}
		break;
	case "close":
		var tabIndex = $('#main_tabs').tabs('getTabIndex', currentTab);
		if (tabIndex == 0) {
			alert('亲，当前是欢迎主页不能关闭。 ^@^!!');
			return false;
		}
		var currtab_title = currentTab.panel("options").title;
		$('#main_tabs').tabs('close', currtab_title);
		break;
	case "closeall":
		$.each(allTabtitle, function(i, n) {
			if (n != onlyOpenTitle) {
				$('#main_tabs').tabs('close', n);
			}
		});
		break;
	case "closeother":
		var currtab_title = currentTab.panel('options').title;
		$.each(allTabtitle, function(i, n) {
			if (n != currtab_title && n != onlyOpenTitle) {
				$('#main_tabs').tabs('close', n);
			}
		});
		break;
	case "closeright":
		var tabIndex = $('#main_tabs').tabs('getTabIndex', currentTab);

		if (tabIndex == alltabs.length - 1) {
			alert('亲，后边没有啦 ^@^!!');
			return false;
		}
		$.each(allTabtitle, function(i, n) {
			if (i > tabIndex) {
				if (n != onlyOpenTitle) {
					$('#main_tabs').tabs('close', n);
				}
			}
		});

		break;
	case "closeleft":
		var tabIndex = $('#main_tabs').tabs('getTabIndex', currentTab);
		if (tabIndex == 1) {
			alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
			return false;
		}
		$.each(allTabtitle, function(i, n) {
			if (i < tabIndex) {
				if (n != onlyOpenTitle) {
					$('#main_tabs').tabs('close', n);
				}
			}
		});

		break;
	case "exit":
		$('#closeMenu').menu('hide');
		break;
	}
}

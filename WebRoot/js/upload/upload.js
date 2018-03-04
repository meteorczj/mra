var queueErrorArray;
$(function(){
	var ctx = $("#ctx").val();
	var swfUpload = new SWFUpload({
		upload_url: ctx + '/attachmentController/upload.do?case_id=' + case_id,
		flash_url: ctx + '/plug-in/swfupload/swfupload.swf',
		
		file_post_name: 'caseFile',
		use_query_string: true,
		post_params: {
			param1: 'Hello',
			param2: encodeURI("你好","UTF-8")
		},
		
		file_types: "*.ppt;*.doc;*.docx;*.xls;*.xlsx;*.png;*.jpg;*.jepg;*.bmp",
		file_types_description: '上报数据文件',
		file_size_limit: '1024000',
		file_upload_limit: 3,
		
		// handlers
		file_dialog_start_handler: fileDialogStart,
		file_queued_handler: fileQueued,
		file_queue_error_handler: fileQueueError,
		file_dialog_complete_handler: fileDialogComplete,
		upload_start_handler: uploadStart,
		upload_progress_handler: uploadProgress,
		upload_success_handler: uploadSuccess,
		upload_error_handler : uploadError,
		upload_complete_handler: uploadComplete,
		
		button_placeholder_id: 'spanButtonPlaceholder',
		button_text: '<a class="uploadBtn">请选择文件 </a>',
		button_text_style : '.uploadBtn { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
		button_image_url: ctx + '/plug-in/swfupload/image/XPButtonNoText_160x22.png',
		button_width: 160,
		button_height: 22,
		button_text_top_padding: 0,
        button_text_left_padding: 45,
		button_cursor: SWFUpload.CURSOR.HAND,
		button_action: SWFUpload.BUTTON_ACTION.SELECT_FILES,
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
		
		debug: false,
		
		custom_settings: {}
	});
});

//========================================  回调函数Handlers  ===================================

/**
* 打开文件选择对话框时响应
*/
function fileDialogStart() {
	if (queueErrorArray) {
		queueErrorArray = null;
	}
}

/**
* 文件被加入上传队列时的回调函数,增加文件信息到列表并自动开始上传.<br />
* <p></p>
* SWFUpload.startUpload(file_id)方法导致指定文件开始上传,
* 如果参数为空,则默认上传队列第一个文件;<br />
* SWFUpload.cancelUpload(file_id,trigger_error_event)取消指定文件上传并从队列删除,
* 如果file_id为空,则删除队列第一个文件,trigger_error_event表示是否触发uploadError事件.
* @param file 加入队列的文件
*/
function fileQueued(file) {
	var swfUpload = this;
	var listItem = '<li id="' + file.id + '">';
	listItem += '文件:<em>' + file.name + '</em>(' + Math.round(file.size/1024) + ' KB)';
	listItem += '<span class="progressValue"></span>'
			  + '<div class="progressBar"><div class="progress"></div></div>'
			  + '<p class="status" >Pending</p>'
			  + '<span class="cancel" >&nbsp;</span>'
			  + '</li>';
	$("#logList").append(listItem);
	$("li#" + file.id + " .cancel").click(function(e) {
		swfUpload.cancelUpload(file.id);
		$("li#" + file.id).slideUp('fast');
	})
//	swfUpload.startUpload();
}

/**
* 文件加入上传队列失败时触发,触发原因包括:<br />
* 文件大小超出限制<br />
* 文件类型不符合<br />
* 上传队列数量限制超出等.
* @param file 当前文件
* @param errorCode 错误代码(参考SWFUpload.QUEUE_ERROR常量)
* @param message 错误信息
*/
function fileQueueError(file,errorCode,message) {
	if (!queueErrorArray) {
		queueErrorArray = [];
	}
	var errorFile = {
		file: file,
		code: errorCode,
		error: ''
	};
	switch (errorCode) {
	case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
		errorFile.error = '文件大小超出限制.';
		break;
	case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
		errorFile.error = '文件类型受限.';
		break;
	case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
		errorFile.error = '文件为空文件.';
		break;
	case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
		errorFile.error = '超出文件数量限制.';
		break;
	default:
		alert('加载入队列出错.');
		break;
	}
	queueErrorArray.push(errorFile);
}

/**
* 选择文件对话框关闭时触发,报告所选文件个数、加入上传队列文件数及上传队列文件总数
* @param numSelected 选择的文件数目
* @param numQueued 加入队列的文件数目
* @param numTotalInQueued 上传文件队列中文件总数
*/
function fileDialogComplete(numSelected,numQueued,numTotalInQueued) {
	var swfupload = this;
	if (queueErrorArray && queueErrorArray.length) {
		var table = $('<table><tr><td>文件</td><td>大小</td></tr></table>');
		if(queueErrorArray[0].code == SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			table = $('<table><tr><td>超出文件数量限制!</td></tr></table>');
		} else {
			for(var i in queueErrorArray) {
				var tr = $('<tr></tr>');
				var info = '<td>' + queueErrorArray[i].file.name + '<span style="color:red">(' + queueErrorArray[i].error + ')</span></td>'
								+ '<td>' + queueErrorArray[i].file.size + 'bytes</td>';
				table.append(tr.append(info));
			}
		}
		
		//调用lhgdialog进行提示
		$.dialog({
			title:"警告!",
			content:table,
			zIndex:2000,
			max    : false, 
	   		min    : false,
	   		lock   : true
		});
		
	} else {
		this.startUpload();
	}
}

/**
* 文件开始上传时触发
* @param file 开始上传目标文件
*/
function uploadStart(file) {
	if (file) {
		$("#logList li#" + file.id).find('p.status').text('上传中...');
		$("#logList li#" + file.id).find('p.progressValue').text('0%');
	}
}

/**
* 文件上传过程中定时触发,更新进度显示
* @param file 上传的文件
* @param bytesCompleted 已上传大小
* @param bytesTotal 文件总大小
*/
function uploadProgress(file,bytesCompleted,bytesTotal) {
	var percentage = Math.round((bytesCompleted / bytesTotal) * 100);
	$("#logList li#" + file.id).find('div.progress').css('width',percentage + '%');
	$("#logList li#" + file.id).find('span.progressValue').text(percentage + '%');
}

/**
* 文件上传完毕并且服务器返回200状态码时触发
* @param file 上传的文件
* @param serverData 
* @param response
*/
function uploadSuccess(file,serverData,response) {
	var item = $("#logList li#" + file.id);
	item.find('div.progress').css('width','100%');
	item.find('span.progressValue').css('color','blue').text('100%');
	item.addClass('success').find('p.status').html('上传完成!');
}

/**
 * 文件上传失败，并且服务器返回错误时触发
 * @param file
 * @param errorCode
 * @param message
 */
function uploadError(file, errorCode, message) {
	var item = $("#logList li#" + file.id);
	item.find('span.progressValue').text('0%');
	item.find('p.status').text('上传失败!');
}

/**
* 在一个上传周期结束后触发(uploadError及uploadSuccess均触发)
* 在此可以开始下一个文件上传(通过上传组件的uploadStart()方法)
* @param file 上传完成的文件对象
*/
function uploadComplete(file) {
	this.uploadStart();
}
package com.czj.platform.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.czj.platform.entity.Attachment;
import com.czj.platform.entity.json.AjaxJson;
import com.czj.platform.service.AttachmentService;
import com.czj.platform.util.DateUtil;
import com.czj.platform.util.DateUtils;

/**
 * 文件上传控制层
 * 
 * @author Chu,zhujun
 * @version 2016年1月20日 下午3:52:16
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "attachmentController")
public class AttachmentController extends BaseController {

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 跳转到文件上传页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "uploadList")
	public String uploadList(HttpServletRequest request) {
		String case_id = request.getParameter("case_id");

		request.setAttribute("case_id", case_id);
		return "attachment/uploadList";
	}

	/**
	 * 附件列表查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "datagrid")
	@ResponseBody
	public ModelMap datagrid(HttpServletRequest request, Attachment entity) {
		ModelMap map = new ModelMap();
		List<Attachment> attachList = new ArrayList<Attachment>();

		try {
			String start_time = request.getParameter("start_time");
			String end_time = request.getParameter("end_time");
			if (null != start_time && !("").equals(start_time) && null != end_time && !("").equals(end_time)) {
				Date start_date = DateUtil.convertStringToDate(start_time);
				Date end_date = DateUtil.convertStringToDate(end_time);

				Calendar cd = Calendar.getInstance();
				cd.setTime(end_date);
				cd.add(Calendar.DATE, 1);

				entity.getMap().put("start_date", start_date);
				entity.getMap().put("end_date", cd.getTime());
			} else {
				Calendar cd = Calendar.getInstance();
				cd.set(Calendar.DAY_OF_MONTH, 1);
				Date s1 = cd.getTime();
				String s2 = DateUtil.convertDateToString(s1);
				Date start_date = DateUtil.convertStringToDate(s2);
				Date end_date = new Date();

				entity.getMap().put("start_date", start_date);
				entity.getMap().put("end_date", end_date);
			}
		} catch (Exception e) {
			error(e.getMessage());
		}

		attachList = this.attachmentService.selectAttachmentList(entity);
		int total = this.attachmentService.selectAttachmentCount(entity);

		entity.getMap().put("rows", attachList);
		entity.getMap().put("total", total);
		map.addAttribute("pageInfo", entity);
		return map;
	}

	/**
	 * 附件单个删除
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelMap delete(HttpServletRequest request, Attachment entity) {
		ModelMap map = new ModelMap();
		AjaxJson result = new AjaxJson();

		try {
			this.attachmentService.deleteAttachmentAndFile(entity);
		} catch (Exception e) {
			e.printStackTrace();
			error(e.getMessage());
			result.setSuccess(false);
			result.setMsg("删除失败!");
		}

		map.put("result", result);
		return map;
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @param caseFiles
	 */
	@RequestMapping(value = "upload")
	public void upload(HttpServletRequest request, HttpServletResponse response, @RequestParam() MultipartFile caseFile) {
		try {
			String case_id = request.getParameter("case_id");
			String filename = caseFile.getOriginalFilename();
			String type = filename.substring(filename.lastIndexOf(".") + 1);

			if (logger.isDebugEnabled())
				debug("======================case_id:" + case_id);

			// 判断文件夹是否存在
			String today = DateUtils.formatDate("yyyy-MM");
			String path = super.getMessage("uploadFile.path") + "/" + today;
			String realPath = path + "/" + filename;
			File filedir = new File(path);
			if (!filedir.isDirectory() && !filedir.exists()) {
				filedir.mkdirs();
			}
			File targetFile = new File(realPath);
			
			// 创建附件信息
			Attachment entity = new Attachment();
			entity.setCase_id(case_id);
			entity.setFile_name(filename);
			entity.setFile_type(type);
			entity.setFile_path(realPath);

			boolean result = this.attachmentService.uploadFileAndSaveAttachment(entity, caseFile, targetFile);
			if (result) {
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			error(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 文件下载
	 * 
	 * @param request
	 * @param response
	 * @param entity
	 */
	@RequestMapping(value = "download")
	public void download(HttpServletRequest request, HttpServletResponse response, Attachment entity) {
		String fileName = entity.getFile_name();
		String filePath = entity.getFile_path();
		if (logger.isDebugEnabled())
			debug("========================fileName:" + fileName);

		File file = new File(filePath);
		try {
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.addHeader("Content-Length", "" + file.length());
			response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(), "ISO-8859-1"));
			// 此处名称转换，为了防止中文名称乱码

			OutputStream os = response.getOutputStream();
			InputStream is = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int length = 0;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

			os.flush();
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

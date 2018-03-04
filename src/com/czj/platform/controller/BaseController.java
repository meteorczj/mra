package com.czj.platform.controller;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.czj.platform.entity.BaseEntity;
import com.czj.platform.entity.json.AjaxJson;
import com.czj.platform.util.DateFormatter;
import com.czj.platform.util.StringUtils;

public class BaseController {
	
	public static final String AJAX_MESSAGE = "success";
	public static final String SUCCESS = "true";
	public static final String FAIL = "fail";
	public static final String RESULT = "result";
	
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	protected Logger logger = Logger.getLogger(getClass());
	
	private MessageSource messageSource;

	@Resource(name = "messageSource")
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key) {
		return this.messageSource.getMessage(key, null, null);
	}

	public void debug(String message) {
		logger.debug(message);
	}

	public void error(String message) {
		logger.error(message);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void renderText(HttpServletResponse response, String result) throws IOException {
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		out.print(result);
		out.flush();
		out.close();
	}
	
	/**
	 * @param request
	 * @return 获取请求项目地址
	 */
	public String getCtxPath(HttpServletRequest request) {
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort());
		ctx.append(request.getContextPath());

		return ctx.toString();
	}
	
	/**
	 * 
	 * @author Zou,HongQuan
	 * @desc 转换成JSON格式数据
	 * @return
	 */
	protected <T extends BaseEntity> Object SupcanConverter(HttpServletRequest request, List<T> data, String... exclude) {
		HashSet<String> excludes = new HashSet<>();
		for (String item : exclude) {
			excludes.add(item);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		boolean flag = "Supcan TreeList".equalsIgnoreCase(request.getHeader("Component"));
		if (null != request.getAttribute("total_count")) {
			result.put("totalRows", request.getAttribute("total_count"));
		}
		result.put("record", list);
		if (data != null) {
			if (data.size() > 0) {
				try {
					PropertyDescriptor[] pds = Introspector.getBeanInfo(data.get(0).getClass(), BaseEntity.class).getPropertyDescriptors();

					for (T item : data) {

						Map<String, Object> d = new HashMap<String, Object>();
						for (String key : item.getMap().keySet()) {
							d.put(key, item.getMap().get(key));
						}
						for (PropertyDescriptor prop : pds) {
							if (excludes.contains(prop.getName())) {
								continue;
							}
							if (prop.getReadMethod() != null) {
								d.put(prop.getName(), prop.getReadMethod().invoke(item));
							}
							if (d.get(prop.getName()) instanceof Date) {
								DateFormatter formatter = prop.getReadMethod().getAnnotation(DateFormatter.class);
								if (formatter == null) {
									formatter = prop.getReadMethod().getDeclaringClass().getAnnotation(DateFormatter.class);
								}
								if (formatter == null) {
									d.put(prop.getName(), DATE_FORMAT.format((Date) d.get(prop.getName())));
								} else {
									d.put(prop.getName(), (new SimpleDateFormat(formatter.value())).format((Date) d.get(prop.getName())));
								}
							}
						}
						list.add(d);
					}
				} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return flag ? result : list;
	}

	/**
	 * 获取拼音码
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "getPinyinCode")
	@ResponseBody
	public ModelMap getPinyinCode(HttpServletRequest request, ModelMap map) {
		String moduleName = request.getParameter("moduleName");
		debug("-----------------BaseController getPinyinCode moduleName: " + moduleName);
		AjaxJson result = new AjaxJson();
		try {
			String pinyinCode = StringUtils.convertToPinyinCode(moduleName);
			debug("-----------------BaseController getPinyinCode pinyinCode: " + pinyinCode);

			map.addAttribute("pinyinCode", pinyinCode.toUpperCase());
		} catch (Exception e) {
			error(e.getMessage());
			result.setSuccess(false);
			result.setMsg("操作失败");
		}
		map.addAttribute("result", result);
		return map;
	}

	/**
	 * 上传图标
	 * 
	 * @param request
	 * @param type
	 *            类型：module,app,action
	 * @param key
	 *            实体的key值
	 */
	public void uploadIcon(HttpServletRequest request, String type, String id) {
		try {
			if (type != null && id != null) {
				// 图标上传
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				MultipartFile imageFile = multipartRequest.getFile("imageFile");
				if (imageFile != null && imageFile.getOriginalFilename() != null
						&& !imageFile.getOriginalFilename().equals("")) {
					debug("---------------getOriginalFilename:" + imageFile.getOriginalFilename());
					debug("---------------getOriginalFilename:" + imageFile.getContentType());
					String[] names = imageFile.getOriginalFilename().split("\\.");

					ServletContext context = request.getSession().getServletContext();
					String realPath = context.getRealPath("/WEB-INF/resources/" + type + "-icons") + "/" + id + "."
							+ names[1];
					debug("---------------realPath:" + realPath);
					File file = new File(realPath);

					// 调用spring方法写入指定路径的文件
					FileCopyUtils.copy(imageFile.getBytes(), file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

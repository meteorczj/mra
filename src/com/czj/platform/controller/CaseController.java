package com.czj.platform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czj.platform.entity.Case;
import com.czj.platform.entity.Constants;
import com.czj.platform.entity.User;
import com.czj.platform.entity.json.AjaxJson;
import com.czj.platform.service.CaseService;

/**
 * 病历控制层
 * 
 * @author Chu,zhujun
 * @version 2016年1月19日 下午12:32:38
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "caseController")
public class CaseController extends BaseController {

	@Autowired
	private CaseService caseService;
	
	/**
	 * 跳转到病历列表页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "caseList")
	public String caseList(HttpServletRequest request) {
		return "case/caseList";
	}

	/**
	 * 病历列表查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public ModelMap datagrid(HttpServletRequest request, Case entity) {
		ModelMap map = new ModelMap();
		List<Case> ddList = new ArrayList<Case>();

		ddList = this.caseService.selectCaseList(entity);
		int total = this.caseService.selectCaseCount(entity);

		entity.getMap().put("rows", ddList);
		entity.getMap().put("total", total);
		map.addAttribute("pageInfo", entity);
		return map;

	}

	/**
	 * 病历分类编码新增、修改页面跳转
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public String addorupdate(HttpServletRequest request, Case entity) {
		
		// 修改
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = this.caseService.selectCase(entity);
			request.setAttribute("is_add", 0);
		} else {
			entity.setId(UUID.randomUUID().toString());
			request.setAttribute("is_add", 1);
		}

		request.setAttribute("entity", entity);
		return "case/caseAdd";
	}

	/**
	 * 病历保存
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public ModelMap save(HttpServletRequest request, Case entity) {
		AjaxJson result = new AjaxJson();
		ModelMap map = new ModelMap();

		try {
			if(logger.isDebugEnabled()) 
				debug("===================case_id:" + entity.getId());
			
			User user = (User) request.getSession().getAttribute(Constants.USER_INFO);
			
			// 更新
			String is_add = request.getParameter("is_add");
			if (StringUtils.isNotBlank(is_add) && is_add.equals("0")) {
				entity.setModifier(user.getUsername());
				entity.setModify_time(new Date());
				
				this.caseService.updateCase(entity);
			} else {
				entity.setCreator(user.getUsername());
				entity.setCreate_time(new Date());
				
				this.caseService.insertCase(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.error(e.getMessage());
			result.setSuccess(false);
			result.setMsg("保存失败!");
		}

		map.put("result", result);
		return map;
	}
	
	/**
	 * 删除病历及其附件信息
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ModelMap delete(HttpServletRequest request, Case entity) {
		ModelMap map = new ModelMap();
		AjaxJson result = new AjaxJson();

		try {
			//this.attachmentService.deleteAttachmentAndFile(entity);
			this.caseService.deleteCaseAndAttachments(entity);
		} catch (Exception e) {
			e.printStackTrace();
			error(e.getMessage());
			result.setSuccess(false);
			result.setMsg("删除失败!");
		}

		map.put("result", result);
		return map;
	}

}

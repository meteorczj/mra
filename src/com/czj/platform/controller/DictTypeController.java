package com.czj.platform.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czj.platform.entity.DictType;
import com.czj.platform.entity.json.AjaxJson;
import com.czj.platform.service.DictTypeService;

/**
 * 数据字典分类控制层
 * 
 * @author Chu,zhujun
 * @version 2016年1月18日 下午2:57:32
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "dictTypeController")
public class DictTypeController extends BaseController {

	@Autowired
	private DictTypeService dictTypeService;

	/**
	 * 跳转到数据字典分类列表页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dictTypeList")
	public String dictTypeList(HttpServletRequest request) {
		return "dictType/dictTypeList";
	}

	/**
	 * 数据字典分类列表查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public ModelMap datagrid(HttpServletRequest request, DictType entity) {
		ModelMap map = new ModelMap();
		List<DictType> dtList = new ArrayList<DictType>();

		dtList = this.dictTypeService.selectDictTypeList(entity);
		int total = this.dictTypeService.selectDictTypeCount(entity);

		entity.getMap().put("rows", dtList);
		entity.getMap().put("total", total);
		map.addAttribute("pageInfo", entity);
		return map;

	}

	/**
	 * 数据字典分类编码新增、修改页面跳转
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public String addorupdate(HttpServletRequest request, DictType entity) {

		// 修改
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = this.dictTypeService.selectDictType(entity);
		}

		request.setAttribute("entity", entity);
		return "dictType/dictTypeAdd";
	}

	/**
	 * 数据字典分类保存
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public ModelMap save(HttpServletRequest request, DictType entity) {
		super.debug("====================" + entity.getCode());

		AjaxJson result = new AjaxJson();
		ModelMap map = new ModelMap();

		try {
			// 更新
			if (StringUtils.isNotBlank(entity.getId())) {
				this.dictTypeService.updateDictType(entity);
			} else {
				this.dictTypeService.insertDictType(entity);
			}
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			super.error(e.getMessage());
			result.setSuccess(false);
			result.setMsg("分类编码重复，保存失败!");
		} catch (Exception e) {
			e.printStackTrace();
			super.error(e.getMessage());
			result.setSuccess(false);
			result.setMsg("保存失败!");
		}

		map.put("result", result);
		return map;
	}

}

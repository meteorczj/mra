package com.czj.platform.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czj.platform.entity.DictData;
import com.czj.platform.entity.json.AjaxJson;
import com.czj.platform.service.DictDataService;

/**
 * 数据字典控制层
 * 
 * @author Chu,zhujun
 * @version 2016年1月19日 下午12:32:38
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "dictDataController")
public class DictDataController extends BaseController {

	@Autowired
	private DictDataService dictDataService;

	/**
	 * 数据字典列表查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public ModelMap datagrid(HttpServletRequest request, DictData entity) {
		ModelMap map = new ModelMap();
		List<DictData> ddList = new ArrayList<DictData>();

		ddList = this.dictDataService.selectDictDataList(entity);
		int total = this.dictDataService.selectDictDataCount(entity);

		entity.getMap().put("rows", ddList);
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
	public String addorupdate(HttpServletRequest request, DictData entity) {

		// 修改
		if (StringUtils.isNotBlank(entity.getId())) {
			entity = this.dictDataService.selectDictData(entity);
		}

		request.setAttribute("entity", entity);
		return "dictData/dictDataAdd";
	}

	/**
	 * 数据字典保存
	 * 
	 * @param request
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public ModelMap save(HttpServletRequest request, DictData entity) {
		super.debug("====================" + entity.getCode());

		AjaxJson result = new AjaxJson();
		ModelMap map = new ModelMap();

		try {
			// 判断字典编码是否重复
			

			// 更新
			if (StringUtils.isNotBlank(entity.getId())) {
				this.dictDataService.updateDictData(entity);
			} else {
				this.dictDataService.insertDictData(entity);
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
	
	@RequestMapping(value = "getDictDataList")
	@ResponseBody
	public List<DictData> getDictDataList(HttpServletRequest request, DictData entity) {
		List<DictData> ddList = new ArrayList<DictData>();

		ddList = this.dictDataService.selectDictDataList(entity);
		
		return ddList;
	}

}

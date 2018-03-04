package com.czj.platform.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.czj.platform.entity.Module;
import com.czj.platform.entity.json.AjaxJson;
import com.czj.platform.service.ModuleService;
import com.czj.platform.vo.ComboTree;

/**
 * 菜单管理
 * 
 * @author chuzj
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/moduleController")
public class ModuleController extends BaseController {

	@Autowired
	private ModuleService moduleService;

	/**
	 * 主页面左侧菜单树跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "left")
	public String left(HttpServletRequest request) {
		return "left";
	}

	/**
	 * ajax加载菜单树和菜单下拉框，加载全部菜单，无权限控制
	 * 
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "getModuleTree")
	@ResponseBody
	public List<ComboTree> getModuleTree(HttpServletRequest request, ComboTree comboTree) {
		debug("-----------------------------getModuleTree comboTree:" + comboTree);
		
		//目前在菜单添加的下拉框树中使用
		String isRecursive = request.getParameter("isRecursive");//是否迭代循环，直接查询所有节点树
		debug("-----------------------------getModuleTree isRecursive:" + isRecursive);
		boolean recursive = false;
		if(isRecursive != null) {
			recursive = Boolean.parseBoolean(isRecursive);
		}
		
		Module entity = new Module();
		
		if (comboTree.getId() != null) {
			entity.setParent(comboTree.getId());
		}
		if (comboTree.getId() == null) {
			entity.setDepth(0);
		}
		
		entity.setIsInvoked(1);

		List<Module> moduleList = moduleService.selectModuleList(entity);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		if (moduleList != null && moduleList.size() > 0) {
			comboTrees = this.comTree(moduleList, comboTree, recursive);
		}
		return comboTrees;
	}
	
	/**
	 * ajax加载主页面左侧菜单树,根据人员和角色权限加载菜单
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "getMenuTreeByRoot")
	@ResponseBody
	public List<ComboTree> getMenuTreeByRoot(HttpServletRequest request, ComboTree comboTree) {
		debug("-----------------------------getMenuTreeByRoot comboTree:" + comboTree.toString());
		
		String isRecursive = request.getParameter("isRecursive");//是否迭代循环，直接查询所有节点树
		debug("-----------------------------getMenuTreeByRoot isRecursive:" + isRecursive);
		boolean recursive = false;
		if(isRecursive != null) {
			recursive = Boolean.parseBoolean(isRecursive);
		}
		
		Module entity = new Module();
		//父菜单为空，则查询一级菜单，否则查询当前菜单下的子菜单
		if (comboTree.getId() != null) {
			entity.setParent(comboTree.getId());
		}
		if (comboTree.getId() == null) {
			entity.setDepth(0);
		}
		
		List<Module> moduleList = moduleService.selectModuleListByRoot(entity);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		if (moduleList != null && moduleList.size() > 0) {
			comboTrees = this.comTree(moduleList, comboTree, recursive);
		}
		return comboTrees;
	}

	/**
	 * 导航菜单跳转到菜单列表页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "moduleList")
	public String moduleList(HttpServletRequest request) {
		return "module/moduleList";
	}

	/**
	 * ajax加载菜单列表数据
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public ModelMap datagrid(HttpServletRequest request, Module entity) {
		ModelMap map = new ModelMap();
		
		debug("---------------------moduleController datagrid entity:" + entity);
		
		if (StringUtils.isBlank(entity.getParent())) {
			entity.setDepth(0);
		}
		
		//查询菜单列表
		List<Module> moduleList = moduleService.selectModuleList(entity);
		int total = moduleService.selectModuleCount(entity);
		
		//json数据返回
		entity.getMap().put("rows", moduleList);
		entity.getMap().put("total", total);
		map.addAttribute("pageInfo", entity);
		
		return map;
	}

	/**
	 * 菜单列表页面跳转到添加或者编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public String addorupdate(HttpServletRequest request, Module entity) {
		if(StringUtils.isNotBlank(entity.getId())) {
			entity = this.moduleService.selectModule(entity);
		} else {
			Long index = moduleService.selectMaxIndex(null);
			
			if(index != null) {
				index = index + 1;
			} else {
				index = 1L;
			}
			request.setAttribute("index", index);
		}
		
		request.setAttribute("entity", entity);
		return "module/moduleAdd";
	}

	/**
	 * 菜单信息保存
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public ModelMap save(HttpServletRequest request,Module entity) {
		debug("--------------ModuleController save module: " + entity);
		String type = request.getParameter("type");
		debug("--------------ModuleController save type: " + type);
		
		AjaxJson result = new AjaxJson();
		ModelMap map = new ModelMap();
		try {
			Module parentModule = new Module();
			//Long index = entity.getIndex();
			//String path = "";
			//设置depth,path
			if(entity.getParent() != null && !entity.getParent().equals("")) {
				parentModule.setId(entity.getParent());
				parentModule = moduleService.selectModule(parentModule);
				entity.setDepth(parentModule.getDepth() + 1);
				/*if(index >=10) {
					path = parentModule.getPath() + index;
				} else {
					path = parentModule.getPath() + "0" + index;
				}*/
			} else {
				//如果父级菜单id为空，则设置为一级菜单
				entity.setDepth(0);
				/*if(index >=10) {
					path = "" + index;
				} else {
					path = "0" + index;
				}*/
			}
			
			if (entity.getId() != null && !entity.getId().equals("")) {
				moduleService.updateModule(entity);
			} else {
				moduleService.insertModule(entity);
				debug("----------module id:" + entity.getId());
				
			}
			
			//上传图标
			if(type != null && entity.getId() != null) {
				//super.uploadIcon(request, type, entity.getId());
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setSuccess(false);
			result.setMsg("操作失败");
		}
		map.addAttribute("result", result);
		return map;
	}

	/**
	 * 删除一条或者多条记录
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "delete")
	@ResponseBody
	public ModelMap delete(HttpServletRequest request, ModelMap map) {
		String id = request.getParameter("id");
		debug("-------------------------------module delete: " + id);
		AjaxJson result = new AjaxJson();
		try {
			Module entity = new Module();
			// id包含“，”，则删除多条记录
			if(id != null && !id.equals("")) {
				if (id.contains(",")) {
					String[] ids = id.split(",");
					entity.getMap().put("pks", ids);
				} else {
					String[] ids = new String[]{id};
					entity.getMap().put("pks", ids);
				}
				
				moduleService.deleteModule(entity);
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setSuccess(false);
			result.setMsg("操作失败");
		}
		map.addAttribute("result", result);
		return map;
	}

	/**
	 * ajax方式文件上传
	 * @param image
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params = "uploadFile", method = RequestMethod.POST)
	public ModelMap uploadFile(@RequestParam("imageFile")
	MultipartFile image, HttpServletRequest request) throws IOException {
		String type = request.getParameter("type");
		debug("---------------type:" + type);
		debug("---------------getOriginalFilename:" + image.getOriginalFilename());
		
		//ServletContext context = SystemContext.getServletContext();
		ServletContext context = request.getSession().getServletContext();
		String realPath = context.getRealPath("/WEB-INF/resources/" + type + "-icons") + "/" + image.getOriginalFilename();
		File file = new File(realPath);
        
		//调用spring方法写入指定路径的文件
        FileCopyUtils.copy(image.getBytes(), file);

		AjaxJson result = new AjaxJson();
		ModelMap map = new ModelMap();
		map.addAttribute("result", result);
		return map;
	}

	public List<ComboTree> comTree(List<Module> all, ComboTree comboTree,boolean isRecursive) {
		List<ComboTree> trees = new ArrayList<ComboTree>();
		for (Module entity : all) {
			trees.add(tree(entity, isRecursive));
		}
		return trees;

	}

	public ComboTree tree(Module entity, boolean recursive) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getTitle());
		tree.getAttributes().put("url", entity.getUrl());

		if(entity.getIsGroup() == 0) {//如果非页子节点，则继续进行查询操作
			Module node = new Module();
			node.setParent(entity.getId());
			List<Module> moduleList = moduleService.selectModuleList(node);
	
			if (moduleList != null && moduleList.size() > 0) {
				tree.setState("closed");
				tree.setChecked(false);
				
				if (recursive) {// 递归查询子节点
					List<ComboTree> children = new ArrayList<ComboTree>();
					for (Module d : moduleList) {
						ComboTree t = tree(d, true);
						children.add(t);
					}
					tree.setChildren(children);
				}
			}
		}
		return tree;
	}

}

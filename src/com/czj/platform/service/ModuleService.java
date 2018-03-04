package com.czj.platform.service;

import java.util.List;

import com.czj.platform.entity.Module;

public interface ModuleService {
	
	public int selectModuleCount(Module entity);
	
	public List<Module> selectModuleList(Module entity);
	
	public Module selectModule(Module entity);
	
	public int insertModule(Module entity);
	
	public int updateModule(Module entity);
	
	public int deleteModule(Module entity);
	
	/**
	 * 获取当前菜单下所属菜单的最大index
	 * @param parentKey
	 * @return
	 */
	Long selectMaxIndex(Module entity);
	
	/**
	 * 根据用户权限和角色权限加载菜单
	 * @param entity
	 * @return
	 */
	List<Module> selectModuleListByRoot(Module entity);
	
}

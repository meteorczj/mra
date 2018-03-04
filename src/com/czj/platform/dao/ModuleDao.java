package com.czj.platform.dao;

import java.util.List;

import com.czj.platform.entity.Module;

public interface ModuleDao {
	
	int selectModuleCount(Module entity);
	
	List<Module> selectModuleList(Module entity);
	
	Module selectModule(Module entity);
	
	int insertModule(Module entity);
	
	int updateModule(Module entity);
	
	int deleteModule(Module entity);
	
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

package com.czj.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czj.platform.dao.ModuleDao;
import com.czj.platform.entity.Module;
import com.czj.platform.service.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService{
	
	@Autowired
	private ModuleDao moduleDao;
	
	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(ModuleDao roleDao) {
		moduleDao = roleDao;
	}
	
	@Override
	public Module selectModule(Module entity) {
		return this.moduleDao.selectModule(entity);
	}

	@Override
	public int insertModule(Module entity) {
		return  moduleDao.insertModule(entity);
	}

	@Override
	public int deleteModule(Module entity) {
		
		String[] keys = (String[]) entity.getMap().get("pks");
		Module module = new Module();
		
		if (keys != null && keys.length > 0) {
			for (int i = 0; i < keys.length; i++) {
				module.setParent(keys[i]);
				
				this.deleteSystemModule(module);
			}
		}
		
		//删除当前菜单
		int result= moduleDao.deleteModule(entity);
		
		return result;
	}
	
	/**
	 * 删除当前菜单下的所有菜单
	 * @param entity
	 */
	private void deleteSystemModule(Module entity) {
		List<Module> moduleList = moduleDao.selectModuleList(entity);
		
		if(moduleList.size()>0) {
			Module module = new Module();
			for(Module mod : moduleList) {
				if(mod.getIsGroup() == 0) {//如果非页子节点，则继续进行查询操作;递归删除所有菜单
					module.setParent(mod.getId());
					deleteSystemModule(module);
				} else {
					for (int j = 0; j < moduleList.size(); j++) {
						//this.deleteModulePageAction(mod.getKey());
					}
				}
				
			}
			
		} else {
			//this.deleteModulePageAction(entity.getParentKey());
		}
		
		//删除菜单下的子节点
		moduleDao.deleteModule(entity);
	}
	
	@Override
	public int selectModuleCount(Module entity) {
		return moduleDao.selectModuleCount(entity);
	}

	@Override
	public List<Module> selectModuleList(Module entity) {
		return moduleDao.selectModuleList(entity);
	}

	@Override
	public int updateModule(Module entity) {
		return moduleDao.updateModule(entity);
	}

	@Override
	public Long selectMaxIndex(Module entity) {
		return moduleDao.selectMaxIndex(entity);
	}

	@Override
	public List<Module> selectModuleListByRoot(Module entity) {
		return moduleDao.selectModuleListByRoot(entity);
	}

}

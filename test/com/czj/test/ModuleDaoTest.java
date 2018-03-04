package com.czj.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.czj.platform.dao.ModuleDao;
import com.czj.platform.entity.Module;
import com.czj.platform.util.DataSourceContextHolder;

/**
 * 
  *   
  * 项目名称：czj  
  * 类名称：ModuleDaoTest  
  * 类描述： 单元测试，在项目未启动的情况下，手动加载spring配置文件
  * 创建人：chuzj
  * 创建时间：Nov 4, 2014 10:12:18 AM  
  * 修改人： chuzj
  * 修改时间：Nov 4, 2014 10:12:18 AM  
  * 修改备注：  
  * @version   
*
 */
public class ModuleDaoTest {
	private ClassPathXmlApplicationContext context;
	private ModuleDao moduleDao1;
	private ModuleDao moduleDao2;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		//DataSourceContextHolder.setDbType("ds1");
		moduleDao1 = (ModuleDao) context.getBean("moduleDao");
		List<Module> moduleList1 = this.moduleDao1.selectModuleList(null);
		System.out.println("-----------------------size1:" + moduleList1.size());
		
		DataSourceContextHolder.setDbType("ds2");
		moduleDao2 = (ModuleDao) context.getBean("moduleDao");
		List<Module> moduleList2 = this.moduleDao2.selectModuleList(null);
		System.out.println("-----------------------size2:" + moduleList2.size());

	}

	@Test
	public void testGetModuleList() {
		
		//List<Module> moduleList1 = this.moduleDao1.selectModuleList(null);
		//System.out.println("-----------------------size1:" + moduleList1.size());
		
		//List<Module> moduleList2 = this.moduleDao2.selectModuleList(null);
		//System.out.println("-----------------------size2:" + moduleList2.size());
		
	}

}

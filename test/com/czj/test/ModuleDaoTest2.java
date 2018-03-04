package com.czj.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.czj.platform.dao.ModuleDao;
import com.czj.platform.entity.Module;
import com.czj.platform.util.DataSourceContextHolder;

/**
 * 
  *   
  * 项目名称：czj  
  * 类名称：ModuleDaoTest  
  * 类描述： 单元测试，在项目未启动的情况下，使用spring test加载spring配置文件
  * 创建人：chuzj
  * 创建时间：Nov 4, 2014 10:12:18 AM  
  * 修改人： chuzj
  * 修改时间：Nov 4, 2014 10:12:18 AM  
  * 修改备注：  
  * @version   
*
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext*.xml")//此处"/"必须有
//@Transactional
public class ModuleDaoTest2{
	
	@Resource(name = "moduleDao")
	private ModuleDao moduleDao;

	/*
	 @RunWith 注释标签是 Junit 提供的，用来说明此测试类的运行者，这里用了 SpringJUnit4ClassRunner，这个类是一个针对 Junit 运行环境的自定义扩展，用来标准化在 Spring 环境中 Junit4.5 的测试用例，例如支持的注释标签的标准化
	 @ContextConfiguration 注释标签是 Spring test context 提供的，用来指定 Spring 配置信息的来源，支持指定 XML 文件位置或者 Spring 配置类名，这里我们指定 classpath 下的 /config/Spring-db1.xml 为配置文件的位置
	 @Transactional 注释标签是表明此测试类的事务启用，这样所有的测试方案都会自动的 rollback，即您不用自己清除自己所做的任何对数据库的变更了
	
	 小结
	 如果您希望在 Spring 环境中进行单元测试，那么可以做如下配置：
	 继续使用 Junit4 测试框架，包括其 @Test 注释标签和相关的类和方法的定义，这些都不用变
	 您需要通过 @RunWith(SpringJUnit4ClassRunner.class) 来启动 Spring 对测试类的支持
	 您需要通过 @ContextConfiguration 注释标签来指定 Spring 配置文件或者配置类的位置
	 您需要通过 @Transactional 来启用自动的事务管理
	 您可以使用 @Autowired 自动织入 Spring 的 bean 用来测试
	
	 另外您不再需要：
	 手工加载 Spring 的配置文件
	 手工清理数据库的每次变更
	 手工获取 application context 然后获取 bean 实例
	 
	 此处junit版本是4.8,4.4版本不支持
	 */
	@Test
	public void testGetModuleList() {
		
		//增加此行，切换到另一个数据源
		//DataSourceContextHolder.setDbType("ds2");
		
		List<Module> moduleList = this.moduleDao.selectModuleList(null);
		
		System.out.println("-----------------------size:" + moduleList.size());
		
	}

}

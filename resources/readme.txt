1、搭建一个基于SSI、easyUI的项目，运行起来，测试左侧的列表树
	1)后台参考ModuleController
	2)前台参考main.jsp

2、测试webservice功能；
	1)配置文件applicationContext-mvc.xml中的WebService CXF配置；
	2)示例参考webservice包下HelloWorld
	3)客户端调用在test包下的WebServiceClientTest

3、测试文件上传、下载功能；
	1)引入swfupload支持库plug-in/swfupload
	2)前台页面参照uploadList.jsp;
	3)后台参考AttachmentController

4、测试导出excel功能；

5、测试打印功能；

6、测试spring定时器功能；

7、测试事务功能；

8、测试连接池功能；proxool;

10、测试登录；过滤action登录；
	1)LoginController.java：登录，退出功能
	2)SessionInterceptor.java：拦截器拦截
	3)在applicationContext-mvc.xml中配置SpringMVC拦截器
	
11、测试菜单和权限；

12、把数据放到内存，并且监控当有数据更新时，更新内存中数据；

13、测试javabean和xml之间的互相转换
	1）使用xstream 2）使用xmlpull_1_0_5.jar和xstream-1.4.7.jar两个jar包 3）测试类：XStreamTest.java
	
14、在单元测试中调用由Srping管理的Service层、Dao层，并能连接到数据源
	1）参考：http://blog.jobbole.com/40740/
	2）测试类：ModuleDaoTest.java（在项目未启动的情况下，手动加载spring配置文件）
	3）测试类：ModuleDaoTest2.java（在项目未启动的情况下，使用spring测试类注解加载spring配置文件）
	
15、spring + mybatis 配置多数据源
	1）参考http://blog.csdn.net/fhx007/article/details/12530735
	2）单元测试类：ModuleDaoTest.java
	
16、添加监听，实现自动创建数据库功能

17、测试log4j功能

18、设置数据库的级联删除功能
	1)以Mysql为例： `FK_t_dict_data` FOREIGN KEY (`group_id`) REFERENCES `t_dict_type` (`id`) ON DELETE CASCADE 
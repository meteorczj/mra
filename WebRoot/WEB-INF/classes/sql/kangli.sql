/*
SQLyog Community Edition- MySQL GUI v6.5 Beta1
MySQL - 5.5.28 : Database - kangli
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

create database if not exists `kangli`;

USE `kangli`;

/*Table structure for table `t_address` */

DROP TABLE IF EXISTS `t_address`;

CREATE TABLE `t_address` (
  `aid` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `postCode` varchar(6) NOT NULL,
  `ownerid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `ownerid` (`ownerid`),
  CONSTRAINT `t_address_ibfk_1` FOREIGN KEY (`ownerid`) REFERENCES `t_user` (`uid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_address` */

/*Table structure for table `t_function` */

DROP TABLE IF EXISTS `t_function`;

CREATE TABLE `t_function` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `iconid` varchar(32) DEFAULT NULL COMMENT '图标ID',
  `parentfunctionid` varchar(32) DEFAULT NULL COMMENT '父权限ID',
  `functionname` varchar(50) NOT NULL COMMENT '菜单名称',
  `functionlevel` smallint(6) DEFAULT NULL COMMENT '菜单等级',
  `functionurl` varchar(100) DEFAULT NULL COMMENT '菜单地址',
  `iconpath` text COMMENT '图标路径',
  `functionorder` varchar(10) DEFAULT NULL,
  `functioniframe` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_function` */

insert  into `t_function`(`id`,`iconid`,`parentfunctionid`,`functionname`,`functionlevel`,`functionurl`,`iconpath`,`functionorder`,`functioniframe`) values ('27','402881763a019ea9013a01b009b80019',NULL,'系统管理',0,'','','ofun5',NULL),('28','402881763a019ea9013a01af2d330015','27','用户管理',1,'userController.do?user','','tfun5',NULL),('297e86303e8bee63013e8bf0ef9e0001','402881763a019ea9013a01af2d330015','27','部门管理3',1,'departController.do?depart3',NULL,'tfun16',NULL),('30','402881763a019ea9013a01af2d330015','27','角色管理',1,'roleController.do?role','','tfun6',NULL),('31','402881763a019ea9013a01af2d330015','27','菜单管理',1,'functionController.do?function','','tfun7',NULL),('402881723a67558f013a6757a9c50002','402881763a019ea9013a01b043a4001a','402881723a67558f013a6757a9c50002','bootstrap',0,'',NULL,'ofun7',NULL),('402881723a67558f013a6757f6160004','55','402881723a67558f013a6757a9c50002','bootstrap',1,'commonController.do?listTurn&turn=bootstrap/test',NULL,'tfun9',1),('402881e53c4c64d8013c4c67b8470001','402881763a019ea9013a01af2d330015','27','类型分组',1,'systemController.do?typeGroupList',NULL,'tfun11',NULL),('402881e53c8589f7013c85afc393000b','55',NULL,'统计查询',0,'',NULL,'ofun3',NULL),('402881e53c8adce5013c8b000beb0003','402881763a019ea9013a01af5caf0016','402881e53c8589f7013c85afc393000b','用户分析',1,'logController.do?statisticTabs',NULL,'tfun17',1),('402881e53c98df12013c98fe11710009','402881763a019ea9013a01af2d330015',NULL,'新闻文件',0,'',NULL,'ofun9',NULL),('402881e53c98df12013c98fe8a65000b','402881763a019ea9013a01af2d330015','402881e53c98df12013c98fe11710009','图片文章',1,'commonController.do?listTurn&turn=system/document/newsList',NULL,'tfun28',1),('402881e53c98df12013c98fed49d000d','402881763a019ea9013a01af2d330015','402881e53c98df12013c98fe11710009','技术文档',1,'commonController.do?listTurn&turn=system/document/regulationsList',NULL,'tfun29',1),('402881e53c98df12013c98ff1bb9000f','402881763a019ea9013a01af2d330015','402881e53c98df12013c98fe11710009','文件下载',1,'commonController.do?listTurn&turn=system/document/filesList',NULL,'ofun10',1),('402881e93cb3f340013cb3f49af60001','402881763a019ea9013a01af2d330015',NULL,'开发维护',0,'',NULL,'ofun11',NULL),('402881e93e7e8722013e7e8970740001','402881763a019ea9013a01af2d330015','27','部门管理2',1,'departController.do?depart2',NULL,'tfun16',NULL),('49','402881763a019ea9013a01af2d330015','402881e93cb3f340013cb3f49af60001','图标管理',1,'iconController.do?icon','','tfun18',NULL),('65','402881763a019ea9013a01af2d330015','402881e93cb3f340013cb3f49af60001','系统配置',1,'configController.do?config','','tfun19',NULL),('71','402881763a019ea9013a01af2d330015','27','日志管理',1,'logController.do?log','','tfun21',NULL),('80','402881763a019ea9013a01af2d330015','27','部门管理',1,'departController.do?depart','','tfun22',NULL),('98','402881763a019ea9013a01af2d330015','27','类型词典',1,'systemController.do?typeGroupTabs','','tfun26',NULL);

/*Table structure for table `t_icon` */

DROP TABLE IF EXISTS `t_icon`;

CREATE TABLE `t_icon` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(100) NOT NULL,
  `type` smallint(6) DEFAULT NULL,
  `path` text COMMENT '路径',
  `content` longblob,
  `iconclas` varchar(200) DEFAULT NULL,
  `extend` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_icon` */

insert  into `t_icon`(`id`,`name`,`type`,`path`,`content`,`iconclas`,`extend`) values ('402881763a019ea9013a01adcf29000f','返回',1,'plug-in/accordion/images/back.png','\0��P@\0Ë��t8�J�It2S�ËB�������P�H��v���X�H�I|��H�u�@�������[��Í@\0����Ë�����Ë�S��t-�X��t&J|9�})Ӆ�|9�D$�|����1������D$�y���[�\0�SVW�É։��������t0�J�N|*9�}&��~\")�9�~��)���������؋R�)��\0\0\0_^[Å�tVSVWU�É։ϋR��t�R�O}1�9�~�׋k����u\0\0\0X9�u���/�H�)����w����؋����j���]_^[Ð��t@��t1SVW�Ɖ׋O�W�V�Jx�F)�~�u��VW���_^t����Z1��1��Z��)�_^[Í@\0SVW�É�1���~H���t#�x�u����	P������X����p��\0�(�������ǋ��t��H�9�|����������,����;_^[Ë�SVW�É։ω�������~���������������_^[Ë�3��\0\0\0�S�Ӊ�1Ʌ�t�K�)�Q����Y��[�Y���ð����Å�tPj\0�����������Í@\0���tR����Í@\0���t�\0\0\0\0\0PR����XÍ@\0SV�É֋��t�\0\0\0\0P�x�����Nu�^[Í@\0��������J��������QRP�I������q���������Ë�SVWU�����P����$���	���x����]�n���\0\0}(V�D$�L$��\0\0�8����؅�~�T$�ǋ��9\0\0\0�*�݋ǋ���\0\0V��L$���\n����؅�}3ۋǋ���\0\0��\0\0]_^[Ë�����PQR�����������Z�2�����ÐR��\0\0\0�C���ZÐR��\0\0\0����ZÐ1Ʌ�t!R:\nt:Jt:Jt:Jt����BBB��Z)�����Í@\01Ʌ�t-Rf;\nt f;Jtf;Jtf;Jt�����������Z)����T���Í@\01Ɋ\nB����ÐWPQ��1��f�u��X�X_�,���Í@\01Ʌ�t�J�����ÐSVW��\0����ً����\0\0\0~��\0\0\0���Y\0\0\0;��Å�u3��P�D$�Ϻ�\0\0������}3��;�}�È��~�V�̑�������\0\0\0_^[Ð��tÐ\0\0��U@\0Ë�t�@���Ë�SVWUQ��$��������؅�tB�$�\0�������3�I������~��ɋ׋$�\0������ɍw�������$���3���Z]_^[ÐSVWU���L$��$���}����؋D$�r������u��t1���������ɋ׋��-�����ɍ_�D$�����$�������YZ]_^[�SVRP��1L���tA�Ju�������P�ƋD����t\n�H�������Ku�ZX�','back','png'),('402881763a019ea9013a01aeefad0014','饼图',1,'plug-in/accordion/images/pie.png','\0��P@\0Ë��t8�J�It2S�ËB�������P�H��v���X�H�I|��H�u�@�������[��Í@\0����Ë�����Ë�S��t-�X��t&J|9�})Ӆ�|9�D$�|����1������D$�y���[�\0�SVW�É։��������t0�J�N|*9�}&��~\")�9�~��)���������؋R�)��\0\0\0_^[Å�tVSVWU�É։ϋR��t�R�O}1�9�~�׋k����u\0\0\0X9�u���/�H�)����w����؋����j���]_^[Ð��t@��t1SVW�Ɖ׋O�W�V�Jx�F)�~�u��VW���_^t����Z1��1��Z��)�_^[Í@\0SVW�É�1���~H���t#�x�u����	P������X����p��\0�(�������ǋ��t��H�9�|����������,����;_^[Ë�SVW�É։ω�������~���������������_^[Ë�3��\0\0\0�S�Ӊ�1Ʌ�t�K�)�Q����Y��[�Y���ð����Å�tPj\0�����������Í@\0���tR����Í@\0���t�\0\0\0\0\0PR����XÍ@\0SV�É֋��t�\0\0\0\0P�x�����Nu�^[Í@\0��������J��������QRP�I������q���������Ë�SVWU�����P����$���	���x����]�n���\0\0}(V�D$�L$��\0\0�8����؅�~�T$�ǋ��9\0\0\0�*�݋ǋ���\0\0V��L$���\n����؅�}3ۋǋ���\0\0��\0\0]_^[Ë�����PQR�����������Z�2�����ÐR��\0\0\0�C���ZÐR��\0\0\0����ZÐ1Ʌ�t!R:\nt:Jt:Jt:Jt����BBB��Z)�����Í@\01Ʌ�t-Rf;\nt f;Jtf;Jtf;Jt�����������Z)����T���Í@\01Ɋ\nB����ÐWPQ��1��f�u��X�X_�,���Í@\01Ʌ�t�J�����ÐSVW��\0����ً����\0\0\0~��\0\0\0���Y\0\0\0;��Å�u3��P�D$�Ϻ�\0\0������}3��;�}�È��~�V�̑�������\0\0\0_^[Ð��tÐ\0\0��U@\0Ë�t�@���Ë�SVWUQ��$��������؅�tB�$�\0�������3�I������~��ɋ׋$�\0������ɍw�������$���3���Z]_^[ÐSVWU���L$��$���}����؋D$�r������u��t1���������ɋ׋��-�����ɍ_�D$�����$�������YZ]_^[�SVRP��1L���tA�Ju�������P�ƋD����t\n�H�������Ku�ZX����Z^[X�$����SVW�Ɖ�9�tr��tQ��tT�F�W�)�w�R��t&��9�uAJt�N�_9�u4����Ju������Z��tf�f�f9�u!���W�)���F�)��Zf9�u	����f9�_^[Í@\0U��SVW��ڋ���Q�����}3��K;�}�؅�}3��+�;�}�ǋ��֋M�����_^[]�\0�@\0SVWUQ��ڋ�E\0�������~n��|i;�|e��~aK��+�+ǋ��}3���u��u3	$�9��L����$��~��ɋ$�E\0������~+�E\0�p��ɋ$�Z�w����','pie','png'),('402881763a019ea9013a01af2d330015','图片',1,'plug-in/accordion/images/pictures.png','\0��P@\0Ë��t8�J�It2S�ËB�������P�H��v���X�H�I|��H�u�@�������[��Í@\0����Ë�����Ë�S��t-�X��t&J|9�})Ӆ�|9�D$�|����1������D$�y���[�\0�SVW�É։��������t0�J�N|*9�}&��~\")�9�~��)���������؋R�)��\0\0\0_^[Å�tVSVWU�É։ϋR��t�R�O}1�9�~�׋k����u\0\0\0X9�u���/�H�)����w����؋����j���]_^[Ð��t@��t1SVW�Ɖ׋O�W�V�Jx�F)�~�u��VW���_^t����Z1��1��Z��)�_^[Í@\0SVW�É�1���~H���t#�x�u����	P������X����p��\0�(�������ǋ��t��H�9�|����������,����;_^[Ë�SVW�É։ω�������~���������������_^[Ë�3��\0\0\0�S�Ӊ�1Ʌ�t�K�)�Q����Y��[�Y���ð����Å�tPj\0�����������Í@\0���tR����Í@\0���t�\0\0\0\0\0PR����XÍ@\0SV�É֋��t�\0\0\0\0P�x�����Nu�^[Í@\0��������J��������QRP�I������q���������Ë�SVWU�����P����$���	���x����]�n���\0\0}(V�D$�L$��\0\0�8����؅�~�T$�ǋ��9\0\0\0�*�݋ǋ���\0\0V��L$���\n����؅�}3ۋǋ���\0\0��\0\0]_^[Ë�����PQR�����������Z�2�����ÐR��\0\0\0�C���ZÐR��\0\0\0����ZÐ1Ʌ�t!R:\nt:Jt:Jt:Jt����BBB��Z)�����Í@\01Ʌ�t-Rf;\nt f;Jtf;Jtf;Jt�����������Z)����T���Í@\01Ɋ\nB����ÐWPQ��1��f�u��X�X_�,���Í@\01Ʌ�t�J�����ÐSVW��\0����ً����\0\0\0~��\0\0\0���Y\0\0\0;��Å�u3��P�D$�Ϻ�\0\0������}3��;�}�È��~�V�̑�������\0\0\0_^[Ð��tÐ\0\0��U@\0Ë�t�@���Ë�SVWUQ��$��������؅�tB�$�\0�������3�I������~��ɋ׋$�\0������ɍw�������$���3���Z]_^[ÐSVWU���L$��$���}����؋D$�r������u��t1���������ɋ׋��-�����ɍ_�D$','pictures','png'),('402881763a019ea9013a01af5caf0016','笔',1,'plug-in/accordion/images/pencil.png','\0��P@\0Ë��t8�J�It2S�ËB�������P�H��v���X�H�I|��H�u�@�������[��Í@\0����Ë�����Ë�S��t-�X��t&J|9�})Ӆ�|9�D$�|����1������D$�y���[�\0�SVW�É։��������t0�J�N|*9�}&��~\")�9�~��)���������؋R�)��\0\0\0_^[Å�tVSVWU�É։ϋR��t�R�O}1�9�~�׋k����u\0\0\0X9�u���/�H�)����w����؋����j���]_^[Ð��t@��t1SVW�Ɖ׋O�W�V�Jx�F)�~�u��VW���_^t����Z1��1��Z��)�_^[Í@\0SVW�É�1���~H���t#�x�u����	P������X����p��\0�(�������ǋ��t��H�9�|����������,����;_^[Ë�SVW�É։ω�������~���������������_^[Ë�3��\0\0\0�S�Ӊ�1Ʌ�t�K�)�Q����Y��[�Y���ð����Å�tPj\0�����������Í@\0���tR����Í@\0���t�\0\0\0\0\0PR����XÍ@\0SV�É֋��t�\0\0\0\0P�x�����Nu�^[Í@\0��������J��������QRP�I������q���������Ë�SVWU�����P����$���	���x����]�n���\0\0}(V�D$�L$��\0\0�8����؅�~�T$�ǋ��9\0\0\0�*�݋ǋ���\0\0V��L$���\n����؅�}3ۋǋ���\0\0��\0\0]_^[Ë�����PQR�����������Z�2�����ÐR��\0\0\0�C���ZÐR��\0\0\0����ZÐ1Ʌ�t!R:\nt:Jt:Jt:Jt����BBB��Z)�����Í@\01Ʌ�t-Rf;\nt f;Jtf;Jtf;Jt�����������Z)����T���Í@\01Ɋ\nB����ÐWPQ��1��f�u��X�X_�,���Í@\01Ʌ�t�J�����ÐSVW��\0����ً����\0\0\0~��\0\0\0���Y\0\0\0;��Å�u3��P�D$�Ϻ�\0\0��','pencil','png'),('402881763a019ea9013a01af94b70017','地图',1,'plug-in/accordion/images/map.png','\0��P@\0Ë��t8�J�It2S�ËB�������P�H��v���X�H�I|��H�u�@�������[��Í@\0����Ë�����Ë�S��t-�X��t&J|9�})Ӆ�|9�D$�|����1������D$�y���[�\0�SVW�É։��������t0�J�N|*9�}&��~\")�9�~��)���������؋R�)��\0\0\0_^[Å�tVSVWU�É։ϋR��t�R�O}1�9�~�׋k����u\0\0\0X9�u���/�H�)����w����؋����j���]_^[Ð��t@��t1SVW�Ɖ׋O�W�V�Jx�F)�~�u��VW���_^t����Z1��1��Z��)�_^[Í@\0SVW�É�1���~H���t#�x�u����	P������X����p��\0�(�������ǋ��t��H�9�|����������,����;_^[Ë�SVW�É։ω�������~���������������_^[Ë�3��\0\0\0�S�Ӊ�1Ʌ�t�K�)�Q����Y��[�Y���ð����Å�tPj\0�����������Í@\0���tR����Í@\0���t�\0\0\0\0\0PR����XÍ@\0SV�É֋��t�\0\0\0\0P�x�����Nu�^[Í@\0��������J��������QRP�I������q���������Ë�SVWU�����P����$���	���x����]�n���\0\0}(V�D$�L$��\0\0�8����؅�~�T$�ǋ��9\0\0\0�*�݋ǋ���\0\0V��L$���\n����؅�}3ۋǋ���\0\0��\0\0]_^[Ë�����PQR�����������Z�2�����ÐR��\0\0\0�C���ZÐR��\0\0\0����ZÐ1Ʌ�t!R:\nt:Jt:Jt:Jt����BBB��Z)�����Í@\01Ʌ�t-Rf;\nt f;Jtf;Jtf;Jt�����������Z)����T���Í@\01Ɋ\nB����ÐWPQ��1��f�u��X�X_�,���Í@\01Ʌ�t�J�����ÐSVW��\0����ً����\0\0\0~��\0\0\0���Y\0\0\0;��Å�u3��P�D$�Ϻ�\0\0������}3��;�}�È��~�V�̑�������\0\0\0_^[Ð��tÐ\0\0��U@\0Ë�t�@���Ë�SVWUQ��$��������؅�tB�$�\0�������3�I������~��ɋ׋$�\0������ɍw�������$���3���Z]_^[ÐSVWU���L$��$���}����؋D$�r������u��t1���������ɋ׋��-�����ɍ_�D$�����$�������YZ]_^[�SVRP��1L���tA�Ju�������P�ƋD����t\n�H�������Ku�ZX����Z^[X�$����SVW�Ɖ�9�tr��tQ��tT�F�W�)�w�R��t&��9�uAJt�N�_9�u4����Ju������Z��tf�f�f9�u!���W�)���F�)��Zf9�u	����f9�_^[Í@\0U��SVW��ڋ�','map','png'),('402881763a019ea9013a01b009b80019','组',1,'plug-in/accordion/images/group_add.png','\0��P@\0Ë��t8�J�It2S�ËB�������P�H��v���X�H�I|��H�u�@�������[��Í@\0����Ë�����Ë�S��t-�X��t&J|9�})Ӆ�|9�D$�|����1������D$�y���[�\0�SVW�É։��������t0�J�N|*9�}&��~\")�9�~��)���������؋R�)��\0\0\0_^[Å�tVSVWU�É։ϋR��t�R�O}1�9�~�׋k����u\0\0\0X9�u���/�H�)����w����؋����j���]_^[Ð��t@��t1SVW�Ɖ׋O�W�V�Jx�F)�~�u��VW���_^t����Z1��1��Z��)�_^[Í@\0SVW�É�1���~H���t#�x�u����	P������X����p��\0�(�������ǋ��t��H�9�|����������,����;_^[Ë�SVW�É։ω�������~���������������_^[Ë�3��\0\0\0�S�Ӊ�1Ʌ�t�K�)�Q����Y��[�Y���ð����Å�tPj\0�����������Í@\0���tR����Í@\0���t�\0\0\0\0\0PR����XÍ@\0SV�É֋��t�\0\0\0\0P�x�����Nu�^[Í@\0��������J��������QRP�I������q���������Ë�SVWU�����P����$���	���x����]�n���\0\0}(V�D$�L$��\0\0�8����؅�~�T$�ǋ��9\0\0\0�*�݋ǋ���\0\0V��L$���\n����؅�}3ۋǋ���\0\0��\0\0]_^[Ë�����PQR�����������Z�2�����ÐR��\0\0\0�C���ZÐR��\0\0\0����ZÐ1Ʌ�t!R:\nt:Jt:Jt:Jt����BBB��Z)�����Í@\01Ʌ�t-Rf;\nt f;Jtf;Jtf;Jt�����������Z)����T���Í@\01Ɋ\nB����ÐWPQ��1��f�u��X�X_�,���Í@\01Ʌ�t�J�����ÐSVW��\0����ً����\0\0\0~��\0\0\0���Y\0\0\0;��Å�u3��P�D$�Ϻ�\0\0������}3��;�}�È��~�V�̑�������\0\0\0_^[Ð��tÐ\0\0��U@\0Ë�t�@���Ë�SVWUQ��$��������؅�tB�$�\0�������3�I������~��ɋ׋$�\0������ɍw�������$���3���Z]_^[ÐSVWU���L$��$���}����؋D$�r������u��t1���������ɋ׋��-�����ɍ_�D$�����$�������YZ]_^[�SVRP��1L���tA�Ju�������P�ƋD����t\n�H�������Ku�ZX����Z^[X�$����SVW�Ɖ','group_add','png'),('402881763a019ea9013a01b043a4001a','计算器',1,'plug-in/accordion/images/calculator.png','\0��P@\0Ë��t8�J�It2S�ËB�������P�H��v���X�H�I|��H�u�@�������[��Í@\0����Ë�����Ë�S��t-�X��t&J|9�})Ӆ�|9�D$�|����1������D$�y���[�\0�SVW�É։��������t0�J�N|*9�}&��~\")�9�~��)���������؋R�)��\0\0\0_^[Å�tVSVWU�É։ϋR��t�R�O}1�9�~�׋k����u\0\0\0X9�u���/�H�)����w����؋����j���]_^[Ð��t@��t1SVW�Ɖ׋O�W�V�Jx�F)�~�u��VW���_^t����Z1��1��Z��)�_^[Í@\0SVW�É�1���~H���t#�x�u����	P������X����p��\0�(�������ǋ��t��H�9�|����������,����;_^[Ë�SVW�É։ω�������~���������������_^[Ë�3��\0\0\0�S�Ӊ�1Ʌ�t�K�)�Q����Y��[�Y���ð����Å�tPj\0�����������Í@\0���tR����Í@\0���t�\0\0\0\0\0PR����XÍ@\0SV�É֋��t�\0\0\0\0P�x�����Nu�^[Í@\0��������J��������QRP�I������q���������Ë�SVWU�����P����$���	���x����]�n���\0\0}(V�D$�L$��\0\0�8����؅�~�T$�ǋ��9\0\0\0�*�݋ǋ���\0\0V��L$���\n����؅�}3ۋǋ���\0\0��\0\0]_^[Ë�����PQR�����������Z�2�����ÐR��\0\0\0�C���ZÐR��\0\0\0����ZÐ1Ʌ�t!R:\nt:Jt:Jt:Jt����BBB��Z)�����Í@\01Ʌ�t-Rf;\nt f;Jtf;Jtf;Jt�����������Z)����T���Í@\01Ɋ\nB����ÐWPQ��1��f�u��X�X_�,���Í@\01Ʌ�','calculator','png'),('55','folder.png',1,'plug-in/accordion/images/folder.png',NULL,'folder','png');

/*Table structure for table `t_module` */

DROP TABLE IF EXISTS `t_module`;

CREATE TABLE `t_module` (
  `id` varchar(64) NOT NULL DEFAULT 'uuid()',
  `index` bigint(20) DEFAULT '0',
  `name` varchar(256) DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `parent` varchar(64) DEFAULT NULL,
  `depth` bigint(20) DEFAULT '0',
  `is_group` bigint(20) DEFAULT '0',
  `is_invoked` bigint(20) DEFAULT '1',
  `url` varchar(256) DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_module` */

insert  into `t_module`(`id`,`index`,`name`,`title`,`path`,`parent`,`depth`,`is_group`,`is_invoked`,`url`,`remark`) values ('6eb5ccec-1c90-11e4-86c8-00ffac811b65',0,'2222','菜单管理','0','d45858c8-1c8e-11e4-86c8-00ffac811b65',1,1,1,NULL,NULL),('d45858c8-1c8e-11e4-86c8-00ffac811b65',0,'111','系统管理','0',NULL,0,0,1,NULL,NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(32) NOT NULL COMMENT '角色ID',
  `rolename` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `rolecode` varchar(15) DEFAULT NULL COMMENT '角色编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`rolename`,`rolecode`) values ('30','管理员','admin');

/*Table structure for table `t_role_function` */

DROP TABLE IF EXISTS `t_role_function`;

CREATE TABLE `t_role_function` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `roleid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `functionid` varchar(32) DEFAULT NULL COMMENT '权限ID',
  `operation` varchar(200) DEFAULT NULL COMMENT '操作权限代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_function` */

insert  into `t_role_function`(`id`,`roleid`,`functionid`,`operation`) values ('297e86303e9ca869013e9cc390c00001','30','28',NULL),('297e86303e9ca869013e9cc390cd0002','30','297e86303e8bee63013e8bf0ef9e0001',NULL),('297e86303e9ca869013e9cc390d40003','30','30',NULL),('297e86303e9ca869013e9cc390db0004','30','31',NULL),('297e86303e9ca869013e9cc390e10005','30','402881e53c4c64d8013c4c67b8470001',NULL),('297e86303e9ca869013e9cc390e80006','30','402881e93e7e8722013e7e8970740001',NULL),('297e86303e9ca869013e9cc390f10007','30','71',NULL),('297e86303e9ca869013e9cc390f90008','30','80',NULL),('297e86303e9ca869013e9cc390ff0009','30','49',NULL),('297e86303e9ca869013e9cc39110000a','30','27',NULL),('297e86303e9ca869013e9cc39117000b','30','402881e93cb3f340013cb3f49af60001',NULL);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `phone` varchar(120) DEFAULT NULL,
  `passwd` varchar(12) NOT NULL,
  `is_invoked` int(1) DEFAULT '1' COMMENT '是否启用',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

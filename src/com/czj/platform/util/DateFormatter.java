/**
 * Copyright 2013-2014 合肥康丽科技有限公司 All rights reserved.
 *
 * @项目名称 	instrument
 * @文件名称		DateFormatter.java
 * @创建日期		2014年4月14日
 * @作　　者		JackyCheng
 * 
 * @版权声明		本软件及其源码归合肥康丽科技有限公司所有，其内容仅限于合肥康丽科技有限公司
 * 						内部传阅，禁止外泄以及用于其它的商业目的。
 */
package com.czj.platform.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author JackyCheng
 * @since 2014年4月14日 下午4:29:07
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatter {
	String value();
}

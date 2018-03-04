package com.czj.platform.util;

public class DataSourceContextHolder {  
	 
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
  
    public static void setDbType(String dbType) {  
        contextHolder.set(dbType);  
    }  
  
    public static String getDbType() {
    	System.out.println("========================datasource:" + (String) contextHolder.get());
        return ((String) contextHolder.get());  
    }  
  
    public static void clearDbType() {  
        contextHolder.remove();  
    }  
}

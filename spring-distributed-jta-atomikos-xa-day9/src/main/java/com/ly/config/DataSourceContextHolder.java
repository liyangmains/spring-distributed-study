package com.ly.config;

import java.util.ArrayList;
import java.util.List;

public class DataSourceContextHolder {
	 private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	 //存放数据源id
     public static List<String> dataSourceIds = new ArrayList<String>();
	    // 设置数据源名
	    public static void setDataSourceKey(String dbName) {
	        contextHolder.set(dbName);
	    }

	    // 获取数据源名
	    public static String getDataSourceKey() {
	        return (contextHolder.get());
	    }

	    // 清除数据源名
	    public static void clearDataSourceKey() {
	        contextHolder.remove();
	    }
	    //判断当前数据源是否存在
	    public static boolean isContainsDataSource(String dataSourceId) {
	        return dataSourceIds.contains(dataSourceId);
	    }
}

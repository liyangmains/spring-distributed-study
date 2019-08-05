package com.ly.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ly.annotation.TargetDataSource;
import com.ly.config.DataSourceContextHolder;
import com.ly.constant.DataEnum;

/**
 * @Author Mr.Lz
 * @Description 动态数据源通知
 * @Date 2018-07-29
 */
@Aspect
@Order(-1)//保证在@Transactional之前执行
@Component
public class DynamicDattaSourceAspect {

	 private Logger logger = LoggerFactory.getLogger(DynamicDattaSourceAspect.class);

    //改变数据源
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
    	 String dbid = targetDataSource.name();
    	 DataEnum dateSource=DataEnum.isDataSource(dbid);
    	 if(dateSource == null){
    		 DataSourceContextHolder.setDataSourceKey(DataEnum.数据源1.getStateInfo());
    	 }else{
    		 DataSourceContextHolder.setDataSourceKey(dateSource.getStateInfo()); 
    	 }
    }

    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        logger.debug("清除数据源 " + targetDataSource.name() + " !");
        DataSourceContextHolder.clearDataSourceKey();
    }
}

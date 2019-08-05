package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
/**
 * demo地址https://blog.csdn.net/m0_37809146/article/details/86673372
 * https://github.com/heibaiying/spring-samples-for-all/tree/master/spring-boot/springboot-druid-mybatis-multi
 * 开启基于注解的dubbo功能（主要是包扫描@DubboComponentScan）
 * 也可以在配置文件中使用dubbo.scan.base-package来替代   @EnableDubbo
 * @author liY
 *
 */
//@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MyApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MyApplication.class, args);
	}

}

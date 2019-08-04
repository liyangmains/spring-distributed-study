package com.ly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
/**
 * 消费端
 * demo地址https://www.cnblogs.com/zjfjava/p/9696086.html
 * 开启基于注解的dubbo功能（主要是包扫描@DubboComponentScan）
 * 也可以在配置文件中使用dubbo.scan.base-package来替代   @EnableDubbo
 * @author liY
 *
 */
@EnableDubbo
@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MyApplication.class, args);
	}

}

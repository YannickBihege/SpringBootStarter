package com.udacity.jwdnd.course1.cloudstorage.config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.udacity.jwdnd.course1.cloudstorage.mapper")
public class MyBatisConfig {

}

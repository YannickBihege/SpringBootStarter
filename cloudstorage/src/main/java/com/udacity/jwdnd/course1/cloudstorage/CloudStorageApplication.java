package com.udacity.jwdnd.course1.cloudstorage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


//@EnableAutoConfiguration
@MapperScan("com.udacity.jwdnd.course1.cloudstorage.mapper")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class CloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

}

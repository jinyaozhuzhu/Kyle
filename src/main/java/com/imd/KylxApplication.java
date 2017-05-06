package com.imd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.imd.dao")
public class KylxApplication {

    public static void main(String[] args) {
        SpringApplication.run(KylxApplication.class, args);
    }
}

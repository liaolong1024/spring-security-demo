package com.lot.ccsmsb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages ={"com.lot.ccsmsb.admin.mapper"})
public class CcsmsbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcsmsbApplication.class, args);
    }

}

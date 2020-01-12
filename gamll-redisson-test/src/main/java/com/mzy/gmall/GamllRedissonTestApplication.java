package com.mzy.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mzy.gamll")
public class GamllRedissonTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamllRedissonTestApplication.class, args);
    }

}

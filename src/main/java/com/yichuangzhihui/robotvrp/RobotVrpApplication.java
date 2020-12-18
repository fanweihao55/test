package com.yichuangzhihui.robotvrp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yichuangzhihui.robotvrp.mapper")
public class RobotVrpApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotVrpApplication.class, args);
    }

}

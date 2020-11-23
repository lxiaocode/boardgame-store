package com.lxiaocode.boardgame.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 后台管理服务
 * @author lixiaofeng
 * @date 2020/11/17 下午4:17
 * @blog http://www.lxiaocode.com/
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lxiaocode.boardgame.**"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}

package com.lxiaocode.boardgame.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 前台商城服务
 * @author lixiaofeng
 * @date 2020/11/17 下午4:06
 * @blog http://www.lxiaocode.com/
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lxiaocode.boardgame.**"})
public class BoardgameApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardgameApplication.class, args);
    }
}

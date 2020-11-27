package com.lxiaocode.boardgame.admin;

import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.search.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 后台管理服务
 * @author lixiaofeng
 * @date 2020/11/17 下午4:17
 * @blog http://www.lxiaocode.com/
 */
@SpringBootApplication
@RestController
@RequestMapping("/import")
@ComponentScan(basePackages = {"com.lxiaocode.boardgame.**"})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Autowired
    private EsProductService esProductService;

    @PostMapping
    public Result importAll() throws IOException {
        int i = esProductService.importAll();
        return Result.success().addResult(i);
    }
}

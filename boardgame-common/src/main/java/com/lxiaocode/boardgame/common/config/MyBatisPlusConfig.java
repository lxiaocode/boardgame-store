package com.lxiaocode.boardgame.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午6:43
 * @blog http://www.lxiaocode.com/
 */
@Configuration
@MapperScan("com.lxiaocode.boardgame.*.domain")
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new CustomMetaObjectHandler();
    }
}

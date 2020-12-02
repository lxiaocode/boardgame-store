package com.lxiaocode.boardgame.manager.constant;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:17
 * @blog http://www.lxiaocode.com/
 */
public enum StatusEnum {
    DISENABLED("禁用中"),
    ENABLED("启用中");
    ;
    @JsonValue
    private String name;

    StatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

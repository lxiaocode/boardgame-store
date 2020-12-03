package com.lxiaocode.boardgame.manager.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lixiaofeng
 * @date 2020/12/2 下午3:17
 * @blog http://www.lxiaocode.com/
 */
public enum StatusEnum {
    DISENABLED(-1, "禁用中"),
    ENABLED(1, "启用中");
    ;
    @JsonValue
    private String name;
    @EnumValue
    private Integer code;

    StatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

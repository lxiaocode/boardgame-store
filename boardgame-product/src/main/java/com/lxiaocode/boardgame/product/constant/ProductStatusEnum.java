package com.lxiaocode.boardgame.product.constant;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lixiaofeng
 * @date 2020/11/22 上午11:13
 * @blog http://www.lxiaocode.com/
 */
public enum ProductStatusEnum {

    UNDER_REVIEW("审核中", -1),
    FOR_SALE("待售中", 0),
    SELLING("出售中", 1),
    SOLD_OUT("售罄", 2),
    RESTOCKING("补货中", 3),
    ;

    @JsonValue
    private final String name;
    @EnumValue
    private final int code;

    @JsonCreator
    ProductStatusEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}

package com.lxiaocode.boardgame.product.constant;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午2:37
 * @blog http://www.lxiaocode.com/
 */
public enum GameModeEnum {

    COMPETITION("竞争"),
    COOPERATION("合作"),
    HYBRID("混合")
    ;

    @JsonValue
    @EnumValue
    private String name;

    GameModeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

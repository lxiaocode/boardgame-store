package com.lxiaocode.boardgame.search.domain;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author lixiaofeng
 * @date 2020/11/28 上午10:30
 * @blog http://www.lxiaocode.com/
 */
@Data
public class EsParameter {

    private String item;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String value;
}

package com.lxiaocode.boardgame.search.domain;

import com.lxiaocode.boardgame.product.constant.GameModeEnum;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/12/1 下午3:29
 * @blog http://www.lxiaocode.com/
 */
@Data
public class EsProductParameter {

    private Integer minPerson;

    private Integer maxPerson;

    private GameModeEnum mode;

    private Integer age;

    @Field(type = FieldType.Keyword)
    private String year;

    private Integer difficulty;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private List<String> category;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private List<String> mechanics;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private List<String> theme;

    private List<EsParameter> otherParameters;
}

package com.lxiaocode.boardgame.search.domain.vo;

import com.lxiaocode.boardgame.product.constant.GameModeEnum;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.search.domain.EsParameter;
import com.lxiaocode.boardgame.search.domain.EsProductParameter;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/12/1 下午8:45
 * @blog http://www.lxiaocode.com/
 */
@Data
public class EsProductParameterVO {

    private String id;

    private String title;

    private EsProductParameter parameter;
}

package com.lxiaocode.boardgame.search.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxiaocode.boardgame.product.constant.ProductStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/11/26 上午11:10
 * @blog http://www.lxiaocode.com/
 */
@Data
@Document(indexName = "product")
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -4709377152781815800L;

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Long)
    private Long price;

    private Float rate;

    @Field(type = FieldType.Keyword)
    private String cover;

    @Field(type = FieldType.Keyword)
    private String detail;

    @Field(type = FieldType.Keyword)
    private ProductStatusEnum status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private EsProductParameter parameter;
}

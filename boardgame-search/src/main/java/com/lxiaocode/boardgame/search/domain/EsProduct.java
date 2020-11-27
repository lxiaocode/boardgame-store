package com.lxiaocode.boardgame.search.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

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

    private Long price;

    private Float rate;

    private String cover;

    private String detail;

    @Field(type = FieldType.Keyword)
    private String status;

    private Date createTime;

    private Integer deleted;
}

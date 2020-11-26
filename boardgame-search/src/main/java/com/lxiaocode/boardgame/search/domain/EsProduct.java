package com.lxiaocode.boardgame.search.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lixiaofeng
 * @date 2020/11/26 上午11:10
 * @blog http://www.lxiaocode.com/
 */
@Data
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -4709377152781815800L;

    private String id;

    private String title;

    private String description;

    private Long price;

    private Float rate;

    private String cover;

    private String detail;

    private String status;

    private Date createTime;

    private Integer deleted;
}

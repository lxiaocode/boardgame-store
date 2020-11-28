package com.lxiaocode.boardgame.common.domain.vo;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/11/28 下午7:49
 * @blog http://www.lxiaocode.com/
 */
@Data
public class Page<T> {
    /**
     * 查询数据列表
     */
    private List<T> records;

    /**
     * 总数
     */
    private long total;
    /**
     * 每页显示条数，默认 10
     */
    private long size;

    /**
     * 当前页
     */
    private long current;

    /**
     * 总页数
     */
    private long pages;

    public void setPages() {
        if (getSize() == 0) {
            this.pages = 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        this.pages = pages;
    }
}

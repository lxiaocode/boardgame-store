package com.lxiaocode.boardgame.search.biz;

import com.lxiaocode.boardgame.common.domain.vo.Page;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.search.domain.vo.EsProductParameterVO;
import com.lxiaocode.boardgame.search.service.EsProductParameterService;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/1 下午11:06
 * @blog http://www.lxiaocode.com/
 */
@Service
public class EsProductParameterAction {
    @Autowired
    private EsProductParameterService esProductParameterService;

    public JsonResult<Page<EsProductParameterVO>> getProductParameter(int page, int size) {
        Page<EsProductParameterVO> esProductParameterVOPage = esProductParameterService.listProductParameter(page, size);
        return Result.success().addResult(esProductParameterVOPage);
    }

    public JsonResult<Page<EsProductParameterVO>> searchFullText(int page, int size, String keyword) {
        MultiMatchQueryBuilder text = QueryBuilders.multiMatchQuery(keyword, "title", "parameter.category", "parameter.mechanics", "parameter.theme");
        Page<EsProductParameterVO> esProductParameterVOPage = esProductParameterService.listProductParameterMatch(page, size, text);

        return Result.success().addResult(esProductParameterVOPage);
    }
}

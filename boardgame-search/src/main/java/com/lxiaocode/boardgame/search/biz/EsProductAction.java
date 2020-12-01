package com.lxiaocode.boardgame.search.biz;

import com.lxiaocode.boardgame.common.domain.vo.Page;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.search.domain.EsProduct;
import com.lxiaocode.boardgame.search.domain.dto.ProductSearchDTO;
import com.lxiaocode.boardgame.search.service.EsProductService;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/11/28 下午9:20
 * @blog http://www.lxiaocode.com/
 */
@Service
public class EsProductAction {

    @Autowired
    private EsProductService esProductService;

    public JsonResult<Page<EsProduct>> getProducts(int page, int size) {
        Page<EsProduct> esProductPage = esProductService.listProduct(page, size);

        return Result.success().addResult(esProductPage);
    }

    public JsonResult<Page<EsProduct>> searchFullText(int page, int size, String keyword) {
        MultiMatchQueryBuilder text = QueryBuilders.multiMatchQuery(keyword, "title", "description");
        Page<EsProduct> esProductPage = esProductService.listProductMatch(page, size, text);

        return Result.success().addResult(esProductPage);
    }

    public JsonResult<Page<EsProduct>> search(int page, int size, ProductSearchDTO productSearchDTO) {
        Page<EsProduct> esProductPage =
                esProductService.listProductMatch(page, size, productSearchDTO.getQueryBuilder());

        return Result.success().addResult(esProductPage);
    }
}

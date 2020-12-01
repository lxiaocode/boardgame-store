package com.lxiaocode.boardgame.search.service;

import com.lxiaocode.boardgame.common.domain.vo.Page;
import com.lxiaocode.boardgame.search.domain.EsProduct;
import com.lxiaocode.boardgame.search.domain.EsProductMapper;
import com.lxiaocode.boardgame.search.domain.vo.EsProductParameterVO;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author lixiaofeng
 * @date 2020/12/1 下午11:04
 * @blog http://www.lxiaocode.com/
 */
@Service
public class EsProductParameterService {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private final IndexCoordinates indexCoordinates = IndexCoordinates.of("product");

    //==================================================================================================================
    //          ProductParameter 商品参数搜索
    //==================================================================================================================
    public Page<EsProductParameterVO> listProductParameter(int page, int size) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withFields("id", "title", "parameter")
                .withPageable(PageRequest.of(page, size))
                .build();

        return searchEsProductParameter(query);
    }

    public Page<EsProductParameterVO> listProductParameterMatch(int page, int size, QueryBuilder queryBuilder) {
        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withFields()
                .withPageable(PageRequest.of(page, size))
                .build();

        return searchEsProductParameter(query);
    }

    private Page<EsProductParameterVO> searchEsProductParameter(Query query) {
        SearchHits<EsProduct> searchHits = elasticsearchOperations.search(query, EsProduct.class);

        Page<EsProductParameterVO> esProductParameterVOPage = new Page<>();
        esProductParameterVOPage.setRecords(
                searchHits.getSearchHits().stream()
                        .map(p -> {
                            EsProduct content = p.getContent();
                            EsProductParameterVO vo = new EsProductParameterVO();
                            BeanUtils.copyProperties(content, vo);

                            return vo;
                        }).collect(Collectors.toList())
        );
        esProductParameterVOPage.setCurrent(query.getPageable().getPageNumber());
        esProductParameterVOPage.setSize(query.getPageable().getPageSize());
        esProductParameterVOPage.setTotal(searchHits.getTotalHits());
        esProductParameterVOPage.setPages();

        return esProductParameterVOPage;
    }
}

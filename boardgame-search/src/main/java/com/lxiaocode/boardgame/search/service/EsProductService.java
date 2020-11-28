package com.lxiaocode.boardgame.search.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lxiaocode.boardgame.common.domain.vo.Page;
import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.ProductMapper;
import com.lxiaocode.boardgame.search.domain.EsProduct;
import com.lxiaocode.boardgame.search.domain.EsProductMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lixiaofeng
 * @date 2020/11/26 下午5:20
 * @blog http://www.lxiaocode.com/
 */
@Service
public class EsProductService {

    @Autowired
    private EsProductMapper esProductMapper;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private final IndexCoordinates indexCoordinates = IndexCoordinates.of("product");

    public int importAll() throws IOException {
//        EsProduct esProduct = new EsProduct();
//        List<Product> products = productMapper.selectList(null);
//        BeanUtils.copyProperties(products.get(1), esProduct);
//
//        IndexQuery indexQuery =
//                new IndexQueryBuilder().withId(esProduct.getId()).withObject(esProduct).build();
//
//        String index = elasticsearchOperations.index(indexQuery, indexCoordinates);
//        System.out.println(index);
//        return 1;

        List<EsProduct> esProducts = esProductMapper.allEsProduct();
        List<IndexQuery> collect = esProducts.stream().map(esProduct -> {
            return new IndexQueryBuilder().withId(esProduct.getId()).withObject(esProduct).build();
        }).collect(Collectors.toList());

        List<String> strings = elasticsearchOperations.bulkIndex(collect, indexCoordinates);
        strings.forEach(System.out::println);

        return 1;
    }

    public Page<EsProduct> listProduct(int page, int size) {

        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withFields()
                .withPageable(PageRequest.of(page, size))
                .build();

        return searchEsProducts(query);
    }

    public Page<EsProduct> listProductMatch(int page, int size, QueryBuilder queryBuilder) {

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withFields()
                .withPageable(PageRequest.of(page, size))
                .build();

        return searchEsProducts(query);
    }

    private Page<EsProduct> searchEsProducts(Query query) {
        SearchHits<EsProduct> searchHits = elasticsearchOperations.search(query, EsProduct.class);

        Page<EsProduct> esProductPage = new Page<>();
        esProductPage.setRecords(
                searchHits.getSearchHits().stream()
                        .map(SearchHit::getContent)
                        .collect(Collectors.toList())
        );
        esProductPage.setCurrent(query.getPageable().getPageNumber());
        esProductPage.setSize(query.getPageable().getPageSize());
        esProductPage.setTotal(searchHits.getTotalHits());
        esProductPage.setPages();

        return esProductPage;
    }
}

package com.lxiaocode.boardgame.search.service;

import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.ProductMapper;
import com.lxiaocode.boardgame.search.domain.EsProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/11/26 下午5:20
 * @blog http://www.lxiaocode.com/
 */
@Service
public class EsProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public int importAll() throws IOException {
        EsProduct esProduct = new EsProduct();
        List<Product> products = productMapper.selectList(null);
        BeanUtils.copyProperties(products.get(1), esProduct);

        IndexQuery indexQuery =
                new IndexQueryBuilder().withId(esProduct.getId()).withObject(esProduct).build();
        IndexCoordinates indexCoordinates = IndexCoordinates.of("product");

        String index = elasticsearchOperations.index(indexQuery, indexCoordinates);
        System.out.println(index);
        return 1;
    }
}

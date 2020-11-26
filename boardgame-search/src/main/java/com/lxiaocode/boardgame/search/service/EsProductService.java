package com.lxiaocode.boardgame.search.service;

import com.alibaba.fastjson.JSON;
import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.ProductMapper;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
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
    private RestHighLevelClient restHighLevelClient;

    public int importAll() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<Product> products = productMapper.selectList(null);

        for(Product product: products) {
            bulkRequest.add(new IndexRequest("product").source(JSON.toJSON(product), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        for (BulkItemResponse bulkItemResponse : bulk) {
            if (bulkItemResponse.isFailed()) {
                BulkItemResponse.Failure failure =
                        bulkItemResponse.getFailure();
                System.out.println(failure.getMessage());
            }
        }

        Iterator<BulkItemResponse> iterator = bulk.iterator();

        int result = 0;
        while (iterator.hasNext()){
            result++;
            iterator.next();

        }
        return result;
    }
}

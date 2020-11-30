package com.lxiaocode.boardgame.search.domain.dto;

import com.lxiaocode.boardgame.product.constant.ProductStatusEnum;
import lombok.Data;
import org.elasticsearch.index.query.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lixiaofeng
 * @date 2020/11/29 下午10:25
 * @blog http://www.lxiaocode.com/
 */
@Data
public class ProductSearchDTO {

    private Long[] priceRange;

    private Float[] rateRange;

    private Integer[] status;

    public QueryBuilder getQueryBuilder() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (priceRange != null && priceRange.length == 2 && priceRange[1] != 0L && priceRange[0] <= priceRange[1]){
            RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("price")
                    .gte(priceRange[0])
                    .lte(priceRange[1]);
            boolQueryBuilder.must(queryBuilder);
        }
        if (rateRange != null && rateRange.length == 2 && rateRange[1] != 0f && rateRange[0] <= rateRange[1]){
            RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("rate")
                    .gte(rateRange[0])
                    .lte(rateRange[1]);
            boolQueryBuilder.must(queryBuilder);
        }
        if (status != null && status.length > 0){
            List<ProductStatusEnum> statusEnums = Arrays.stream(status).map(ProductStatusEnum::getInstance).collect(Collectors.toList());
            TermsQueryBuilder queryBuilder = QueryBuilders.termsQuery("status", statusEnums);
            boolQueryBuilder.should(queryBuilder);
        }

        return boolQueryBuilder;
    }
}

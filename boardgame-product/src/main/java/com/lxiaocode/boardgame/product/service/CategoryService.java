package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.domain.Category;
import com.lxiaocode.boardgame.product.domain.CategoryMapper;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.product.domain.ParameterMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午3:41
 * @blog http://www.lxiaocode.com/
 */
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {

//    private static final String ITEM = "游戏类别";

    public void batchSaveCategory(Set<Category> categories) {
        super.saveBatch(categories, categories.size());
    }

//    public void batchSaveCategory(String productId, String[] categories) {
//        Set<Parameter> parameterSet = Arrays.stream(categories).map(c -> {
//            Parameter parameter = new Parameter();
//            parameter.setProductId(productId);
//            parameter.setItem(ITEM);
//            parameter.setValue(c);
//
//            return parameter;
//        }).collect(Collectors.toSet());
//        super.saveBatch(parameterSet, parameterSet.size());
//    }

    public void deleteByProductId(String productId) {
        super.lambdaUpdate()
                .eq(Category::getProductId, productId)
                .remove();
    }
}

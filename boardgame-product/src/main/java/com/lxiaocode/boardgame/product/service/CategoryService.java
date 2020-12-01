package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.domain.Category;
import com.lxiaocode.boardgame.product.domain.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author lixiaofeng
 * @date 2020/11/30 下午3:41
 * @blog http://www.lxiaocode.com/
 */
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {

    public void saveCategory(Category category) {
        super.save(category);
    }

    public void batchSaveCategory(Set<Category> categories) {
        super.saveBatch(categories, categories.size());
    }

    public void deleteByProductId(String productId) {
        super.lambdaUpdate()
                .eq(Category::getProductId, productId)
                .remove();
    }
}

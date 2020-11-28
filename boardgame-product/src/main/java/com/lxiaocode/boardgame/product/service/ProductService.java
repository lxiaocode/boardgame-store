package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.constant.ProductStatusEnum;
import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.ProductMapper;
import com.lxiaocode.boardgame.product.domain.vo.ProductDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午9:07
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {

    @Autowired
    private ProductMapper productMapper;

    public void saveProduct(Product product) {
        super.save(product);
    }

    public void updateProduct(Product product) {
        super.updateById(product);
    }

    public IPage<Product> pageProduct(IPage<Product> page) {
        return lambdaQuery().orderByDesc(Product::getCreateTime).page(page);
    }

    public void deleteProduct(String id) {
        super.removeById(id);
    }


    public ProductStatusEnum getProductIdStatus(String id) {
        return productMapper.getProductIdStatus(id);
    }
}

package com.lxiaocode.boardgame.product.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.constant.ProductStatusEnum;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.dto.ParameterDTO;
import com.lxiaocode.boardgame.product.domain.dto.ProductDTO;
import com.lxiaocode.boardgame.product.service.ProductService;
import com.lxiaocode.boardgame.product.service.StockpileService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午9:07
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ProductAction {

    private final ProductService productService;
    private final StockpileAction stockpileAction;

    public ProductAction(ProductService productService, StockpileAction stockpileAction){
        this.productService = productService;
        this.stockpileAction = stockpileAction;
    }

    /**
     * create product information
     * @param productDTO
     * @return
     */
    public Result createProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product.setStatus(ProductStatusEnum.UNDER_REVIEW);
        productService.saveProduct(product);
        // init stockpile
        stockpileAction.initStockpile(product.getId());

        return Result.success();
    }

    /**
     * update the status of the product
     * @param productId
     * @param code
     * @return
     */
    public Result updateProductStatus(String productId, int code) {
        Product product = new Product();
        product.setId(productId);
        product.setStatus(ProductStatusEnum.getInstance(code));
        productService.updateProduct(product);

        return Result.success();
    }

    public Result getProducts(int page, int size) {
        IPage<Product> productIPage = new Page<>();
        productIPage.setCurrent(page).setSize(size);

        IPage<Product> result = productService.pageProduct(productIPage);
        return Result.success().addResult(result);
    }
}

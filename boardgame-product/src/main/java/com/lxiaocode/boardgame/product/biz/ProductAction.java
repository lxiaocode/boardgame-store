package com.lxiaocode.boardgame.product.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.constant.ProductStatusEnum;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.product.domain.ParameterMapper;
import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.StockpileMapper;
import com.lxiaocode.boardgame.product.domain.dto.ParameterDTO;
import com.lxiaocode.boardgame.product.domain.dto.ProductDTO;
import com.lxiaocode.boardgame.product.service.ParameterService;
import com.lxiaocode.boardgame.product.service.ProductService;
import com.lxiaocode.boardgame.product.service.StockpileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午9:07
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ProductAction {

    @Autowired
    private ProductService productService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private StockpileService stockpileService;

    @Autowired
    private StockpileAction stockpileAction;


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

    public Result updateProduct(String productId, ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productId);
        BeanUtils.copyProperties(productDTO, product);
        productService.updateProduct(product);

        return Result.success();
    }

    @Transactional
    public Result deleteProduct(String productId) {
        // 判断商品状态，只有 审核中、待售中、售罄 的商品可以删除
        ProductStatusEnum status = productService.getProductIdStatus(productId);
        if (ProductStatusEnum.SELLING.equals(status) || ProductStatusEnum.RESTOCKING.equals(status)){
            return Result.fail(DefaultApiCode.CALIBRATION_FAIL, "当前商品无法删除");
        }

        // 先删除商品库存
        stockpileService.deleteStockpileByProduct(productId);
        // 再删除商品参数
        parameterService.deleteParameterByProductId(productId);
        // 最后删除商品信息
        productService.deleteProduct(productId);

        return Result.success();
    }

    public JsonResult<IPage<Product>> getProducts(int page, int size) {
        IPage<Product> productIPage = new Page<>();
        productIPage.setCurrent(page).setSize(size);

        IPage<Product> result = productService.pageProduct(productIPage);
        return Result.success().addResult(result);
    }
}

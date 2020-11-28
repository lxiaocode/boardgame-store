package com.lxiaocode.boardgame.admin.controller.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.biz.ParameterAction;
import com.lxiaocode.boardgame.product.biz.ProductAction;
import com.lxiaocode.boardgame.product.biz.StockpileAction;
import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.ProductMapper;
import com.lxiaocode.boardgame.product.domain.dto.ParameterDTO;
import com.lxiaocode.boardgame.product.domain.dto.ProductDTO;
import com.lxiaocode.boardgame.product.domain.dto.StockpileDTO;
import com.lxiaocode.boardgame.product.domain.vo.ProductDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午9:13
 * @blog http://www.lxiaocode.com/
 */
@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductAction productAction;

    @Autowired
    private ParameterAction parameterAction;

    @Autowired
    private StockpileAction stockpileAction;

    /**
     * 添加商品信息
     * @param productDTO
     * @return
     */
    @PostMapping("")
    public Result createProduct(@RequestBody ProductDTO productDTO) {
        return productAction.createProduct(productDTO);
    }

    /**
     * 添加商品参数
     * @param productId
     * @param parameterDTOS
     * @return
     */
    @PostMapping("/{productId}/parameters")
    public Result addParameters(@PathVariable String productId, @RequestBody ArrayList<ParameterDTO> parameterDTOS) {
        if (parameterDTOS.size() == 1){
            return parameterAction.saveParameter(productId, parameterDTOS.get(0));
        }else if (parameterDTOS.size() > 1){
            return parameterAction.batchSaveParameter(productId, parameterDTOS);
        }
        return Result.fail();
    }

    /**
     * 设置商品库存
     * @param productId
     * @param stockpileDTO
     * @return
     */
    @PutMapping("/{productId}/stockpile")
    public Result setStockpile(@PathVariable String productId, @RequestBody StockpileDTO stockpileDTO) {
        return stockpileAction.setStockpile(productId, stockpileDTO);
    }

    /**
     * 切换商品状态
     * @param productId
     * @param productStatus
     * @return
     */
    @PutMapping("/{productId}/status")
    public Result setStatus(@PathVariable String productId, @RequestParam Integer productStatus) {
        return productAction.updateProductStatus(productId, productStatus);
    }

    /**
     * 商品信息编辑
     * @param productId
     * @param productDTO
     * @return
     */
    @PutMapping("/{productId}")
    public Result updateProduct(@PathVariable String productId, @RequestBody ProductDTO productDTO) {
        return productAction.updateProduct(productId, productDTO);
    }

    /**
     * 删除商品
     * @param productId
     * @return
     */
    @DeleteMapping("/{productId}")
    public Result deleteProduct(@PathVariable String productId) {
        return productAction.deleteProduct(productId);
    }

    @GetMapping("")
    public JsonResult<IPage<Product>> getProducts(@RequestParam Integer page, @RequestParam Integer size) {
        return productAction.getProducts(page, size);
    }

    @GetMapping("/{productId}")
    public JsonResult<Product> getProduct() {
        // TODO 查询商品
        return Result.success().addResult(null);
    }
}

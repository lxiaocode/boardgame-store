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

    @PostMapping("")
    public Result createProduct(@RequestBody ProductDTO productDTO) {
        return productAction.createProduct(productDTO);
    }

    @PostMapping("/{id}/parameters")
    public Result addParameters(@PathVariable String id, @RequestBody ArrayList<ParameterDTO> parameterDTOS) {
        if (parameterDTOS.size() == 1){
            return parameterAction.saveParameter(id, parameterDTOS.get(0));
        }else if (parameterDTOS.size() > 1){
            return parameterAction.batchSaveParameter(id, parameterDTOS);
        }
        return Result.fail();
    }

    @PutMapping("/{id}/stockpile")
    public Result setStockpile(@PathVariable String id, @RequestBody StockpileDTO stockpileDTO) {
        return stockpileAction.setStockpile(id, stockpileDTO);
    }

    @PutMapping("/{id}/status")
    public Result setStatus(@PathVariable String id, @RequestParam Integer productStatus) {
        return productAction.updateProductStatus(id, productStatus);
    }

    @GetMapping("")
    public JsonResult<IPage<Product>> getProducts(@RequestParam Integer page, @RequestParam Integer size) {
        return productAction.getProducts(page, size);
    }
}

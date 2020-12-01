package com.lxiaocode.boardgame.admin.controller.product;

import com.lxiaocode.boardgame.common.domain.vo.Page;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.biz.ProductAction;
import com.lxiaocode.boardgame.product.domain.dto.ProductDTO;
import com.lxiaocode.boardgame.search.biz.EsProductAction;
import com.lxiaocode.boardgame.search.domain.EsProduct;
import com.lxiaocode.boardgame.search.domain.dto.ProductSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private EsProductAction esProductAction;

    /**
     * 添加商品信息
     * @param productDTO
     * @return
     */
    @PostMapping("")
    public Result addProduct(@RequestBody ProductDTO productDTO) {
        return productAction.addProduct(productDTO);
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

    /**
     * 切换商品状态
     * @param productId
     * @param status
     * @return
     */
    @PutMapping("/{productId}/status/{status}")
    public Result setStatus(@PathVariable String productId, @PathVariable Integer status) {
        return productAction.updateProductStatus(productId, status);
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

    //==================================================================================================================
    //      Elasticsearch 接口
    //==================================================================================================================
    /**
     * 分页-商品完整信息(不包含库存)
     * @param page 当前页数，从 0 开始
     * @param size 每页大小
     * @return
     */
    @GetMapping("")
    public JsonResult<Page<EsProduct>> getProducts(@RequestParam Integer page, @RequestParam Integer size) {
        return esProductAction.getProducts(page, size);
    }

    /**
     * 分页-商品信息全文搜索
     * @param page
     * @param size
     * @param keyword
     * @return
     */
    @GetMapping("/text")
    public JsonResult<Page<EsProduct>> searchFullText(@RequestParam Integer page,
                                                      @RequestParam Integer size,
                                                      @RequestParam String keyword) {
        return esProductAction.searchFullText(page, size, keyword);
    }

    /**
     * 商品信息条件过滤查询
     * @param page
     * @param size
     * @param productSearchDTO
     * @return
     */
    @PostMapping("/search")
    public JsonResult<Page<EsProduct>> search(@RequestParam Integer page,
                                              @RequestParam Integer size,
                                              @RequestBody ProductSearchDTO productSearchDTO) {
        return esProductAction.search(page, size, productSearchDTO);
    }
}

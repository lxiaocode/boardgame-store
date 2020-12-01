package com.lxiaocode.boardgame.admin.controller.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.biz.StockpileAction;
import com.lxiaocode.boardgame.product.domain.dto.StockpileDTO;
import com.lxiaocode.boardgame.product.domain.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lixiaofeng
 * @date 2020/11/25 上午9:22
 * @blog http://www.lxiaocode.com/
 */
@RestController
@RequestMapping("/admin/product/stockpile")
public class StockpileController {

    @Autowired
    private StockpileAction stockpileAction;

    /**
     * 修改商品库存
     * @param stockpileDTO
     * @return
     */
    @PutMapping("")
    public Result setStockpile(@RequestBody @Valid StockpileDTO stockpileDTO) {
        return stockpileAction.setStockpile(stockpileDTO);
    }

    /**
     * 商品库存分页列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("")
    public JsonResult<IPage<StockpileVO>> getStockpiles(@RequestParam Integer page, @RequestParam Integer size) {
        return stockpileAction.getStockpiles(page, size);
    }

    /**
     * 商品库存查询
     * @param stockpileId
     * @return
     */
    @GetMapping("/{stockpileId}")
    public JsonResult<StockpileVO> getStockpile(@PathVariable String stockpileId) {
        return stockpileAction.getStockpile(stockpileId);
    }
}

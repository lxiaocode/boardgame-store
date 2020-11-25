package com.lxiaocode.boardgame.admin.controller.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.product.biz.StockpileAction;
import com.lxiaocode.boardgame.product.domain.Stockpile;
import com.lxiaocode.boardgame.product.domain.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("")
    public JsonResult<IPage<StockpileVO>> getStockpiles(@RequestParam Integer page, @RequestParam Integer size) {
        return stockpileAction.getStockpiles(page, size);
    }
}

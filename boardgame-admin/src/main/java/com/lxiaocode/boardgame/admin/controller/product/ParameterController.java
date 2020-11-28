package com.lxiaocode.boardgame.admin.controller.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lxiaocode.boardgame.common.response.JsonResult;
import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.biz.ParameterAction;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.product.domain.Product;
import com.lxiaocode.boardgame.product.domain.Stockpile;
import com.lxiaocode.boardgame.product.domain.vo.ParameterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lixiaofeng
 * @date 2020/11/25 上午9:52
 * @blog http://www.lxiaocode.com/
 */
@RestController
@RequestMapping("/admin/product/parameter")
public class ParameterController {

    @Autowired
    private ParameterAction parameterAction;

    @GetMapping("")
    public JsonResult<IPage<ParameterVO>> getStockpiles(@RequestParam Integer page, @RequestParam Integer size) {
        return parameterAction.getProducts(page, size);
    }

    @GetMapping("/{parameterId}")
    public JsonResult<Parameter> getParameter() {
        // TODO 查询商品参数
        return Result.success().addResult(null);
    }

    /**
     * 删除商品参数
     * @param parameterId
     * @return
     */
    @DeleteMapping("/{parameterId}")
    public Result deleteParameter(@PathVariable String parameterId) {
        return parameterAction.deleteParameter(parameterId);
    }
}

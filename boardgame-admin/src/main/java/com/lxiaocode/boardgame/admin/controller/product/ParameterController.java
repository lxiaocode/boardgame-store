package com.lxiaocode.boardgame.admin.controller.product;

import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.biz.ParameterAction;
import com.lxiaocode.boardgame.product.domain.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    /**
     * 添加商品参数
     * @param productParameterDTO
     * @return
     */
    @PostMapping("")
    public Result addParameters(@RequestBody @Valid ProductParameterDTO productParameterDTO) {
        return parameterAction.addProductParameter(productParameterDTO);
    }

    /**
     * 修改(删)其他参数
     * @param productId
     * @param parameterDTOS
     * @return
     */
    @PutMapping("")
    public Result modifyParameter(@RequestParam String productId, @RequestBody List<ParameterDTO> parameterDTOS) {
        return parameterAction.modifyParameter(productId, parameterDTOS);
    }

    /**
     * 修改基本参数
     * @param parameterPrimaryDTO
     * @return
     */
    @PutMapping("/primary")
    public Result modifyParameterPrimary(@RequestBody @Valid ParameterPrimaryDTO parameterPrimaryDTO) {
        return parameterAction.modifyParameterPrimary(parameterPrimaryDTO);
    }

    /**
     * 修改(删)分类
     * @param categoryDTO
     * @return
     */
    @PutMapping("/category")
    public Result modifyParameterCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return parameterAction.modifyParameterCategory(categoryDTO);
    }

    /**
     * 修改(删)机制
     * @param mechanicsDTO
     * @return
     */
    @PutMapping("/mechanics")
    public Result modifyParameterMechanics(@RequestBody @Valid MechanicsDTO mechanicsDTO) {
        return parameterAction.modifyParameterMechanics(mechanicsDTO);
    }

    /**
     * 修改(删)主题
     * @param themeDTO
     * @return
     */
    @PutMapping("/theme")
    public Result modifyParameterTheme(@RequestBody @Valid ThemeDTO themeDTO) {
        return parameterAction.modifyParameterTheme(themeDTO);
    }

//    @GetMapping("")
//    public JsonResult<IPage<ParameterVO>> getStockpiles(@RequestParam Integer page, @RequestParam Integer size) {
//        return parameterAction.getProducts(page, size);
//    }
//
//    @GetMapping("/{parameterId}")
//    public JsonResult<Parameter> getParameter() {
//        // TODO 查询商品参数
//        return Result.success().addResult(null);
//    }

//    /**
//     * 删除商品参数
//     * @param parameterId
//     * @return
//     */
//    @DeleteMapping("/{parameterId}")
//    public Result deleteParameter(@PathVariable String parameterId) {
//        return parameterAction.deleteParameter(parameterId);
//    }
}

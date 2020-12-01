package com.lxiaocode.boardgame.product.biz;

import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.domain.*;
import com.lxiaocode.boardgame.product.domain.dto.*;
import com.lxiaocode.boardgame.product.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午10:19
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ParameterAction {

    @Autowired
    private ParameterService parameterService;
    @Autowired
    private ParameterPrimaryService parameterPrimaryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MechanicsService mechanicsService;
    @Autowired
    private ThemeService themeService;

    /**
     * 添加商品参数
     * @param productParameterDTO
     * @return
     */
    public Result addProductParameter(ProductParameterDTO productParameterDTO) {
        String productId = productParameterDTO.getProductId();
        // 添加主要参数
        parameterPrimaryService.saveParameterPrimary(productParameterDTO.getParameterPrimary());
        // 添加分类、机制、主题
        categoryService.batchSaveCategory(productParameterDTO.getCategorySet());
        mechanicsService.batchSaveMechanics(productParameterDTO.getMechanicsSet());
        themeService.batchSaveTheme(productParameterDTO.getThemeSet());
        // 添加其他参数
        if (productParameterDTO.getParameters() != null
                && productParameterDTO.getParameters().size() > 0){
            this.modifyParameter(productParameterDTO.getProductId(), productParameterDTO.getParameters());
        }
        return Result.success();
    }

    /**
     * 当删除商品时，删除商品参数
     * @param productId
     * @return
     */
    public void deleteProductParameter(String productId) {
        // 删除其他参数
        parameterService.deleteByProductId(productId);
        // 删除主要参数
        parameterPrimaryService.deletedByProductId(productId);

        // 删除分类、机制、主题
        categoryService.deleteByProductId(productId);
        mechanicsService.deleteByProductId(productId);
        themeService.deleteByProductId(productId);
    }

    /**
     * 修改(删)其他参数
     * @param productId
     * @param parameterDTOS
     * @return
     */
    public Result modifyParameter(String productId, List<ParameterDTO> parameterDTOS) {
        Set<Parameter> parameterSet = parameterDTOS.parallelStream().map(parameterDTO -> {
            Parameter parameter = new Parameter();
            parameter.setProductId(productId);
            BeanUtils.copyProperties(parameterDTO, parameter);
            return parameter;
        }).collect(Collectors.toSet());

        parameterService.deleteByProductId(productId);
        parameterService.batchSaveParameter(parameterSet);

        return Result.success();
    }

    /**
     * 修改基本参数
     * @param parameterPrimaryDTO
     * @return
     */
    public Result modifyParameterPrimary(ParameterPrimaryDTO parameterPrimaryDTO) {
        ParameterPrimary parameterPrimary = new ParameterPrimary();
        BeanUtils.copyProperties(parameterPrimaryDTO, parameterPrimary);
        parameterPrimaryService.updateByProductId(parameterPrimary);

        return Result.success();
    }

    /**
     * 修改(删)分类
     * @param categoryDTO
     * @return
     */
    @Transactional
    public Result modifyParameterCategory(CategoryDTO categoryDTO) {
        Set<Category> categorySet = Arrays.stream(categoryDTO.getCategories()).map(c -> {
            Category category = new Category();
            category.setProductId(categoryDTO.getProductId());
            category.setValue(c);

            return category;
        }).collect(Collectors.toSet());

        categoryService.deleteByProductId(categoryDTO.getProductId());
        categoryService.batchSaveCategory(categorySet);

        return Result.success();
    }

    /**
     * 修改(删)机制
     * @param mechanicsDTO
     * @return
     */
    @Transactional
    public Result modifyParameterMechanics(MechanicsDTO mechanicsDTO) {
        Set<Mechanics> mechanicsSet = Arrays.stream(mechanicsDTO.getMechanics()).map(c -> {
            Mechanics mechanics = new Mechanics();
            mechanics.setProductId(mechanicsDTO.getProductId());
            mechanics.setValue(c);

            return mechanics;
        }).collect(Collectors.toSet());

        mechanicsService.deleteByProductId(mechanicsDTO.getProductId());
        mechanicsService.batchSaveMechanics(mechanicsSet);

        return Result.success();
    }

    /**
     * 修改(删)主题
     * @param themeDTO
     * @return
     */
    public Result modifyParameterTheme(ThemeDTO themeDTO) {
        Set<Theme> themeSet = Arrays.stream(themeDTO.getThemes()).map(t -> {
            Theme theme = new Theme();
            theme.setProductId(themeDTO.getProductId());
            theme.setValue(t);

            return theme;
        }).collect(Collectors.toSet());

        themeService.deleteByProductId(themeDTO.getProductId());
        themeService.batchSaveTheme(themeSet);

        return Result.success();
    }
}

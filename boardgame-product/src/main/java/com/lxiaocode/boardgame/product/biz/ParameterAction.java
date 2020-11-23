package com.lxiaocode.boardgame.product.biz;

import com.lxiaocode.boardgame.common.response.Result;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.product.domain.dto.ParameterDTO;
import com.lxiaocode.boardgame.product.service.ParameterService;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArraysOfLong;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午10:19
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ParameterAction {

    private final ParameterService parameterService;

    public ParameterAction(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public Result saveParameter(String productId, ParameterDTO parameterDTO) {
        Parameter parameter = new Parameter();
        parameter.setProductId(productId);
        BeanUtils.copyProperties(parameterDTO, parameter);
        parameterService.saveParameter(parameter);

        return Result.success();
    }

    public Result batchSaveParameter(String productId, ArrayList<ParameterDTO> parameterDTOS) {
        Set<Parameter> parameterSet = parameterDTOS.parallelStream().map(parameterDTO -> {
            Parameter parameter = new Parameter();
            parameter.setProductId(productId);
            BeanUtils.copyProperties(parameterDTO, parameter);
            return parameter;
        }).collect(Collectors.toSet());
        parameterService.batchSaveParameter(parameterSet);

        return Result.success();
    }
}

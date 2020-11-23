package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.product.domain.ParameterMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午10:19
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ParameterService extends ServiceImpl<ParameterMapper, Parameter> {

    public void saveParameter(Parameter parameter) {
        super.save(parameter);
    }

    public void batchSaveParameter(Set<Parameter> parameters) {
        super.saveBatch(parameters, parameters.size());
    }
}

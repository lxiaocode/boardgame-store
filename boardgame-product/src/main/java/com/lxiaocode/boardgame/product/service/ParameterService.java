package com.lxiaocode.boardgame.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.product.domain.Parameter;
import com.lxiaocode.boardgame.product.domain.ParameterMapper;
import com.lxiaocode.boardgame.product.domain.vo.ParameterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午10:19
 * @blog http://www.lxiaocode.com/
 */
@Service
public class ParameterService extends ServiceImpl<ParameterMapper, Parameter> {

    @Autowired
    private ParameterMapper parameterMapper;

    public void saveParameter(Parameter parameter) {
        super.save(parameter);
    }

    public void batchSaveParameter(Set<Parameter> parameters) {
        super.saveBatch(parameters, parameters.size());
    }

    public IPage<ParameterVO> pageParameter(IPage<ParameterVO> page) {
        return parameterMapper.pageParameter(page);
    }

    public void deleteParameter(String id) {
        super.removeById(id);
    }

    public void deleteByProductId(String productId) {
        super.lambdaUpdate()
                .eq(Parameter::getProductId, productId)
                .remove();
    }
}

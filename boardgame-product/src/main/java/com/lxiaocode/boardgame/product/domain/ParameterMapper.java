package com.lxiaocode.boardgame.product.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxiaocode.boardgame.product.domain.vo.ParameterVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lixiaofeng
 * @date 2020/11/23 上午10:18
 * @blog http://www.lxiaocode.com/
 */
@Mapper
public interface ParameterMapper extends BaseMapper<Parameter> {

    IPage<ParameterVO> pageParameter(IPage<ParameterVO> page);
}

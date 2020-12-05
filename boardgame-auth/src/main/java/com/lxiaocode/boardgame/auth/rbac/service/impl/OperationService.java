package com.lxiaocode.boardgame.auth.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxiaocode.boardgame.auth.rbac.domain.Operation;
import com.lxiaocode.boardgame.auth.rbac.domain.OperationMapper;
import org.springframework.stereotype.Service;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午2:50
 * @blog http://www.lxiaocode.com/
 */
@Service
public class OperationService extends ServiceImpl<OperationMapper, Operation> {

    public void saveOne(Operation operation) {
        super.save(operation);
    }

    public boolean checkUnique(String url) {
        Integer count = super.lambdaQuery()
                .eq(Operation::getUrl, url)
                .count();

        return count == 0;
    }
}

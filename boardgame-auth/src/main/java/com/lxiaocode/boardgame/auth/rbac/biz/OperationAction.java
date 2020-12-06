package com.lxiaocode.boardgame.auth.rbac.biz;

import com.lxiaocode.boardgame.auth.rbac.PermissionEnum;
import com.lxiaocode.boardgame.auth.rbac.domain.Operation;
import com.lxiaocode.boardgame.auth.rbac.domain.dto.AdditionOperationDTO;
import com.lxiaocode.boardgame.auth.rbac.service.impl.OperationService;
import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lixiaofeng
 * @date 2020/12/3 下午3:49
 * @blog http://www.lxiaocode.com/
 */
@Service
public class OperationAction {

    @Autowired
    private PermissionAction permissionAction;

    private final OperationService operationService;

    public OperationAction(OperationService operationService) {
        this.operationService = operationService;
    }

    @Transactional
    public Result addition(AdditionOperationDTO additionOperationDTO) {
        if (! operationService.checkUnique(additionOperationDTO.getUrl())){
            return Result.fail(DefaultApiCode.CALIBRATION_FAIL, additionOperationDTO.getUrl() + " 已存在");
        }
        Operation operation = new Operation();
        BeanUtils.copyProperties(additionOperationDTO, operation);
        operationService.saveOne(operation);
        permissionAction.addition(operation.getId(), PermissionEnum.OPERATION);

        return Result.success().addResult(operation);
    }
}

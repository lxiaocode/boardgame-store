package com.lxiaocode.boardgame.auth.rbac.biz;

import com.lxiaocode.boardgame.auth.rbac.PermissionEnum;
import com.lxiaocode.boardgame.auth.rbac.domain.Menu;
import com.lxiaocode.boardgame.auth.rbac.domain.dto.AdditionMenuDTO;
import com.lxiaocode.boardgame.auth.rbac.service.impl.MenuService;
import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import com.lxiaocode.boardgame.common.response.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午4:00
 * @blog http://www.lxiaocode.com/
 */
@Service
public class MenuAction {

    @Autowired
    private PermissionAction permissionAction;

    @Autowired
    private MenuService menuService;

    @Transactional
    public Result addition(AdditionMenuDTO additionMenuDTO) {
        if (! menuService.checkUnique(additionMenuDTO.getPath())){
            return Result.fail(DefaultApiCode.CALIBRATION_FAIL, additionMenuDTO.getPath() + " 已存在");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(additionMenuDTO, menu);
        menuService.saveOne(menu);
        permissionAction.addition(menu.getId(), PermissionEnum.MENU);

        return Result.success().addResult(menu);
    }
}

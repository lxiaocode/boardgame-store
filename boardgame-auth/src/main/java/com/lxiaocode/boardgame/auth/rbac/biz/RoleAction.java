package com.lxiaocode.boardgame.auth.rbac.biz;

import com.lxiaocode.boardgame.auth.rbac.domain.Role;
import com.lxiaocode.boardgame.auth.rbac.domain.RolePermissionRelation;
import com.lxiaocode.boardgame.auth.rbac.domain.dto.AdditionRoleDTO;
import com.lxiaocode.boardgame.auth.rbac.domain.dto.AdditionRoleToUserDTO;
import com.lxiaocode.boardgame.auth.rbac.service.impl.RolePermissionRelationService;
import com.lxiaocode.boardgame.auth.rbac.service.impl.RoleService;
import com.lxiaocode.boardgame.common.response.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lixiaofeng
 * @date 2020/12/6 下午5:09
 * @blog http://www.lxiaocode.com/
 */
@Service
public class RoleAction {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    public Result addition(AdditionRoleDTO additionRoleDTO) {
        // 校验唯一性
        if (!roleService.checkUnique(additionRoleDTO.getName())){
            // TODO 异常
            return Result.fail();
        }

        Role role = new Role();
        BeanUtils.copyProperties(additionRoleDTO, role);
        roleService.saveOne(role);

        // 关联权限
        Set<RolePermissionRelation> collect = Arrays.stream(additionRoleDTO.getPermissionIds()).map(pid -> {
            RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
            rolePermissionRelation.setRoleId(role.getId());
            rolePermissionRelation.setPermissionId(pid);

            return rolePermissionRelation;
        }).collect(Collectors.toSet());
        rolePermissionRelationService.saveBatch(collect);

        return Result.success();
    }

    public Result additionToUser(AdditionRoleToUserDTO additionRoleToUserDTO) {
        // TODO 11:25
        return null;
    }
}

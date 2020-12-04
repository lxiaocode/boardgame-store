package com.lxiaocode.boardgame.auth.access.handler;

import com.lxiaocode.boardgame.auth.rbac.domain.IPermissionDetails;
import com.lxiaocode.boardgame.auth.rbac.service.OperationDetailsService;
import com.lxiaocode.boardgame.auth.rbac.service.impl.OperationDetailsServiceImpl;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 加载授权信息
 * @author lixiaofeng
 * @date 2020/12/3 上午11:12
 * @blog http://www.lxiaocode.com/
 */

public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private OperationDetailsService operationDetailsService = new OperationDetailsServiceImpl();
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // TODO 缓存
        // 获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 加载所有接口许可
        List<IPermissionDetails> IPermissionDetails = operationDetailsService.loadPermissionAndRoles();
        // 遍历所有许可，加载所需角色
        Set<String> set = new HashSet<>();
        for (IPermissionDetails permission : IPermissionDetails){
            if (antPathMatcher.match(permission.getUrl(), requestUrl)){
                permission.getRoles().parallelStream().forEach(IRole -> set.add(IRole.getName()));
            }
        }
        if (! set.isEmpty()){
            return SecurityConfig.createList(set.toArray(new String[0]));
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    public void setOperationDetailsService(OperationDetailsService operationDetailsService) {
        this.operationDetailsService = operationDetailsService;
    }
}

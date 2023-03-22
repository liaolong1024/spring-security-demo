package com.lot.ccsmsb.security;

import com.lot.ccsmsb.admin.service.ModuleService;
import com.lot.ccsmsb.entity.SysModules;
import com.lot.ccsmsb.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 该类通过请求URL匹配该URL所需要的 role
 * @author ll
 */
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ModuleService moduleService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestURI = ((FilterInvocation) object).getRequest().getRequestURI();
        List<SysModules> allModules = moduleService.getAllModules();
        for (SysModules module : allModules) {
            if (antPathMatcher.match(module.getModuleUrl(), requestURI)) {
                String[] roles = module.getSysRoles().stream().map(SysRole::getRoleNameEn).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

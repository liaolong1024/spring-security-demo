package com.lot.ccsmsb.security;

import com.lot.ccsmsb.admin.mapper.AdminMapper;
import com.lot.ccsmsb.admin.service.AdminService;
import com.lot.ccsmsb.entity.SysAdmin;
import com.lot.ccsmsb.entity.SysModules;
import com.lot.ccsmsb.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ll
 */
@Component
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String adminLoginName) throws UsernameNotFoundException {

        SysAdmin sysAdmin = adminService.selectByAdminName(adminLoginName);
        List<SysRole> roles = adminService.getRolesByAdminId(sysAdmin.getAdminId());
        sysAdmin.setSysRoles(roles);
        return new AdminDetails(sysAdmin);

    }

}

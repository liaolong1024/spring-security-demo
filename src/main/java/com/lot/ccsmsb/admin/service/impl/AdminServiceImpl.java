package com.lot.ccsmsb.admin.service.impl;

import com.lot.ccsmsb.admin.mapper.AdminMapper;
import com.lot.ccsmsb.admin.mapper.ModuleMapper;
import com.lot.ccsmsb.admin.service.AdminService;
import com.lot.ccsmsb.entity.SysAdmin;
import com.lot.ccsmsb.entity.SysModules;
import com.lot.ccsmsb.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ll
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public SysAdmin selectByAdminName(String adminLoginName) {

        SysAdmin sysAdmin = adminMapper.selectUserIdByAdminName(adminLoginName);

        if (sysAdmin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return sysAdmin;
    }

    public List<SysRole> getRolesByAdminId(Long adminId) {
        return adminMapper.selectByAdminName(adminId);
    }
}

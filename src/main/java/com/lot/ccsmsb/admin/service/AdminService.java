package com.lot.ccsmsb.admin.service;

import com.lot.ccsmsb.entity.SysAdmin;
import com.lot.ccsmsb.entity.SysModules;
import com.lot.ccsmsb.entity.SysRole;

import java.util.List;

/**
 * @author LiaoLong
 * @create 2023-03-20 23:14
 */

public interface AdminService {

    public SysAdmin selectByAdminName(String adminLoginName);

    public List<SysRole> getRolesByAdminId(Long adminId);

}

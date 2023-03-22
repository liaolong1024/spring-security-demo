package com.lot.ccsmsb.admin.mapper;

import com.lot.ccsmsb.entity.SysAdmin;
import com.lot.ccsmsb.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ll
 */

@Mapper
public interface AdminMapper {

    List<SysRole> selectByAdminName(Long adminId);

    SysAdmin selectUserIdByAdminName(String adminName);

}

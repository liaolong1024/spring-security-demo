package com.lot.ccsmsb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ll
 */

@Setter
@Getter
@TableName("sys_admin")
public class SysAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long adminId;

    private String adminLoginName;

    private String adminLoginPass;

    private String adminLoginMac;

    private String adminLoginIp;

    private String adminLoginBeginTime;

    private String adminLoginEndTime;

    private Integer adminStatus;

    private Date adminCreateDate;

    private String adminMobilePhone;

    private String adminEmail;

    private Long adminIsDefault;

    private Boolean hasAuthorizeRight;

    private Integer adminPwdState;

    private Long adminLoginFailCount;

    /** 管理员-角色： 多对多关系 */
    private List<SysRole> sysRoles;

    private List<SysAdminOperateLog> sysAdminOperateLogs;

}

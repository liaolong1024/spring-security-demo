package com.lot.ccsmsb.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author ll
 */
@Setter
@Getter
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private String roleNameEn;

    private String roleNameCn;

    private Integer roleType;

    private Integer roleStatus;

    private String roleRemark;

    private List<SysAdmin> sysAdmins;

    private List<SysModules> sysModules;
}

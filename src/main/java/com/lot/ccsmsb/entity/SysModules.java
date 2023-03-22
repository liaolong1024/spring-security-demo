package com.lot.ccsmsb.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author ll
 */
@Getter
@Setter
public class SysModules implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long moduleId;

    private String moduleNameEn;

    private String moduleNameCn;

    private String moduleUrl;

    private Long moduleFid;

    private String moduleOrder;

    private Long moduleType;

    private Integer visible;

    private List<SysRole> sysRoles;
}

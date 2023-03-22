package com.lot.ccsmsb.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ll
 */
@Getter
@Setter
public class SysAdminOperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long aoId;

    private Long aoAdminId;

    private String aoModelName;

    private Integer aoOperateType;

    private String aoOperateContent;

    private String aoValueBefore;

    private String aoValueAfter;

    private Date aoOperateTime;

    private String aoRemark;
}

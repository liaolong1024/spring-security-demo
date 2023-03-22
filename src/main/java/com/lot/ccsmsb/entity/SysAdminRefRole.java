package com.lot.ccsmsb.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ll
 */
@Setter
@Getter
public class SysAdminRefRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long arrId;

    private Long arrRoleId;

    private Long arrAdminId;
}

package com.lot.ccsmsb.admin.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ll
 */
@Setter
@Getter
public class AdminVo {

    private String username;

    private String password;

    private String verifyCode;

    private String token;
}

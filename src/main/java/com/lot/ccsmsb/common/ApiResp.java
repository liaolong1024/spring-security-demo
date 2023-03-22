package com.lot.ccsmsb.common;

import lombok.*;

import java.io.Serializable;

/**
 * @author ll
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResp implements Serializable {

    private String code;

    private String message;

    private Object data;

    public static ApiResp success(Object data) {
        return new ApiResp("0000", "success", data);
    }

}

package com.example.auth.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回枚举类
 */
public class ResponseCode {

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String dec;

    public ResponseCode() {
    }

    public ResponseCode(String code, String dec) {
        this.code = code;
        this.dec = dec;
    }
}

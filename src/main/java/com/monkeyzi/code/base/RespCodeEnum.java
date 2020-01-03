package com.monkeyzi.code.base;

public enum  RespCodeEnum {

    SUCCESS(0),
    ERROR(1);

    private Integer code;
    RespCodeEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}

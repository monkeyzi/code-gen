package com.monkeyzi.code.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private T       data;
    private Integer code;
    private String  msg;



    public boolean isSuccess(){
        return this.code.equals(RespCodeEnum.SUCCESS.getCode());
    }
    /**
     * 返回成功的方法
     */
    public static <T> Result<T> successd(T data, Integer code, String msg) {
        return new Result<>(data,code,msg);
    }

    public static <T> Result<T> ok(String msg) {
        return successd(null, RespCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return successd(data, RespCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> ok(T data) {
        return successd(data, RespCodeEnum.SUCCESS.getCode(), "");
    }

    /**
     * 返回失败的
     * @param data
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> failed(T data, Integer code, String msg) {
        return new Result<>(data, code, msg);
    }

    public static <T> Result<T> fail(String msg) {
        return failed(null, RespCodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> fail(T data, String msg) {
        return failed(data, RespCodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> fail(T data) {
        return failed(data, RespCodeEnum.ERROR.getCode(), "");
    }

    public static <T> Result<T> fail(Integer code,String msg) {
        return failed(null, code, msg);
    }

}

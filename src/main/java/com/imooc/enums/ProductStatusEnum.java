package com.imooc.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ProductStatusEnum implements  CodeEnum{
    UP(0, "在架"),DOWN(1, "下架");


    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }

    @Override
    public Integer getCode() {
        return code;
    }
}

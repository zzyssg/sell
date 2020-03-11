package com.imooc.VO;

import lombok.Data;
/**
 * 返回的最外层对象
 *
 * */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}

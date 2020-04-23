package com.imooc.enums;

public enum ResultEnum {
    SUCCESS(0,"成功"),

    PRARM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    CANCLE_FAILED(14,"取消订单失败"),
    UPDATE_FAILED(15,"更新订单失败"),
    CART_EMPTY(18,"购物车为空"),
    WE_CHAT_ERROR(20, "微信公众账号错误"),

    ORDER_FINISH_SUCCESS(22,"订单完结成功"),
    PRODUCT_STATUS_ERROR(23,"商品状态不正确"),

    PRODUCT_CATRGORY_NOT_EXIST(25,"商品类型不存在"),
    FORM_DATA_ERROR(26,"表单数据不正确"),

    LOGIN_FAILED(27, "登录失败"),
    LOGOUT_SUCCESS(28,"登出成功");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

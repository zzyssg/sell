package com.imooc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVO {

    @JsonProperty("name")
    private String productName;

    @JsonProperty("type")
    private Integer productType;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("descriptipn")
    private String productDescriotion;

    @JsonProperty("icon")
    private String productIcon;

}

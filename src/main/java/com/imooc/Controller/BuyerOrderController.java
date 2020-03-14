package com.imooc.Controller;

import com.imooc.VO.ResultVO;
import com.imooc.converter.OrderFORM2OrderDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    //创建订单

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建订单参数不正确 {}", orderForm);
            throw new SellException(ResultEnum.PRARM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());

        }
        OrderDTO orderDTO = OrderFORM2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("购车为空{}", ResultEnum.CART_EMPTY);
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createRes = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();

        map.put("orderId", createRes.getOrderId());
        return ResultVOUtil.success(map);

    }

    //订单列表

    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PRARM_ERROR);
        }

        PageRequest request = PageRequest.of(page, size);

        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        return ResultVOUtil.success(orderDTOPage.getContent());

    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId
    ) {
        //TODO
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDTO);

    }

    //取消订单
    @PostMapping("/cancle")
    public ResultVO<OrderDTO> cancle(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId
    ) {
        //TODO
        buyerService.canlceOrder(openid, orderId);
        return ResultVOUtil.success();
    }

}

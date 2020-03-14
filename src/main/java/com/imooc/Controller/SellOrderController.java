package com.imooc.Controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.lly835.bestpay.rest.type.Get;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {

    @Autowired
    private OrderService orderService;


    /**
     * @param page 第几页数据
     * @param size 每页有几行
     */

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);


    }

    @GetMapping("/cancle")
    public ModelAndView cancle(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.calcle(orderDTO);
        } catch (SellException e) {


            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            log.error("【卖家端取消订单】查询不到订单{}", e.getMessage());

            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS);
        map.put("url", "/sell/seller/order/list");

        return new ModelAndView("common/success1");

    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            log.error("【卖家端查询详情】查询不到订单{}", e.getMessage());

            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO", orderDTO);

        return new ModelAndView("order/detail", map);

    }
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            log.error("【卖家端完成订单】查询不到订单{}", e.getMessage());

            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success1");

    }


}

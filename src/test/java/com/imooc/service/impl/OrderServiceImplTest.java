package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.model.OrderDetail;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID = "110110";

    private final String ORDER_ID = "1543971928341467276";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("zlj");
        orderDTO.setBuyerAddress("hhh");
        orderDTO.setBuyerPhone("13876876768");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("12345678");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1234567");
        o2.setProductQuantity(100);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);

        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findById() {
        OrderDTO result = orderService.findById(ORDER_ID);
        log.info("【查询单个订单】result={}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() {
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, PageRequest.of(0,2));
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findById(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findById(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findById(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());

    }
}

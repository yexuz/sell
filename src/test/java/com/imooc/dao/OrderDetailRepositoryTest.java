package com.imooc.dao;

import com.imooc.model.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;


    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567890");
        orderDetail.setOrderId("111111");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(122));
        orderDetail.setProductQuantity(3);

        OrderDetail result = repository.save(orderDetail);
        assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("111111");
        assertNotEquals(0, orderDetailList.size());
    }
}

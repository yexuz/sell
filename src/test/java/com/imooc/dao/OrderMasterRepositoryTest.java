package com.imooc.dao;

import com.imooc.model.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110110";
    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("zlj");
        orderMaster.setBuyerPhone("13876876768");
        orderMaster.setBuyerAddress("aaaaa");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = repository.save(orderMaster);
        assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, PageRequest.of(0, 1));
        assertNotEquals(0, result.getTotalElements());
        System.out.println(result.getTotalElements());
    }
}

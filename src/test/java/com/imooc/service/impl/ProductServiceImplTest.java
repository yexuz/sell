package com.imooc.service.impl;

import com.imooc.enums.ProductStatusEnum;
import com.imooc.model.ProductInfo;
import com.imooc.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findById() {
        ProductInfo productInfo = productService.findById("123");
        Assert.assertNotNull("123", productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0, productInfoList);
    }

    @Test
    public void findAll() {
        Page<ProductInfo> productInfoPage = productService.findAll(PageRequest.of(0,2));
//        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的虾");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);


    }
}

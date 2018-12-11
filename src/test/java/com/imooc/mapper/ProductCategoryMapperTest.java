package com.imooc.mapper;

import com.imooc.model.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "haha");
        map.put("categoryType", 6);
        int result = productCategoryMapper.insertByMap(map);
        assertEquals(1 , result);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("hehe");
        productCategory.setCategoryType(7);
        int result = productCategoryMapper.insertByObject(productCategory);
        assertEquals(1 , result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = productCategoryMapper.findByCategoryType(6);
        assertNotNull(productCategory);
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> productCategory = productCategoryMapper.findByCategoryName("haha");
//        assertNotNull(productCategory);
        assertEquals(2, productCategory.size());
    }

}

package com.imooc.mapper;

import com.imooc.model.ProductCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name, category_type) values(#{categoryName,jdbcType=VARCHAR}, #{categoryType,jdbcType=INTEGER}) ")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name, category_type) values(#{categoryName,jdbcType=VARCHAR}, #{categoryType,jdbcType=INTEGER}) ")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType,jdbcType=INTEGER}")
    @Results({
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
    })
    ProductCategory findByCategoryType(Integer categoryType);


    @Select("select * from product_category where category_name = #{categoryName,jdbcType=VARCHAR}")
    @Results({
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
    })
    List<ProductCategory> findByCategoryName(String categoryName);
}

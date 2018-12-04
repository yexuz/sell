package com.imooc.service.impl;

import com.imooc.dao.OrderDetailRepository;
import com.imooc.dao.OrderMasterRepository;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.model.OrderDetail;
import com.imooc.model.OrderMaster;
import com.imooc.model.ProductInfo;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

//        List<CartDTO> cartDTOList = new ArrayList<>();
        //1. 查询商品（数量、价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findById(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算总价格
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity())
//            cartDTOList.add(cartDTO);
        }

        //3. 写入数据库
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);

        //4. 扣除库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findById(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}

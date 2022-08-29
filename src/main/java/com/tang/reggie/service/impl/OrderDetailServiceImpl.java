package com.tang.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.reggie.entity.OrderDetail;
import com.tang.reggie.mapper.OrderDetailMapper;
import com.tang.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;
 
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
 
}
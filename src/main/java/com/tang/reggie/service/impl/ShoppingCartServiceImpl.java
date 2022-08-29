package com.tang.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.reggie.entity.ShoppingCart;
import com.tang.reggie.mapper.ShoppingCartMapper;
import com.tang.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;
 
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
 
}
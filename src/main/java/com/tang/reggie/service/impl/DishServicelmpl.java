package com.tang.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.reggie.entity.Dish;
import com.tang.reggie.mapper.DishMapper;
import com.tang.reggie.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServicelmpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
package com.tang.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.reggie.entity.Setmeal;
import com.tang.reggie.mapper.SetmealMapper;
import com.tang.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServicelmpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
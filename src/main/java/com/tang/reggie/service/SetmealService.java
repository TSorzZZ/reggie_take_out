package com.tang.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.reggie.dto.SetmealDto;
import com.tang.reggie.entity.Dish;
import com.tang.reggie.entity.Setmeal;
import com.tang.reggie.entity.SetmealDish;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}
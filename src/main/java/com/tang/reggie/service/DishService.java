package com.tang.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.reggie.dto.DishDto;
import com.tang.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

    //保存菜品，插入菜品对应的口味，需要操作两张表
    public void saveWithFlavour(DishDto dishDto);

    //根据id查询菜品对应口味
    public DishDto getByIdWithFlavour(Long id);

    public void updateWithFlavour(DishDto dishDto);
}

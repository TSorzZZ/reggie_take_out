package com.tang.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}

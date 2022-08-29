package com.tang.reggie.dto;

import com.tang.reggie.entity.Setmeal;
import com.tang.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}

package com.tang.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.reggie.common.R;
import com.tang.reggie.entity.Employee;
import com.tang.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


// 处理员工请求的controller，接收前端输入信息并给予响应，将信息参数传给service处理
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        // 1-加密密码
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2-根据username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 3-没找到用户
        if(emp == null)return R.error("登录失败");

        // 4-比对密码 不一致也返回登录失败
        if(!emp.getPassword().equals(password))return R.error("登录失败");

        // 5-比对成功 看员工是否可用
        if(emp.getStatus() == 0)return R.error("账号失效");

        // 6- 登录成功
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);

    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.removeAttribute("employee");
        return R.success("退出成功");
    }

    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("新增员工的信息：{}",employee.toString());
        //设置初始密码，需要进行md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        /*
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //强转为long类型
        Long empID = (Long)request.getSession().getAttribute("employee");

        employee.setCreateUser(empID);
        employee.setUpdateUser(empID);
        */
        employeeService.save(employee);
        return R.success("添加员工成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        log.info("name{}",name);
        if(name != null){
            lambdaQueryWrapper.like(Employee::getName,name);
        }
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo);
        return R.success(pageInfo);

    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){

//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return R.success("更新成功");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){

        log.info("根据id查询员工信息。。。");
        Employee employee = employeeService.getById(id);
        if (employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到该员工信息");
    }

}

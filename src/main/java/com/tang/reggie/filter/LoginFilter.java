package com.tang.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.tang.reggie.common.BaseContext;
import com.tang.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 过滤器 用于判断用户登录状态，防止能够访问到一些数据库的关键数据
// filter是对客户端访问资源的过滤，符合条件放行，不符合条件不放行，并且可以对目 标资源访问前后进行逻辑处理
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginFilter implements Filter {
    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = request.getRequestURI();    //1-获得请求的uri
        //log.info("请求uri"+requestUri);
        String[] urls = new String[]{                   //2-定义不需要请求的路径
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };
        //3-判断本次请求是否需要处理
        boolean check = check(urls,requestUri);
        if(check){
            filterChain.doFilter(request,response);
            return;
        }
        //4-判断后端登陆状态
        if(request.getSession().getAttribute("employee") != null){
            log.info("已登陆{}",request.getSession().getAttribute("employee"));
            Long empId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        //4-判断前端登陆状态
        if(request.getSession().getAttribute("user") != null){
            log.info("已登陆{}",request.getSession().getAttribute("user"));
            Long userId = (Long)request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }

        log.info("未登录");
        //5-如果未登录返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    public boolean check(String[] urls,String requestUri){
        for(String url:urls){
            if(PATH_MATCHER.match(url,requestUri))return true;
        }
        return false;
    }

}

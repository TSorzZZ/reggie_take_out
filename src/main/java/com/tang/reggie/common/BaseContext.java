package com.tang.reggie.common;

//获取当前线程的登录id  客户端发送的每次http请求，对应的在服务端都会分配一个新的线程来处理，
// 在处理过程中涉及到下面类中的方法都属于相同的一个线程:  filter   controller     objecthandler
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
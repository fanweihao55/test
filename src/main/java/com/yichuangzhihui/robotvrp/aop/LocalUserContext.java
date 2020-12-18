package com.yichuangzhihui.robotvrp.aop;


import com.yichuangzhihui.robotvrp.pojo.Users;

/**
 * 线程存放
 */
public class LocalUserContext{

    private static ThreadLocal<Users> threadLocal = new ThreadLocal<>();

    public static void set(Users user) {
        LocalUserContext.threadLocal.set(user);
    }

    public static void clear() {
        LocalUserContext.threadLocal.remove();
    }

    public static Users getUser() {

        return LocalUserContext.threadLocal.get();
    }
}

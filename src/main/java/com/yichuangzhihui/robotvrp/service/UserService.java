package com.yichuangzhihui.robotvrp.service;

import com.yichuangzhihui.robotvrp.pojo.Users;

/**
 * @Date :  2020/12/10 14:19
 * @Author : fanweihao
 * @Version: V1.0
 */
public interface UserService {
    //用户登录
    Users user_log(Users users);

    void addUser(Users users);

    void updateUser(Users users);

    void deleteUser(long id);
}

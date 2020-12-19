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
    //添加用户
    void addUser(Users users);
    //修改用户
    void updateUser(Users users);
    //根据id删除用户
    void deleteUser(long id);
    //根据用户id查询单个用户
    Users selectUserOne(long id);
}

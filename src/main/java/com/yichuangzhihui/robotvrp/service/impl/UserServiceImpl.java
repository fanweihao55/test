package com.yichuangzhihui.robotvrp.service.impl;


import com.yichuangzhihui.robotvrp.mapper.UsersMapper;
import com.yichuangzhihui.robotvrp.pojo.Users;
import com.yichuangzhihui.robotvrp.service.UserService;
import com.yichuangzhihui.robotvrp.util.Access;
import com.yichuangzhihui.robotvrp.util.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @Date :  2020/12/10 14:19
 * @Author : fanweihao
 * @Version: V1.0
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

     @Resource
     private UsersMapper userMapper;

    /**
     * 用户登录
     * @param users
     * @return
     */
    @Override
    public Users user_log(Users users) {
        log.info("userLogInData"+users.toString());
        Access.isNull(users,"请填写账号密码");
        Access.isNull(users.getPassword() , "密码未填写");
        Access.isNull(users.getUsername(), "用户名未填写");

        Users users1 = userMapper.user_log(users.getUsername());
        if (users1==null){
            throw new ServiceException(202,"用户不存在");
        }
        if (!users1.getPassword().equals(users.getPassword())){
            throw new ServiceException(202,"密码错误");
        }

        return users1;
    }

    /**
     * 添加用户
     * @param users
     */
    @Override
    @Transactional
    public void addUser(Users users) {
        log.info("userLogInData"+users.toString());
        Access.isNull(users.getPassword() , "密码未填写");
        Access.isNull(users.getUsername(), "账号未填写");

        Users users1 = userMapper.user_log(users.getUsername());

        if (users1!=null){
            throw new ServiceException(201,"用户名重复");
        }

        users.setCreateTime(new Date());
        users.setIsdelete("false");

        userMapper.insertSelective(users);
    }

    @Transactional
    @Override
    public void updateUser(Users users) {
        Access.isNull(users.getUsername(), "用户名未填写");

        Users users1 = userMapper.user_log(users.getUsername());

        if (users1!=null&&users1.getId()!=users.getId()){
            throw new ServiceException(201,"用户名重复");
        }
        userMapper.updateByPrimaryKeySelective(users);

    }
    @Transactional
    @Override
    public void deleteUser(long id) {
        userMapper.deleteByPrimaryKey(id);
    }


}

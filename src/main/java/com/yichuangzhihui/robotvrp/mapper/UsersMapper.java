package com.yichuangzhihui.robotvrp.mapper;

import com.yichuangzhihui.robotvrp.pojo.Users;


/**
 * @Date :  2020/12/15 14:24
 * @Author : fanweihao
 * @Version:  V1.0
 */
public interface UsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users user_log(String username);
}

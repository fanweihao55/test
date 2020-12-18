package com.yichuangzhihui.robotvrp.service;

import com.yichuangzhihui.robotvrp.pojo.Uar;

import java.util.List;

/**
 * @Date :  2020/12/11 14:17
 * @Author : fanweihao
 * @Version: V1.0
 */
public interface UARService {
    //新增无人机
    void addUAR(Uar uar);
    //修改无人机
    void updateUAR(Uar uar);
    //查询全部无人机
    List<Uar> selectAllUAR();
    //查询单个无人机
    Uar selectOneUAR(long uarid);
    //删除无人机
    void deleteUAR(long uarid);
}

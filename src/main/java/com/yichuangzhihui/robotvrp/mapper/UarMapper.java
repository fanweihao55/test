package com.yichuangzhihui.robotvrp.mapper;

import com.yichuangzhihui.robotvrp.pojo.Uar;

import java.util.List;


/**
 * @Date :  2020/12/14 14:21
 * @Author : fanweihao
 * @Version:  V1.0
 */
public interface UarMapper {
    int deleteByPrimaryKey(Long uarId);

    int insert(Uar record);

    int insertSelective(Uar record);

    Uar selectByPrimaryKey(Long uarId);

    int updateByPrimaryKeySelective(Uar record);

    int updateByPrimaryKey(Uar record);
    //根据无人机名称查询无人机
    Uar getUAR(String name);
    //查询全部无人机
    List<Uar> selectAllUAR();

    void delteUarIdAndTarmacID(long uarid);
    //根据停机坪id查询关联的无人机集合
    List<Uar> selectTarmacIdByUarList(Long tarmacId);
}

package com.yichuangzhihui.robotvrp.mapper;

import com.yichuangzhihui.robotvrp.pojo.Tarmac;

import java.util.List;
import java.util.Map;

public interface TarmacMapper {
    int deleteByPrimaryKey(Long tarmacId);

    int insert(Tarmac record);

    int insertSelective(Tarmac record);

    Tarmac selectByPrimaryKey(Long tarmacId);

    int updateByPrimaryKeySelective(Tarmac record);

    int updateByPrimaryKey(Tarmac record);

    List<Tarmac> selectAllTarmac();

    Tarmac selectTarmacLat(String tarmacName);
    //添加停机坪与任务点关联关系
    void setTarmacAndTaskPoint(Map<String, Long> map);
    //删除关联关系
    void deleteTarmacIdAndTaskPointId(Long tarmacId);
    //删除关联关系
    void deleteTarmacIdAndUarId(Long tarmacId);
    //添加停机坪与无人机关联关系
    void setTarmacAndUar(Map<String, Long> map);
    //根据停机坪id查询关联的任务点id
    List<Long> findPointIdsByTarmacIdId(long tarmacId);
    //根据停机坪id查询关联的无人机id
    List<Long> findUarIdsByTarmacIdId(long tarmacId);

}

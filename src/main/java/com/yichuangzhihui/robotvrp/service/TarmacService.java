package com.yichuangzhihui.robotvrp.service;

import com.yichuangzhihui.robotvrp.dto.TarmacDto;
import com.yichuangzhihui.robotvrp.pojo.Tarmac;

import java.util.List;

/**
 * @Date :  2020/12/8 9:27
 * @Author : fanweihao
 * @Version: V1.0
 */
public interface TarmacService {
    //新增停机坪
    void addTarmac(TarmacDto tarmacDto);
    //修改停机坪
    void updateTarmac(TarmacDto tarmacDto);
    //删除停机坪
    void deleteTarmac(long tarmacId);
    //查询所有停机坪
    List<Tarmac> selectAllTarmac();
    //停机坪更新任务点
    void addTarmacIdAndTaskPointId(TarmacDto tarmacDto);
    //停机坪更新无人机
    void addTarmacIdAndUarId(TarmacDto tarmacDto);
    //查询单个停机坪
    Tarmac selectOneTarmac(long tarmacId);
    //根据停机坪id查询关联的任务点id
    List<Long> findPointIdsByTarmacId(long tarmacId);
    //根据停机坪id查询关联的无人机id
    List<Long> findUarIdsByTarmacId(long tarmacId);
}

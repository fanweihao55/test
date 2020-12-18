package com.yichuangzhihui.robotvrp.mapper;

import com.yichuangzhihui.robotvrp.pojo.TaskPoint;

import java.util.List;

public interface TaskPointMapper {
    int deleteByPrimaryKey(Long taskPointId);

    int insert(TaskPoint record);

    int insertSelective(TaskPoint record);

    TaskPoint selectByPrimaryKey(Long taskPointId);

    int updateByPrimaryKeySelective(TaskPoint record);

    int updateByPrimaryKey(TaskPoint record);

    List<TaskPoint> selectAllTaskPoint();

    TaskPoint selectTaskPoint(String taskPointName);

    void delteByTaskPointIdAndTarmacID(long taskPointId);
    //根据停机坪id查询任务点集合信息
    List<TaskPoint> selectTarmacIdByPointList(Long tarmacId);
}

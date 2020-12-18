package com.yichuangzhihui.robotvrp.service;

import com.yichuangzhihui.robotvrp.pojo.TaskPoint;

import java.util.List;

/**
 * @Date :  2020/12/8 9:28
 * @Author : fanweihao
 * @Version: V1.0
 */
public interface TaskPointService {
    //新增任务点
    void addTaskPoint(TaskPoint taskPoint);
    //修改任务点
    void updateTaskPoint(TaskPoint taskPoint);
    //删除任务点
    void deleteTaskPoint(long taskPointId);
    //查询所有任务点
    List<TaskPoint> selectAllTaskPoint();
    //查询单个任务点
    TaskPoint selectOneTaskPoint(long taskPointId);
}

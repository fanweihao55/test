package com.yichuangzhihui.robotvrp.service.impl;

import com.yichuangzhihui.robotvrp.mapper.TaskPointMapper;
import com.yichuangzhihui.robotvrp.pojo.TaskPoint;
import com.yichuangzhihui.robotvrp.service.TaskPointService;
import com.yichuangzhihui.robotvrp.util.Access;
import com.yichuangzhihui.robotvrp.util.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Date :  2020/12/8 9:29
 * @Author : fanweihao
 * @Version: V1.0
 */
@Log4j2
@Service
public class TaskPointServiceImpl implements TaskPointService {
    private Logger logger = LogManager.getLogger(TaskPointServiceImpl.class.getName());

    @Resource
    private TaskPointMapper taskPointMapper;
    /**
     * 新增任务点
     * @param taskPoint
     */
    @Override
    @Transactional
    public void addTaskPoint(TaskPoint taskPoint) {
        logger.info("addTaskPoint begin tarmac::"+taskPoint.toString());
        Access.isNull(taskPoint.getTaskPointLat(),"纬度不能为空");
        Access.isNull(taskPoint.getTaskPointLng(),"经度不能为空");

        TaskPoint taskPoint1=taskPointMapper.selectTaskPoint(taskPoint.getTaskPointName());

        if (taskPoint1!=null){
            throw new ServiceException(201,"任务点名称重复");
        }

        taskPoint.setCreateDate(new Date());
        taskPoint.setIsDelete(false);
        taskPointMapper.insertSelective(taskPoint);
    }

    /**
     * 修改任务点
     * @param taskPoint
     */
    @Override
    @Transactional
    public void updateTaskPoint(TaskPoint taskPoint) {
        logger.info("updateTaskPoint begin taskPoint = ::"+taskPoint.toString());
        //判断任务点名称是否重复
        if (taskPoint.getTaskPointName()!=null){
            TaskPoint taskPoint1=taskPointMapper.selectTaskPoint(taskPoint.getTaskPointName());

            if (taskPoint1!=null&&taskPoint1.getTaskPointId()!=taskPoint.getTaskPointId()){
                throw new ServiceException(201,"任务点名称重复");
            }
        }
        taskPointMapper.updateByPrimaryKeySelective(taskPoint);
    }

    /**
     * 删除任务点
     * @param taskPointId
     */
    @Override
    @Transactional
    public void deleteTaskPoint(long taskPointId) {
        logger.info("deleteTaskPoint begin taskPointId = ::"+taskPointId);
/*
        //根据id查询任务点
        TaskPoint taskPoint = taskPointMapper.selectByPrimaryKey(taskPointId);

        if (taskPoint==null){
            throw new ServiceException(201,"任务点不存在");
        }
        if (taskPoint.getIsDelete()==true){
            throw new ServiceException(201,"任务点已被删除");
        }
        taskPoint.setIsDelete(true);*/
        //先删除任务点的关联关系
        taskPointMapper.delteByTaskPointIdAndTarmacID(taskPointId);
        //删除任务点
        taskPointMapper.deleteByPrimaryKey(taskPointId);

    }

    /**
     * 查询所有任务点
     * @return
     */
    @Override
    public List<TaskPoint> selectAllTaskPoint() {
        logger.info("selectAllTaskPoint begin");

        List<TaskPoint> list=taskPointMapper.selectAllTaskPoint();

        return list;
    }

    /**
     * 查询单个任务点
     * @param taskPointId
     * @return
     */
    @Override
    public TaskPoint selectOneTaskPoint(long taskPointId) {
        logger.info("selectOneTaskPoint begin  taskPointId:::"+taskPointId);
        return taskPointMapper.selectByPrimaryKey(taskPointId);
    }


}

package com.yichuangzhihui.robotvrp.controller;

import com.yichuangzhihui.robotvrp.pojo.TaskPoint;
import com.yichuangzhihui.robotvrp.service.TaskPointService;
import com.yichuangzhihui.robotvrp.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date :  2020/12/8 9:28
 * @Author : fanweihao
 * 任务点
 */
@RestController
@RequestMapping("/taskPoint/")
@Api("任务点")
public class
    TaskPointController {

    @Resource
    private TaskPointService taskPointService;


    /**
     * 新增任务点
     * @param taskPoint
     * @return
     */
    @ApiOperation("新增任务点")
    @PostMapping("addTaskPoint")
    public Result addTaskPoint(@RequestBody TaskPoint taskPoint){
        taskPointService.addTaskPoint(taskPoint);
        return new Result("新增任务点成功","0");
    }



    /**
     * 修改任务点
     * @param taskPoint
     * @return
     */
    @ApiOperation("修改任务点")
    @PostMapping("updateTaskPoint")
    public Result updateTaskPoint(@RequestBody TaskPoint taskPoint){
        taskPointService.updateTaskPoint(taskPoint);
        return new Result("修改任务点成功 ","0");
    }

    /**
     * 删除任务点
     * @param taskPointId
     * @return
     */
    @ApiOperation("删除任务点")
    @GetMapping("deleteTaskPoint/{taskPointId}")
    public Result deleteTarmac(@PathVariable("taskPointId") long taskPointId){
        taskPointService.deleteTaskPoint(taskPointId);
        return new Result("删除任务点成功 ","0");
    }

    /**
     * 查询单个任务点
     * @param taskPointId
     * @return
     */
    @ApiOperation("查询单个任务点")
    @GetMapping("selectOneTaskPoint/{taskPointId}")
    public Result selectOneTaskPoint(@PathVariable("taskPointId") long taskPointId){
        TaskPoint taskPoint=taskPointService.selectOneTaskPoint(taskPointId);
        return new Result(taskPoint,"查询单个任务点成功 ","0");
    }

    /**
     * 查询所有任务点
     */
    @ApiOperation("查询所有任务点")
    @GetMapping("selectAllTaskPoint")
    public Result selectAllTaskPoint(){
        List<TaskPoint> list= taskPointService.selectAllTaskPoint();
        return new Result(list,"查询所有任务点成功","0");
    }

}

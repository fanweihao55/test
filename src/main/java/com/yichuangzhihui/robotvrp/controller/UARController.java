package com.yichuangzhihui.robotvrp.controller;

import com.yichuangzhihui.robotvrp.pojo.Uar;
import com.yichuangzhihui.robotvrp.service.UARService;
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
 * @Date :  2020/12/11 14:15
 * @Author : fanweihao
 * @Version: V1.0
 * 无人机
 */
@RestController
@RequestMapping("/uar/")
@Api("无人机")
public class UARController {

    @Resource
    private UARService uarService;
    /**
     * 新增无人机
     * @param uar
     * @return
     */
    @PostMapping("addUAR")
    @ApiOperation("新增无人机")
    public Result addUAR(@RequestBody Uar uar){
        uarService.addUAR(uar);
        return new Result("新增无人机成功","0");
    }

    /**
     * 修改无人机
     * @param uar
     * @return
     */
    @PostMapping("updateUAR")
    @ApiOperation("修改无人机")
    public Result updateUAR(@RequestBody Uar uar){
        uarService.updateUAR(uar);
        return new Result("修改无人机成功","0");
    }

    /**
     * 查询全部无人机
     * @return
     */
    @GetMapping("selectAllUAR")
    @ApiOperation("查询全部无人机")
    public Result selectAllUAR(){
        List<Uar> list= uarService.selectAllUAR();
        return new Result(list,"修改无人机成功","0");
    }

    /**
     * 查询单个无人机
     * @return
     */
    @ApiOperation("查询单个无人机")
    @GetMapping("selectOneUAR/{uarid}")
    public Result selectOneUAR(@PathVariable("uarid") long uarid){
        Uar list= uarService.selectOneUAR(uarid);
        return new Result(list,"查询单个无人机成功","0");
    }

    /**
     * 删除无人机
     * @param uarid
     * @return
     */
    @ApiOperation("删除无人机")
    @GetMapping("deleteUAR/{uarid}")
    public Result deleteUAR(@PathVariable("uarid") long uarid){
        uarService.deleteUAR(uarid);
        return new Result("删除无人机成功","0");
    }
}

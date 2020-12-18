package com.yichuangzhihui.robotvrp.controller;

import com.yichuangzhihui.robotvrp.dto.SolutionDto;
import com.yichuangzhihui.robotvrp.pojo.Solution;
import com.yichuangzhihui.robotvrp.service.SolutionService;
import com.yichuangzhihui.robotvrp.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
 * @Date :  2020/12/11 13:37
 * @Author : fanweihao
 * @Version: V1.0
 * 无人机路径解决方案
 */
@RestController
@RequestMapping("/solution/")
@Api("无人机路径解决方案")
public class SolutionController {

    @Resource
    private SolutionService solutionService;

    /**
     * 无人机路径解决第一种
     *  根据前端传来初始停机坪位置
     * @param solutionDto
     * @return
     */
    @ApiOperation("无人机路径解决")
    @PostMapping("pathSoutionOne")
    public Result pathSoutionOne(@RequestBody SolutionDto solutionDto){
        List<String> list=solutionService.pathSoutionOne(solutionDto);
        return new Result(list,"最优路径","0");
    }

    /**
     * 根据停机坪id查询出最优路径
     * @param tarmacId
     * @return
     */
    @ApiModelProperty("根据停机坪id查询出最优路径")
    @GetMapping("findPathSoutionByTarmacId/{tarmacId}")
    public Result findPathSoutionByTarmacId(@PathVariable("tarmacId") long tarmacId){
        Solution solution = solutionService.findPathSoutionByTarmacId(tarmacId);
        if (solution!=null){
            return new Result(solution,"查询最优路径完成","0");
        }else {
            return new Result("此停机坪没有最优路径","0");
        }
    }

    /**
     * 查询全部的最优路径
     * @return
     */
    @ApiOperation("查询全部的最优路径")
    @GetMapping("selectAllPath")
    public Result selectAllPath(){
        List<Solution> solutionList = solutionService.selectAllPath();
        return new Result(solutionList,"查询全部最优路径完成","0");

    }




}

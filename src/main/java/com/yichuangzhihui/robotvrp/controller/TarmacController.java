package com.yichuangzhihui.robotvrp.controller;

import com.yichuangzhihui.robotvrp.aop.AuthCheck;
import com.yichuangzhihui.robotvrp.dto.TarmacDto;
import com.yichuangzhihui.robotvrp.pojo.Tarmac;
import com.yichuangzhihui.robotvrp.service.TarmacService;
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
 * @Date :  2020/12/8 9:25
 * @Author : fanweihao
 * 停机坪
 */
@RestController
@RequestMapping("/tarmac/")
@Api("停机坪")
public class TarmacController {

    @Resource
    private TarmacService tarmacService;

    /**
     * 新增停机坪
     * @param tarmacDto
     * @return
     */
    @PostMapping("addTarmac")
    @ApiOperation("新增停机坪")
    public Result addTarmac(@RequestBody TarmacDto tarmacDto){
        tarmacService.addTarmac(tarmacDto);
        return new Result("新增停机坪成功","0");
    }

    /**
     * 修改停机坪
     * @param tarmacDto
     * @return
     */
    @PostMapping("updateTarmac")
    @ApiOperation("修改停机坪")
    public Result updateTarmac(@RequestBody TarmacDto tarmacDto){
        tarmacService.updateTarmac(tarmacDto);
        return new Result("修改停机坪成功 ","0");
    }

    /**
     * 删除停机坪
     * @param tarmacId
     * @return
     */
    @ApiOperation("删除停机坪")
    @GetMapping("deleteTarmac/{tarmacId}")
    public Result deleteTarmac(@PathVariable("tarmacId") long tarmacId){
        tarmacService.deleteTarmac(tarmacId);
        return new Result("删除停机坪成功 ","0");
    }

    /**
     * 查询所有停机坪
     * @return
     */
    @AuthCheck
    @GetMapping("selectAllTarmac")
    @ApiOperation("查询所有停机坪")
    public Result selectAllTarmac(){
        List<Tarmac> list = tarmacService.selectAllTarmac();
        return new Result(list,"查询所有停机坪成功","0");
    }

    /**
     * 查询单个停机坪
     * @param tarmacId
     * @return
     */
    @ApiOperation("查询单个停机坪")
    @GetMapping("selectOneTarmac/{tarmacId}")
    public Result selectOneTarmac(@PathVariable("tarmacId") long tarmacId){
        Tarmac tarmac = tarmacService.selectOneTarmac(tarmacId);
        return new Result(tarmac,"查询停机坪成功","0");
    }

    /**
     * 根据停机坪id查询关联的任务点id
     * @param tarmacId
     * @return
     */
    @ApiOperation("根据停机坪id查询关联的任务点id")
    @GetMapping("findPointIdsByTarmacId/{tarmacId}")
    public Result findPointIdIdsByTarmacId(@PathVariable("tarmacId") long tarmacId){
        List<Long> PointIdIds = tarmacService.findPointIdsByTarmacId(tarmacId);
        return new Result(PointIdIds,"查询关联任务点id完成","0");
    }

    /**
     * 根据停机坪id查询关联的无人机id
     * @param tarmacId
     * @return
     */
    @ApiOperation("根据停机坪id查询关联的无人机id")
    @GetMapping("findUarIdsByTarmacId/{tarmacId}")
    public Result findUarIdsByTarmacId(@PathVariable("tarmacId") long tarmacId){
        List<Long> uarIds = tarmacService.findUarIdsByTarmacId(tarmacId);
        return new Result(uarIds,"查询关联无人机id完成","0");
    }


    /**
     * 停机坪更新任务点
     * @param tarmacDto
     * @return
     */
    @PostMapping("addTarmacIdAndTaskPointId")
    public Result addTarmacIdAndTaskPointId(@RequestBody TarmacDto tarmacDto){

        tarmacService.addTarmacIdAndTaskPointId(tarmacDto);

        return new Result("停机坪任更新任务点完成","0");
    }

    /**
     * 停机坪更新无人机
     * @param tarmacDto
     * @return
     */
    @PostMapping("addTarmacIdAndUarId")
    public Result addTarmacIdAndUarId(@RequestBody TarmacDto tarmacDto){
        tarmacService.addTarmacIdAndUarId(tarmacDto);
        return new Result("停机坪更新无人机完成","0");
    }





}

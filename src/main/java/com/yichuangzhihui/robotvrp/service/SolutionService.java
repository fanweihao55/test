package com.yichuangzhihui.robotvrp.service;

import com.yichuangzhihui.robotvrp.dto.SolutionDto;
import com.yichuangzhihui.robotvrp.pojo.Solution;

import java.util.List;

/**
 * @Date :  2020/12/11 13:45
 * @Author : fanweihao
 * @Version: V1.0
 */
public interface SolutionService {
    //无人机路径解决第一种
    String pathSoutionOne(SolutionDto solutionDto);
    //根据停机坪id查询关联的无人机id
    Solution findPathSoutionByTarmacId(long tarmacId);
    //查询全部的最优路径
    List<Solution> selectAllPath();

}

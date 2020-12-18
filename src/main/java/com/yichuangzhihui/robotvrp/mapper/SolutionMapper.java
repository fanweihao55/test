package com.yichuangzhihui.robotvrp.mapper;

import com.yichuangzhihui.robotvrp.pojo.Solution;

import java.util.List;


/**
 * @Date :  2020/12/14 16:52
 * @Author : fanweihao
 * @Version:  V1.0
 */
public interface SolutionMapper {
    int deleteByPrimaryKey(Long solutionId);

    int insert(Solution record);

    int insertSelective(Solution record);

    Solution selectByPrimaryKey(Long solutionId);

    int updateByPrimaryKeySelective(Solution record);

    int updateByPrimaryKey(Solution record);
    //根据停机坪id查询路径方案
    Solution selectTarmac(Long tarmacId);

    Solution findPathSoutionByTarmacId(long tarmacId);

    List<Solution> selectAllPath();

}

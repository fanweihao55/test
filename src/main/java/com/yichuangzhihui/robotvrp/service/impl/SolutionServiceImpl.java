package com.yichuangzhihui.robotvrp.service.impl;


import com.yichuangzhihui.robotvrp.VRP.Node;
import com.yichuangzhihui.robotvrp.VRP.VRP;
import com.yichuangzhihui.robotvrp.dto.SolutionDto;
import com.yichuangzhihui.robotvrp.mapper.SolutionMapper;
import com.yichuangzhihui.robotvrp.mapper.TarmacMapper;
import com.yichuangzhihui.robotvrp.mapper.TaskPointMapper;
import com.yichuangzhihui.robotvrp.mapper.UarMapper;
import com.yichuangzhihui.robotvrp.pojo.Solution;
import com.yichuangzhihui.robotvrp.pojo.Tarmac;
import com.yichuangzhihui.robotvrp.pojo.TaskPoint;
import com.yichuangzhihui.robotvrp.pojo.Uar;
import com.yichuangzhihui.robotvrp.service.SolutionService;
import com.yichuangzhihui.robotvrp.util.Access;
import com.yichuangzhihui.robotvrp.util.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Date :  2020/12/11 13:45
 * @Author : fanweihao
 * @Version: V1.0
 */
@Service
@Log4j2
public class
SolutionServiceImpl implements SolutionService {
    private Logger logger = LogManager.getLogger(SolutionServiceImpl.class.getName());

    //停机坪
    @Resource
    private TarmacMapper tarmacMapper;
    //无人机
    @Resource
    private UarMapper uarMapper;
    //任务点
    @Resource
    private TaskPointMapper taskPointMapper;
    //方案
    @Resource
    private SolutionMapper solutionMapper;

    //无人机路径解决方案
    @Override
    @Transactional
    public List<String> pathSoutionOne(SolutionDto solutionDto) {
        Random ran = new Random(151190);
        //判断停机坪id是否存在
        Access.isNull(solutionDto.getTarmacId(),"停机坪id不能为空");
        logger.info("pathSoutionOne 停机坪id:"+solutionDto.getTarmacId());
        //根据停机坪id查询出关联的任务点信息
        List<TaskPoint> taskPointList=taskPointMapper.selectTarmacIdByPointList(solutionDto.getTarmacId());
        //创建节点数组 长度为任务点集合长度+1
        Node[] nodes=new Node[taskPointList.size()+1];
        //将停机坪关联的任务点信息打印到日志中
        logger.info("停机坪关联的任务点信息长度 taskPointList:"+taskPointList.size());
        //根据停机坪id查询出关联的无人机信息
        List<Uar> uarList=uarMapper.selectTarmacIdByUarList(solutionDto.getTarmacId());
        //判断停机坪是否有无人机存在 没有无人机则抛异常
        if (uarList.size()==0){
            throw new ServiceException(202,"停机坪没有关联的无人机信息");
         }
        //将无人机数量存入到日志中
        logger.info("停机坪关联的无人机信息集合数量 uarList:"+uarList.size());
        //根据停机坪id查询停机坪信息
        Tarmac tarmac = tarmacMapper.selectByPrimaryKey(solutionDto.getTarmacId());
        //将停机坪信息打印到日志中
        logger.info("停机坪信息 tarmac:"+tarmac.toString());
        //将停机坪xy轴传入节点第一位
        Node depot=new Node(tarmac.getTarmacLng().doubleValue(),tarmac.getTarmacLat().doubleValue());
        //将停机坪装载到任务点上
        nodes[0]=depot;
        //创建一个新的节点类 将节点类添加到集合第一位
        taskPointList.add(0,new TaskPoint());
        //从集合索引1开始将节点id,节点x轴,y轴传入到nodes节点数组中
        for (int i = 1; i < nodes.length; i++) {
            nodes[i]=new Node(taskPointList.get(i).getTaskPointId().intValue()
                    ,taskPointList.get(i).getTaskPointLng().doubleValue()
                    ,taskPointList.get(i).getTaskPointLat().doubleValue()
                    ,4+ran.nextInt(7));
        }
        //日志打印任务点节点数组
        logger.info("任务点节点数组长度  nodes"+nodes.length);
        //调用算法 返回结果
        VRP vrp=new VRP();

        List<String> list = vrp.VrpSolution(taskPointList.size()-1, 1, nodes);

        if (list.size()==0){
            throw new ServiceException(202,"没有适合的无人机路径方案");
        }
        //取出集合第一个
        String path=list.get(0);
        //判断出最短路径
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).length()<path.length()&&list.get(i).length()>0){
                path=list.get(i);
            }
        }
        //将最优的路径存入日志
        logger.info("最优路径 path: "+path);
        //判断得到最优的路径 将最优路径保存到数据库
        //根据停机坪id查询路径方案
        Solution s=solutionMapper.selectTarmac(solutionDto.getTarmacId());
        //创建新的解决方案类
        Solution solution=new Solution();
        //创建时间
        solution.setCreateDate(new Date());
        //修改时间
        solution.setUpdateDate(new Date());
        //是否删除
        solution.setIsDelete(false);
        //停机坪id
        solution.setTarmacId(tarmac.getTarmacId());
        //停机坪名称
        solution.setTarmacName(tarmac.getTarmacName());
        //最优路径
        solution.setOptimalPath(path);
        //先判断数据库是否有此路径的方案 如果有则修改 没有直接添加
        if (s==null){
            //直接添加
            solutionMapper.insertSelective(solution);
        }else {
            solution.setSolutionId(s.getSolutionId());
            solution.setCreateDate(s.getCreateDate());
            //修改
            solutionMapper.updateByPrimaryKeySelective(solution);
        }
        //将最优路径切割
        List<String> stringList=new ArrayList<>();
        String[] vehicles = path.split("Vehicle");
        for (String vehicle : vehicles) {
            if (vehicle.length()>0){
                stringList.add(vehicle);
            }
        }
        //返回最终结果
        return stringList;

    }

    /**
     * 根据停机坪id查询关联的无人机id
     * @param tarmacId
     * @return
     */
    @Override
    public Solution findPathSoutionByTarmacId(long tarmacId) {
        return solutionMapper.findPathSoutionByTarmacId(tarmacId);
    }

    /**
     * 查询全部的最优路径
     * @return
     */
    @Override
    public List<Solution> selectAllPath() {
        return solutionMapper.selectAllPath();
    }
}
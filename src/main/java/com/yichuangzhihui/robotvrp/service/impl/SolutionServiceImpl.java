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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Date :  2020/12/11 13:45
 * @Author : fanweihao
 * @Version: V1.0
 */
@Service
@Log4j2
public class SolutionServiceImpl implements SolutionService {
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
    public String pathSoutionOne(SolutionDto solutionDto) {
        Random ran = new Random(151190);
        //判断停机坪id是否存在
        Access.isNull(solutionDto.getTarmacId(),"停机坪id不能为空");
        logger.info("pathSoutionOne 停机坪id:"+solutionDto.getTarmacId());
        //根据停机坪id查询出关联的任务点信息
        List<TaskPoint> taskPointList = taskPointMapper.selectTarmacIdByPointList(solutionDto.getTarmacId());
        //将停机坪关联的任务点信息打印到日志中
        logger.info("停机坪关联的任务点信息长度 taskPointList:"+taskPointList.size());
        //判断有没有关联的任务点信息 没有则抛出异常
        if (taskPointList.size() == 0){
            throw new ServiceException(202,"停机坪没有关联的任务点信息");
        }
        //创建节点数组 长度为任务点集合长度+1
        Node[] nodes=new Node[taskPointList.size()+1];
        //根据停机坪id查询出关联的无人机信息
        List<Uar> uarList=uarMapper.selectTarmacIdByUarList(solutionDto.getTarmacId());
        //判断停机坪是否有无人机存在 没有无人机则抛异常
        if (uarList.size() == 0){
            throw new ServiceException(202,"停机坪没有关联的无人机信息");
        }
        //将无人机数量存入到日志中
        logger.info("停机坪关联的无人机信息集合数量 uarList:"+uarList.size());
        //根据停机坪id查询停机坪信息
        Tarmac tarmac = tarmacMapper.selectByPrimaryKey(solutionDto.getTarmacId());
        //将停机坪信息打印到日志中
        logger.info("停机坪信息 tarmac:"+tarmac.toString());
        //将停机坪xy轴传入节点第一位
        Node depot = new Node(tarmac.getTarmacLng().doubleValue(),tarmac.getTarmacLat().doubleValue());
        //将停机坪装载到任务点上
        nodes[0] = depot;
        //创建一个新的节点类 将节点类添加到集合第一位
        taskPointList.add(0,new TaskPoint());
        //定义一个map key为节点id  value为数据库id
        Map<String,Integer> map = new HashMap<>();
        //将0坐标id传入map中
        map.put("0",0);
        //从集合索引1开始将节点id,节点x轴,y轴传入到nodes节点数组中
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node(i
                    ,taskPointList.get(i).getTaskPointLng().doubleValue()
                    ,taskPointList.get(i).getTaskPointLat().doubleValue()
                    ,4+ran.nextInt(7));
            //将节点数组的id 和数据库查询出的id存入map中 以节点id为可以 数据库id为value
            map.put(Integer.toString(i),taskPointList.get(i).getTaskPointId().intValue());
        }
        //日志打印任务点节点数组
        logger.info("任务点节点数组长度  nodes"+nodes.length);
        //调用算法 返回结果
        VRP vrp=new VRP();
        //调用算法 传入节点长度-1 nodes节点数组 和无人机数量
        List<String> list = vrp.VrpSolution(taskPointList.size()-1, uarList.size(), nodes);
        //判断返回的结果
        if (list.size() == 0){
            throw new ServiceException(202,"没有适合的无人机路径方案");
        }
        //取出集合第一个
        String path=list.get(0);
        //判断出最短路径
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).length()<path.length()&&list.get(i).length()>0){
                path = list.get(i);
            }
        }
        //判断得出最优路径之后 对最优路径进行切割取出每一位id然后与数据库id进行切换
        String[] vehicle_s = path.split("Vehicle ");
        //定义一个空的最优路径
        String sout="";
        //取出切换后的每一个路径信息 从索引1开始
        for (int i = 1; i < vehicle_s.length; i++) {
            //通过->进行二次截取
            String[] split = vehicle_s[i].split("->");
            //将截取的数组进行二次循环
            for (int i1 = 0; i1 < split.length; i1++) {
                if (i1 == 0){
                    sout += split[i1]+"->";
                }else if (i1 == split.length-1){
                    if (i == vehicle_s.length-1){
                        sout += split[i1];
                    }else {
                        sout += split[i1]+",";
                    }
                }else {
                    String id = split[i1];
                    Integer integer = map.get(id);
                    if (integer != null){
                        sout += integer+"->";
                    }
                }
            }
        }
        //将最优的路径存入日志
        logger.info("最优路径 path: "+sout);
        //判断得到最优的路径 将最优路径保存到数据库
        //根据停机坪id查询路径方案
        Solution s = solutionMapper.selectTarmac(solutionDto.getTarmacId());
        //创建新的解决方案类
        Solution solution = new Solution();
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
        solution.setOptimalPath(sout);
        //先判断数据库是否有此路径的方案 如果有则修改 没有直接添加
        if (s == null){
            //直接添加
            solutionMapper.insertSelective(solution);
        }else {
            solution.setSolutionId(s.getSolutionId());
            solution.setCreateDate(s.getCreateDate());
            //修改
            solutionMapper.updateByPrimaryKeySelective(solution);
        }
        return sout;

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

    /**
     * 根据id查询最优路径
     * @param solutionId
     * @return
     */
    @Override
    public Solution findPathSoutionBySolutionId(long solutionId) {
        return solutionMapper.selectByPrimaryKey(solutionId);
    }

    /**
     * 删除最优路径
     * @param solutionId
     */
    @Override
    @Transactional
    public void deletePath(long solutionId) {
        solutionMapper.deleteByPrimaryKey(solutionId);
    }

}

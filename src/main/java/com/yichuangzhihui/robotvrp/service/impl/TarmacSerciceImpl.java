package com.yichuangzhihui.robotvrp.service.impl;

import com.yichuangzhihui.robotvrp.dto.TarmacDto;
import com.yichuangzhihui.robotvrp.mapper.TarmacMapper;
import com.yichuangzhihui.robotvrp.pojo.Tarmac;
import com.yichuangzhihui.robotvrp.service.TarmacService;
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

/**
 * @Date :  2020/12/8 9:27
 * @Author : fanweihao
 * @Version: V1.0
 */
@Log4j2
@Service
public class TarmacSerciceImpl implements TarmacService {

    private Logger logger = LogManager.getLogger(TarmacSerciceImpl.class.getName());

    @Resource
    private TarmacMapper tarmacMapper;


    /**
     * 新增停机坪
     * @param tarmacDto
     */
    @Override
    @Transactional
    public void addTarmac(TarmacDto tarmacDto) {
        logger.info("addTarmac begin tarmac::"+tarmacDto.toString());
           // color scheme  配色方案
        // code style scheme 代码样式方案
        // view mode 查看模式
        // look and feel 外观和感觉

        Access.isNull(tarmacDto.getTarmacLat(),"纬度不能为空");
        Access.isNull(tarmacDto.getTarmacLng(),"经度不能为空");

        if (tarmacDto.getTarmacName()!=null){
            //查询停机坪名称是否重复
            Tarmac tarmac = tarmacMapper.selectTarmacLat(tarmacDto.getTarmacName());
            if (tarmac!=null){
                throw new ServiceException(201,"停机坪名称重复");
            }
        }

        Tarmac ts= new Tarmac();

        //名称
        ts.setTarmacName(tarmacDto.getTarmacName());
        //维度
        ts.setTarmacLat(tarmacDto.getTarmacLat());
        //经度
        ts.setTarmacLng(tarmacDto.getTarmacLng());
        //创建时间
        ts.setCreateDate(new Date());
        //是否删除
        ts.setIsDelete(false);
        //添加到数据库
        tarmacMapper.insertSelective(ts);
        Tarmac tarmac1 = tarmacMapper.selectTarmacLat(tarmacDto.getTarmacName());
        //判断新增停机坪有没有添加任务点
        if (tarmacDto.getTaskPointIds()!=null&&tarmacDto.getTaskPointIds().length>0){
            this.setTarmacAndTaskPoint(tarmac1.getTarmacId(),tarmacDto.getTaskPointIds());
        }
        //判断新增停机坪有没有添加无人机
        if (tarmacDto.getUarIds()!=null&&tarmacDto.getUarIds().length>0){
            this.setTarmacAndUar(tarmac1.getTarmacId(),tarmacDto.getUarIds());
        }



    }
    //停机坪与任务点关联
    @Transactional
    public void setTarmacAndTaskPoint(Long tarmacId, Long[] taskPointIds) {
        logger.info("setTarmacAndTaskPoint:: tarmacId="+tarmacId+"And taskPointIds[]="+taskPointIds);
        if (taskPointIds!=null&&taskPointIds.length>0){
            for (Long taskPointId : taskPointIds) {
                Map<String,Long> map = new HashMap<>();
                map.put("tarmacId",tarmacId);
                map.put("taskPointId",taskPointId);
                tarmacMapper.setTarmacAndTaskPoint(map);
            }
        }

    }

    /**
     * 修改停机坪
     * @param tarmacDto
     */
    @Transactional
    @Override
    public void updateTarmac(TarmacDto tarmacDto) {
        logger.info("updateTarmac begin tarmac::"+tarmacDto.toString());

        //查询是否有相同名称
        if (tarmacDto.getTarmacName()!=null){
            Tarmac t = tarmacMapper.selectTarmacLat(tarmacDto.getTarmacName());
            if (t!=null&&t.getTarmacId()!=tarmacDto.getTarmacId()){
                throw new ServiceException(201,"停机坪名称重复");
            }
        }
        Tarmac tarmac=new Tarmac();
        //停机坪id
        tarmac.setTarmacId(tarmacDto.getTarmacId());
        //停机坪名称
        tarmac.setTarmacName(tarmacDto.getTarmacName());
        //经度
        tarmac.setTarmacLng(tarmacDto.getTarmacLng());
        //维度
        tarmac.setTarmacLat(tarmacDto.getTarmacLat());
        //修改
        tarmacMapper.updateByPrimaryKeySelective(tarmac);

        //修改之前先清除之前关联关系
        tarmacMapper.deleteTarmacIdAndTaskPointId(tarmacDto.getTarmacId());
        //添加新的关联关系
        if (tarmacDto.getTaskPointIds()!=null&&tarmacDto.getTaskPointIds().length>0){
            this.setTarmacAndTaskPoint(tarmacDto.getTarmacId(),tarmacDto.getTaskPointIds());
        }

        //修改之前先清除之前关联关系
        tarmacMapper.deleteTarmacIdAndUarId(tarmacDto.getTarmacId());

        //添加新的关联关系
        if (tarmacDto.getUarIds()!=null&&tarmacDto.getUarIds().length>0){
            this.setTarmacAndUar(tarmacDto.getTarmacId(),tarmacDto.getUarIds());
        }




    }

    /**
     * 删除停机坪
     * @param tarmacId
     */
    @Override
    @Transactional
    public void deleteTarmac(long tarmacId) {
        logger.info("deleteTarmac begin id =:"+tarmacId);

  /*      //根据id查询停机坪
        Tarmac tarmac = tarmacMapper.selectByPrimaryKey(tarmacId);

        if (tarmac==null){
            throw new ServiceException(201,"停机坪不存在");
        }
        if (tarmac.getIsDelete()==true){
            throw new ServiceException(201,"停机坪已被删除");
        }
        tarmac.setIsDelete(true);
        tarmacMapper.updateByPrimaryKeySelective(tarmac);*/

        //先清除停机坪与任务点关联关系
       tarmacMapper.deleteTarmacIdAndTaskPointId(tarmacId);
        //先清除停机坪与无人机的关联关系
       tarmacMapper.deleteTarmacIdAndUarId(tarmacId);
       tarmacMapper.deleteByPrimaryKey(tarmacId);

    }

    /**
     * 查询所有停机坪
     * @return
     */
    @Override
    public List<Tarmac> selectAllTarmac() {

        logger.info("selectAllTarmac begin ");

        List<Tarmac> list=tarmacMapper.selectAllTarmac();

        logger.info("selectAllTarmac is successful "+list);
        return list;
    }

    /**
     * 停机坪更新任务点
     * @param tarmacDto
     */
    @Override
    @Transactional
    public void addTarmacIdAndTaskPointId(TarmacDto tarmacDto) {
        logger.info("addTarmacIdAndTaskPointId begin tarmacDto::"+tarmacDto.toString());

        Access.isNull(tarmacDto.getTarmacId(),"停机坪id不能为空");
        //先清除之前的关联关系
        tarmacMapper.deleteTarmacIdAndTaskPointId(tarmacDto.getTarmacId());
        //添加依赖关系
        this.setTarmacAndTaskPoint(tarmacDto.getTarmacId(),tarmacDto.getTaskPointIds());

    }

    /**
     * 停机坪更新无人机
     * @param tarmacDto
     */
    @Override
    @Transactional
    public void addTarmacIdAndUarId(TarmacDto tarmacDto) {
        logger.info("addTarmacIdAndUarId begin tarmacDto::"+tarmacDto.toString());
        Access.isNull(tarmacDto.getTarmacId(),"停机坪id不能为空");
        //先清除之前的关联关系
        tarmacMapper.deleteTarmacIdAndUarId(tarmacDto.getTarmacId());
        //添加停机坪与无人机的依赖关系
        this.setTarmacAndUar(tarmacDto.getTarmacId(),tarmacDto.getUarIds());

    }

    /**
     * 查询单个停机坪
     * @param tarmacId
     * @return
     */
    @Override
    public Tarmac selectOneTarmac(long tarmacId) {
        logger.info("selectOneTarmac bgin tarmacId= "+tarmacId);

        return tarmacMapper.selectByPrimaryKey(tarmacId);
    }

    /**
     * 根据停机坪id查询关联的任务点id
     * @param tarmacId
     * @return
     */
    @Override
    public List<Long> findPointIdsByTarmacId(long tarmacId) {
        logger.info("findPointIdsByTarmacIdId bgin tarmacId= "+tarmacId);
        return tarmacMapper.findPointIdsByTarmacIdId(tarmacId);
    }

    /**
     * 根据停机坪id查询关联的无人机id
     * @param tarmacId
     * @return
     */
    @Override
    public List<Long> findUarIdsByTarmacId(long tarmacId) {
        logger.info("findUarIdsByTarmacIdId bgin tarmacId= "+tarmacId);
        return tarmacMapper.findUarIdsByTarmacIdId(tarmacId);
    }

    //添加停机坪与无人机的依赖关系
    @Transactional
    public void setTarmacAndUar(Long tarmacId, Long[] uarIds) {
        logger.info("setTarmacAndTaskPoint:: tarmacId="+tarmacId+"And taskPointIds[]="+uarIds);
        if (uarIds!=null&&uarIds.length>0){
            for (Long uarId : uarIds) {
                Map<String,Long> map = new HashMap<>();
                map.put("tarmacId",tarmacId);
                map.put("uarId",uarId);
                tarmacMapper.setTarmacAndUar(map);
            }
        }
    }
}

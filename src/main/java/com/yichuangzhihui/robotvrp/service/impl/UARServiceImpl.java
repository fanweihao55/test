package com.yichuangzhihui.robotvrp.service.impl;

import com.yichuangzhihui.robotvrp.mapper.UarMapper;
import com.yichuangzhihui.robotvrp.pojo.Uar;
import com.yichuangzhihui.robotvrp.service.UARService;
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
 * @Date :  2020/12/11 14:18
 * @Author : fanweihao
 * @Version: V1.0
 */
@Service
@Log4j2
public class UARServiceImpl implements UARService {
    private Logger logger = LogManager.getLogger(UARServiceImpl.class.getName());

    @Resource
    private UarMapper uarMapper;

    //新增无人机
    @Override
    @Transactional
    public void addUAR(Uar uars) {
        logger.info("addUAR begin uars::"+uars.toString());

        Access.isNull(uars.getUarName(),"无人机名称不能为空");

        Uar uar=uarMapper.getUAR(uars.getUarName());

        if (uar!=null){
            throw new ServiceException(201,"无人机名称重复");
        }

        uars.setCreateTime(new Date());

        uars.setUarName(uars.getUarName());

        uars.setIsdelete(false);

        //添加无人机
        uarMapper.insertSelective(uars);
    }

    /**
     * 修改无人机
     * @param uar
     */
    @Override
    @Transactional
    public void updateUAR(Uar uar) {
        logger.info("updateUAR begin uars::"+uar.toString());

        Uar uar1 = uarMapper.getUAR(uar.getUarName());

        if (uar1!=null&&uar1.getUarId()!=uar.getUarId()){
            throw new ServiceException(201,"无人机名称重复");
        }

        uarMapper.updateByPrimaryKey(uar);
    }

    /**
     * 查询全部无人机
     * @return
     */
    @Override
    public List<Uar> selectAllUAR() {
        logger.info("selectAllUAR begin selectAllUAR::");

        return uarMapper.selectAllUAR();
    }

    /**
     * 查询单个无人机
     * @return
     */
    @Override
    public Uar selectOneUAR(long uarid) {
        logger.info("selectOneUAR begin uarid::"+uarid);

        return uarMapper.selectByPrimaryKey(uarid);
    }

    /**
     * 删除无人机
     * @param uarid
     */
    @Override
    @Transactional
    public void deleteUAR(long uarid) {
        logger.info("deleteUAR begin uarid::"+uarid);

        //清除无人机与停机坪关联关系
        uarMapper.delteUarIdAndTarmacID(uarid);
        //删除无人机
        uarMapper.deleteByPrimaryKey(uarid);
    }

}

package com.yichuangzhihui.robotvrp.util;

import org.springframework.util.StringUtils;

public class Access {

    public static void isNull(Object obj,String msg){
        if (StringUtils.isEmpty(obj)){
            throw new ServiceException(201,msg);
        }
    }

    public static void isNotNull(Object obj,String msg){
        if (!StringUtils.isEmpty(obj)){
            throw new ServiceException(201,msg);
        }
    }

    public static void isReal(Object obj,String msg,Integer code){
        if (StringUtils.isEmpty(obj)){
            throw new ServiceException(code,msg);
        }
    }
}

package com.yichuangzhihui.robotvrp.config;

import com.alibaba.fastjson.JSON;
import com.yichuangzhihui.robotvrp.aop.AuthCheck;
import com.yichuangzhihui.robotvrp.aop.LocalUserContext;
import com.yichuangzhihui.robotvrp.pojo.Users;
import com.yichuangzhihui.robotvrp.util.RedisUtils;
import com.yichuangzhihui.robotvrp.util.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 拦截器 验证token
 */
@Log4j2
public class CustomInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtils redisUtils;


    /**
     *  验证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 判断方法上是否有此注解
        Optional<AuthCheck> authCheck = this.getAuthCheck(handler);
        //没有这个注解就放行
        if (!authCheck.isPresent()) {
            return true;
        }

        String token = request.getHeader("token");

        //获取cookie
        if (token == null){
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSON(new Result(null,"请登录","204")).toString());
            return false;
        }

        String value = redisUtils.get(token);
        log.info("token value : ----" + value);

        //redis里拿不到数据则删除cookie
        if (StringUtils.isEmpty(value)){
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSON(new Result(null,"验证失败,请重新登录","204")).toString());
            return false;
        }
        //拿到redis里的数据转换为对象 , 带进线程里
        Users user = JSON.parseObject(value, Users.class);
        LocalUserContext.set(user);
        return true;
    }


    /**
     * 验证是否有此注解
     * @param handler
     * @return
     */
    private Optional<AuthCheck> getAuthCheck(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AuthCheck authCheck = handlerMethod.getMethod().getAnnotation(AuthCheck.class);
            if (authCheck == null) {
                return Optional.empty();
            }
            return Optional.of(authCheck);
        }
        return Optional.empty();
    }
}

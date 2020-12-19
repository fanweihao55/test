package com.yichuangzhihui.robotvrp.controller;

import com.alibaba.fastjson.JSON;
import com.yichuangzhihui.robotvrp.pojo.Users;
import com.yichuangzhihui.robotvrp.service.UserService;
import com.yichuangzhihui.robotvrp.util.MapTokenManager;
import com.yichuangzhihui.robotvrp.util.Menus;
import com.yichuangzhihui.robotvrp.util.RedisUtils;
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
import java.util.HashMap;

/**
 * @Date :  2020/12/10 14:10
 * @Author : fanweihao
 * @Version: V1.0
 */
@RestController
@RequestMapping("/login/")
@Api("用户登录")
public class LogInController {

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private UserService userService;
    //用户登录
    @PostMapping("/user_log")
    @ApiOperation("登录验证")
    public Result user_log(@RequestBody Users users){

        Users users1=userService.user_log(users);
        //创建redis key
        String redisKey = MapTokenManager.generateToken(users1.getId().toString());

        //将数据存进缓存里,设置过期时间为1天
        redisUtils.setMx(redisKey, 60*60*24 , JSON.toJSONString(users1));

        HashMap<String, Object> map = new HashMap<>();
        map.put("username",users1.getUsername());
        map.put("cookieKey", Menus.TOKEN_NAME);
        map.put("cookieValue",redisKey);
        return new Result(map , "登入成功","0");


    }


    /**
     * 添加用户
     * @param users
     * @return
     */
    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public Result addUser(@RequestBody Users users){

        userService.addUser(users);

        return new Result("添加用户成功","0");

    }


    @GetMapping("/selectUserOne/{id}")
    @ApiOperation("根据id查询单个用户")
    public Result selectUserOne(@PathVariable("id") long id){

        Users users1=userService.selectUserOne(id);

        return new Result("添加用户成功","0");

    }



    /**
     * 修改用户
     * @param users
     * @return
     */
    @PostMapping("/updateUser")
    @ApiOperation("修改用户")
    public Result updateUser(@RequestBody Users users){

        userService.updateUser(users);

        return new Result("修改用户成功","0");

    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping("/deleteUser/{id}")
    @ApiOperation("删除用户")
    public Result deleteUser(@PathVariable("id") long id){

        userService.deleteUser(id);

        return new Result("删除用户成功","0");

    }

}

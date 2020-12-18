package com.yichuangzhihui.robotvrp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * @Date :  2020/12/15 14:24
 * @Author : fanweihao
 * @Version:  V1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Users",description = "用户表")
public class Users {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("创建时间 后台给出")
    private Date createTime;
    @ApiModelProperty("是否删除 后台给出")
    private String isdelete;
}

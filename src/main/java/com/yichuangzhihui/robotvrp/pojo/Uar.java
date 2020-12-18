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
 * @Date :  2020/12/14 14:21
 * @Author : fanweihao
 * @Version:  V1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Uar",description = "无人机表")
public class Uar {
    @ApiModelProperty("无人机id")
    private Long uarId;
    @ApiModelProperty("无人机名称")
    private String uarName;
    @ApiModelProperty("创建时间 后台给出")
    private Date createTime;
    @ApiModelProperty("是否删除 后台给出")
    private Boolean isdelete;
}

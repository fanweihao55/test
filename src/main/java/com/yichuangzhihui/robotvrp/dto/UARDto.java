package com.yichuangzhihui.robotvrp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Date :  2020/12/11 14:23
 * @Author : fanweihao
 * @Version: V1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UARDto",description = "无人机传参表")
public class UARDto {

    //停机坪id
    @ApiModelProperty("停机坪id")
    private Long tarmacId;
    //无人机名
    @ApiModelProperty("无人机名")
    private String name;
    //无人机id
    @ApiModelProperty("无人机id")
    private Long uarid;

}

package com.yichuangzhihui.robotvrp.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TaskPoint",description = "任务点表")
public class TaskPoint {
    /**
    * 任务点id
    */
    @ApiModelProperty("任务点id")
    private Long taskPointId;

    /**
    * 任务点名称
    */
    @ApiModelProperty("任务点名称")
    private String taskPointName;

    /**
    * 任务点经度
    */
    @ApiModelProperty("任务点经度")
    private BigDecimal taskPointLng;

    /**
    * 任务点纬度
    */
    @ApiModelProperty("任务点纬度")
    private BigDecimal taskPointLat;

    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间 后台给出")
    private Date createDate;

    /**
    * 是否删除
    */
    @ApiModelProperty("是否删除 后台给出")
    private Boolean isDelete;


}

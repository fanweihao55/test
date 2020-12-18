package com.yichuangzhihui.robotvrp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Date :  2020/12/12 17:03
 * @Author : fanweihao
 * @Version: V1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TarmacDto",description = "停机坪传参表")
public class TarmacDto {


    /**
     * 停机坪主键
     */
    @ApiModelProperty("停机坪id")
    private Long tarmacId;
    /**
     * 停机坪名称
     */
    @ApiModelProperty("停机坪名称")
    private String tarmacName;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private BigDecimal tarmacLng;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private BigDecimal tarmacLat;
    /**
     * 任务点id
     */
    @ApiModelProperty("任务点id 数组")
    private Long[] taskPointIds;
    /**
     * 无人机id
     */
    @ApiModelProperty("无人机id 数组")
    private Long[] uarIds;
}

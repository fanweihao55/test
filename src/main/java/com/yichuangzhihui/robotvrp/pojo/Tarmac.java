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


/**
 * @Date :  2020/12/14 9:53
 * @Author : fanweihao
 * @Version:  V1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Tarmac",description = "停机坪表")
public class Tarmac {
    /**
    * 主键
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

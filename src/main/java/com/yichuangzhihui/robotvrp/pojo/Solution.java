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
 * @Date :  2020/12/14 16:52
 * @Author : fanweihao
 * @Version:  V1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Solution",description = "解决方案表")
public class Solution {
    @ApiModelProperty("解决方案表主键id")
    private Long solutionId;
    @ApiModelProperty("停机坪名称")
    private String tarmacName;
    @ApiModelProperty("创建时间 后台给出")
    private Date createDate;
    @ApiModelProperty("修改时间 后台给出")
    private Date updateDate;
    @ApiModelProperty("是否删除 后台给出")
    private Boolean isDelete;
    @ApiModelProperty("最优解决路径")
    private String optimalPath;
    @ApiModelProperty("停机坪id")
    private Long tarmacId;
}

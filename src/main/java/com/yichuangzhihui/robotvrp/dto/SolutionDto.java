package com.yichuangzhihui.robotvrp.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Date :  2020/12/13 14:42
 * @Author : fanweihao
 * @Version: V1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SolutionDto",description = "解决方案表传参")
public class SolutionDto {


    /**
     * 停机坪主键
     */
    @ApiModelProperty("停机坪主键 必须传")
    private Long tarmacId;
    /**
     * 任务点id
     */
    @ApiModelProperty("任务点id 暂时可不传")
    private Long[] taskPointIds;


}

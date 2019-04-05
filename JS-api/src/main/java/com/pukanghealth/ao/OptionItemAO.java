package com.pukanghealth.ao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author:LiWang
 * @Description:
 * @Date:Created in 11:45 2019/4/2
 * @Modified By:
 */
@Data
public class OptionItemAO {
    @ApiModelProperty(value = "所属模块分类")
    @NotBlank(message="所属模块分类Id不能")
    private String optionModuleId;
    @ApiModelProperty(value = "所属模块下组分类")
    @NotBlank(message="所属模块下组分类Id不能为空")
    private String groupId;
}

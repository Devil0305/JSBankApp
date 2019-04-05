package com.pukanghealth.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author:LiWang
 * @Description:
 * @Date:Created in 15:19 2019/3/29
 * @Modified By:
 */
@Data
@ApiModel(value = "修改手机")
public class MobileCaptchaAO {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;
    @ApiModelProperty(value = "验证码")
    @NotBlank(message="验证码不能为空")
    private String captcha;
}

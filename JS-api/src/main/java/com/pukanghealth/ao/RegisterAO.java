

package com.pukanghealth.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 注册表单
 *
 * @author wangli
 */
@Data
@ApiModel(value = "注册表单")
public class RegisterAO {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message="验证码不能为空")
    private String captcha;

}

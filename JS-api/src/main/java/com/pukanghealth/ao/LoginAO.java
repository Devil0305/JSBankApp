

package com.pukanghealth.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author wangli
 */
@Data
@ApiModel(value = "登录表单")
public class LoginAO {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "验证码/密码")
    @NotBlank(message="验证码/密码不能为空")
    private String password;

    @ApiModelProperty(value = "登录类型")
    @NotBlank(message="登录类型不能为空")
    private String type;


}

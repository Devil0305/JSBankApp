package com.pukanghealth.controller;

import com.pukanghealth.annotation.Login;
import com.pukanghealth.annotation.LoginUser;
import com.pukanghealth.ao.MobileCaptchaAO;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppUserEntity;
import com.pukanghealth.service.AppUserService;
import com.pukanghealth.service.SMSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pukanghealth.common.utils.R;
import springfox.documentation.annotations.ApiIgnore;


/**
 * app端用户表
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
@RestController
@RequestMapping("api/")
@Api(tags="用户信息")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private SMSService smsService;

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @Login
    @GetMapping("/appuser/userInfo")
    @ApiOperation(value="获取用户信息", response=AppUserEntity.class)
    public R userInfo(@ApiIgnore @LoginUser AppUserEntity user){
        return R.ok().put("user", user);
    }


    /**
     * 修改用户信息
     */
    @Login
    @PostMapping("/appuser/updateUserInfo")
    @ApiOperation(value="实名认证")
    public R updateUserInfo(@RequestBody AppUserEntity appUser){
        ValidatorUtils.validateEntity(appUser);
        appUserService.updateById(appUser);

        return R.ok();
    }

    /**
     * 更换手机号
     */
    @Login
    @PostMapping("/appuser/updateMobile")
    @ApiOperation(value="更换手机号")
    public R updateMobile(@RequestBody MobileCaptchaAO mobileCaptchaAO,@ApiIgnore @RequestAttribute("userId")Long userId){
        try {
            //表单校验
            ValidatorUtils.validateEntity(mobileCaptchaAO);
            smsService.validateCaptcha(mobileCaptchaAO.getMobile(), mobileCaptchaAO.getCaptcha());
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
        appUserService.updateMobileByUserId(mobileCaptchaAO.getMobile(),userId);
        return R.ok();
    }

}

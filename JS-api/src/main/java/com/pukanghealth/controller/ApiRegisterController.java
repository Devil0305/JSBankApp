

package com.pukanghealth.controller;

import com.pukanghealth.ao.RegisterAO;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppUserEntity;

import com.pukanghealth.common.utils.R;
import com.pukanghealth.service.AppUserService;
import com.pukanghealth.service.SMSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 注册接口
 *
 * @author wangli
 */
@RestController
@RequestMapping("/api")
@Api(tags="注册接口")
public class ApiRegisterController {
    @Autowired
    private AppUserService userService;
    @Autowired
    private SMSService smsService;
    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterAO form){
        boolean flag = false;
        ValidatorUtils.validateEntity(form);
        try {
            flag=smsService.validateCaptcha(form.getMobile(), form.getCaptcha());
        } catch (Exception e) {
            e.printStackTrace();
            return  R.error(500,e.getMessage());
        }
        if(flag) {
            //表单校验
            AppUserEntity user = new AppUserEntity();
            AppUserEntity isExist = userService.queryByMobile(form.getMobile());
            user.setUserId(isExist != null ? isExist.getUserId() : null);
            user.setMobile(form.getMobile());
            user.setUserName(form.getMobile());
            user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
            user.setCreateTime(new Date());
            userService.saveOrUpdate(user);
        }else {
            return R.error("验证码不正确");
        }
        return R.ok();
    }
}

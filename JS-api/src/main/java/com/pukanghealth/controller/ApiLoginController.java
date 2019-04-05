

package com.pukanghealth.controller;


import com.pukanghealth.annotation.Login;
import com.pukanghealth.ao.LoginAO;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.service.AppTokenService;
import com.pukanghealth.service.AppUserService;
import com.pukanghealth.service.SMSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录接口
 *
 * @author wangli
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
public class ApiLoginController {
    private Logger logger = LoggerFactory.getLogger(ApiLoginController.class);

    @Autowired
    private AppUserService userService;
    @Autowired
    private AppTokenService tokenService;
    @Autowired
    private SMSService smsService;

    @PostMapping("login")
    @ApiOperation("登录type登录方式标识1验证码登录")
    public R login(@RequestBody LoginAO form){
        //表单校验
        ValidatorUtils.validateEntity(form);
        if("1".equals(form.getType())){//验证码登录
            try {
                //表单校验
                smsService.validateCaptcha(form.getMobile(), form.getPassword());
            } catch (Exception e) {
                return R.error(e.getMessage());
            }
        }
        Map<String, Object> map = userService.login(form);

        return R.ok(map);
    }

    @Login
    @PostMapping("logout")
    @ApiOperation("退出")
    public R logout(@ApiIgnore @RequestAttribute("userId") long userId){
        tokenService.expireToken(userId);
        return R.ok();
    }

    /**
     * 发送短信验证码
     * @param
     * @return
     */
    @PostMapping(value="/sendSmsCaptcha")
    @ApiOperation("发送短信验证码")
    public @ResponseBody R sendSmsCaptcha(@RequestBody String mobile, HttpServletRequest request){
        try {
            if (null == mobile) {
                Map<String,Object> map = new HashMap<>();
                String staticTerminal = request.getParameter("staticTerminal");
                String staticVersion = request.getParameter("staticVersion");
                String userId = (String) request.getAttribute("userId");
                logger.error("api/sendSmsCaptcha,mobile,参数信息mobile=null,用户信息:userId=" + userId +
                        ",终端为" + staticTerminal + ",版本为" + staticVersion);
                map.put("errorCode", 17);
                map.put("errorMessage", "mobile不能为空(错误代码:010901170)");
                return R.error(map);
            }
            String captcha = smsService.getCaptchaByMobile(mobile);
            smsService.sendCaptcha(mobile, captcha);
        }catch (Exception e){
            return R.error(e.getMessage());
        }

        return R.ok();
    }

    /**
     * 验证短信验证码
     * @param
     * @return
     */
    @PostMapping(value="/smsCaptchaLogin")
//    @ApiOperation("验证短信验证码")
    public @ResponseBody R smsCaptchaLogin(@RequestBody LoginAO form) {
        try {
            //表单校验
            ValidatorUtils.validateEntity(form);
            smsService.validateCaptcha(form.getMobile(), form.getPassword());
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
        //用户登录
        Map<String, Object> map = userService.login(form);
        return R.ok(map);
    }
}

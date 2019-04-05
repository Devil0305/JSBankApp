package com.pukanghealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pukanghealth.annotation.Login;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppCouponEntity;
import com.pukanghealth.entity.AppUserCouponDO;
import com.pukanghealth.entity.AppUserCouponEntity;
import com.pukanghealth.service.AppCouponService;
import com.pukanghealth.service.AppUserCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 优惠券
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/appcoupon")
@Api(tags="优惠券")
public class AppCouponController {
    private Logger logger = LoggerFactory.getLogger(AppCouponController.class);
    @Autowired
    private AppCouponService appCouponService;
    @Autowired
    private AppUserCouponService appUserCouponService;
    /**
     * 列表
     */
    @Login
    @GetMapping("/list")
    @ApiOperation("获取所有可买优惠券列表")
    public R list(){
        List<AppCouponEntity> list = appCouponService.list(new QueryWrapper<AppCouponEntity>().eq("coupon_deleted",0).orderByDesc("create_time"));

        return R.ok().put("list", list);
    }

    /**
     * 获取个人优惠券列表
     * @param type 0:待使用  1：过期  2:已使用
     * @param
     * @return
     */
    @Login
    @GetMapping("/myCouponList")
    @ApiOperation("获取我的优惠券列表（0:待使用  1：过期  2:已使用）")
    public R myCouponList(@RequestParam("type") Integer type, @ApiIgnore @RequestAttribute("userId")String userId, HttpServletRequest request){
        if (null == type) {
            Map<String,Object> map = new HashMap<>();
            String staticTerminal = request.getParameter("staticTerminal");
            String staticVersion = request.getParameter("staticVersion");
            logger.error("api/myCouponList,type,参数信息type=null,用户信息:userId=" + userId +
                    ",终端为" + staticTerminal + ",版本为" + staticVersion);
            map.put("errorCode", 17);
            map.put("errorMessage", "type不能为空(错误代码:010901170)");
            return R.error(map);
        }
        List<AppUserCouponDO> list = appUserCouponService.myCouponListByUserId(type,userId);

        return R.ok().put("list", list);
    }


    /**
     * 信息
     */
    @Login
    @PostMapping("/generateQRcode")
    @ApiOperation("生成二维码")
    public R info(@RequestParam("userCouponId") Integer userCouponId){
       //todo 生成二维码
        return null;
    }


}

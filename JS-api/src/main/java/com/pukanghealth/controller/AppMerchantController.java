package com.pukanghealth.controller;

import java.util.Arrays;
import java.util.Map;

import com.pukanghealth.annotation.Login;
import com.pukanghealth.ao.AppMerchantAO;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppMerchantEntity;
import com.pukanghealth.service.AppMerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.R;



/**
 * 合作商户
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Api(tags = "合作商户")
@RestController
@RequestMapping("api/appmerchant")
public class AppMerchantController {
    @Autowired
    private AppMerchantService appMerchantService;

    /**
     * 合作商户列表
     */
    @PostMapping("merchantList")
    @Login
    @ApiOperation("获取合作商户信息")
    public R list(@RequestBody AppMerchantAO appMerchantAO){
        PageUtils page = appMerchantService.queryMerchantPage(appMerchantAO);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{merchantId}")
    public R info(@PathVariable("merchantId") Integer merchantId){
        AppMerchantEntity appMerchant = appMerchantService.getById(merchantId);

        return R.ok().put("appMerchant", appMerchant);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppMerchantEntity appMerchant){
        appMerchantService.save(appMerchant);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppMerchantEntity appMerchant){
        ValidatorUtils.validateEntity(appMerchant);
        appMerchantService.updateById(appMerchant);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] merchantIds){
        appMerchantService.removeByIds(Arrays.asList(merchantIds));

        return R.ok();
    }

}

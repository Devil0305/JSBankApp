package com.pukanghealth.controller;

import com.pukanghealth.annotation.Login;
import com.pukanghealth.ao.AppNetworkAO;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppNetworkEntity;
import com.pukanghealth.service.AppNetworkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.R;

import java.util.Arrays;


/**
 * 网点信息
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/appnetwork")
@Api(tags = "网点查询")
public class AppNetworkController {
    @Autowired
    private AppNetworkService appNetworkService;

    /**
     * 建行网点列表
     */
    @Login
    @PostMapping("CCBList")
    @ApiOperation("获取建行网点信息")
    public R CCBList(@RequestBody AppNetworkAO appNetworkAO) {

        PageUtils page = appNetworkService.queryCCBPage(appNetworkAO);

        return R.ok().put("page", page);
    }

    /**
     * 社保网点列表
     * @return
     */
    @Login
    @PostMapping("socialSecurityList")
    @ApiOperation("获取社保网点信息")
    public R socialSecurityList(@RequestBody AppNetworkAO appNetworkAO){

        PageUtils page = appNetworkService.querySocialSecurityPage(appNetworkAO);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{networkId}")
    public R info(@PathVariable("networkId") Integer networkId){
        AppNetworkEntity appNetwork = appNetworkService.getById(networkId);

        return R.ok().put("appNetwork", appNetwork);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppNetworkEntity appNetwork){
        appNetworkService.save(appNetwork);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppNetworkEntity appNetwork){
        ValidatorUtils.validateEntity(appNetwork);
        appNetworkService.updateById(appNetwork);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] networkIds){
        appNetworkService.removeByIds(Arrays.asList(networkIds));

        return R.ok();
    }

}

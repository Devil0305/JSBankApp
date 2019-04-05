package com.pukanghealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppFeatureEntity;
import com.pukanghealth.service.AppFeatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 首页模块显示控制
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
@RestController
@RequestMapping("api/appfeature")
public class AppFeatureController {
    @Autowired
    private AppFeatureService appFeatureService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = appFeatureService.queryPage(params);
        List<AppFeatureEntity> list = appFeatureService.list(new QueryWrapper<AppFeatureEntity>());
        return R.ok().put("page", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{featureId}")
    public R info(@PathVariable("featureId") Integer featureId){
        AppFeatureEntity appFeature = appFeatureService.getById(featureId);

        return R.ok().put("appFeature", appFeature);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppFeatureEntity appFeature){
        appFeatureService.save(appFeature);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppFeatureEntity appFeature){
        ValidatorUtils.validateEntity(appFeature);
        appFeatureService.updateById(appFeature);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] featureIds){
        appFeatureService.removeByIds(Arrays.asList(featureIds));

        return R.ok();
    }

}

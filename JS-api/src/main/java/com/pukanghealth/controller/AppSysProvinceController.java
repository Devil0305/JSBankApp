package com.pukanghealth.controller;

import java.util.Arrays;
import java.util.Map;

import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppSysProvinceEntity;
import com.pukanghealth.service.AppSysProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.R;



/**
 * 
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/appsysprovince")
public class AppSysProvinceController {
    @Autowired
    private AppSysProvinceService appSysProvinceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = appSysProvinceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{provinceId}")
    public R info(@PathVariable("provinceId") Integer provinceId){
        AppSysProvinceEntity appSysProvince = appSysProvinceService.getById(provinceId);

        return R.ok().put("appSysProvince", appSysProvince);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppSysProvinceEntity appSysProvince){
        appSysProvinceService.save(appSysProvince);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppSysProvinceEntity appSysProvince){
        ValidatorUtils.validateEntity(appSysProvince);
        appSysProvinceService.updateById(appSysProvince);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] provinceIds){
        appSysProvinceService.removeByIds(Arrays.asList(provinceIds));

        return R.ok();
    }

}

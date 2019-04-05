package com.pukanghealth.controller;

import java.util.Arrays;
import java.util.Map;

import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pukanghealth.entity.AppModuleOptionEntity;
import com.pukanghealth.service.AppModuleOptionService;



/**
 * 
 *
 * @author wangli
 * @email 
 * @date 2019-04-04 15:10:39
 */
@RestController
@RequestMapping("pukanghealth/appmoduleoption")
public class AppModuleOptionController {
    @Autowired
    private AppModuleOptionService appModuleOptionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = appModuleOptionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        AppModuleOptionEntity appModuleOption = appModuleOptionService.getById(id);

        return R.ok().put("appModuleOption", appModuleOption);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppModuleOptionEntity appModuleOption){
        appModuleOptionService.save(appModuleOption);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppModuleOptionEntity appModuleOption){
        ValidatorUtils.validateEntity(appModuleOption);
        appModuleOptionService.updateById(appModuleOption);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        appModuleOptionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

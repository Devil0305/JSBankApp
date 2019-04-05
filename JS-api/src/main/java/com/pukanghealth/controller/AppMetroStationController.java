package com.pukanghealth.controller;

import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppMetroStationEntity;
import com.pukanghealth.service.AppMetroStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/appmetrostation")
public class AppMetroStationController {
    @Autowired
    private AppMetroStationService appMetroStationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = appMetroStationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{metroStationId}")
    public R info(@PathVariable("metroStationId") Integer metroStationId){
        AppMetroStationEntity appMetroStation = appMetroStationService.getById(metroStationId);

        return R.ok().put("appMetroStation", appMetroStation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppMetroStationEntity appMetroStation){
        appMetroStationService.save(appMetroStation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppMetroStationEntity appMetroStation){
        ValidatorUtils.validateEntity(appMetroStation);
        appMetroStationService.updateById(appMetroStation);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] metroStationIds){
        appMetroStationService.removeByIds(Arrays.asList(metroStationIds));

        return R.ok();
    }

}

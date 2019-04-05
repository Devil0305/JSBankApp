package com.pukanghealth.controller;

import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppOrderEntity;
import com.pukanghealth.service.AppOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 订单
 *
 * @author wangli
 * @email
 * @date 2019-03-27 15:25:40
 */
@RestController
@RequestMapping("sys/apporder")
public class AppOrderController {
    @Autowired
    private AppOrderService appOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = appOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    public R info(@PathVariable("orderId") Integer orderId){
        AppOrderEntity appOrder = appOrderService.getById(orderId);

        return R.ok().put("appOrder", appOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppOrderEntity appOrder){
        appOrderService.save(appOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppOrderEntity appOrder){
        ValidatorUtils.validateEntity(appOrder);
        appOrderService.updateById(appOrder);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] orderIds){
        appOrderService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}

package com.pukanghealth.controller;

import java.util.Arrays;
import java.util.Map;

import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppTokenEntity;
import com.pukanghealth.service.AppTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.R;



/**
 * 用户Token
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
@RestController
@RequestMapping("api/apptoken")
public class AppTokenController {
    @Autowired
    private AppTokenService appTokenService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = appTokenService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    public R info(@PathVariable("userId") Long userId){
        AppTokenEntity appToken = appTokenService.getById(userId);

        return R.ok().put("appToken", appToken);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AppTokenEntity appToken){
        appTokenService.save(appToken);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AppTokenEntity appToken){
        ValidatorUtils.validateEntity(appToken);
        appTokenService.updateById(appToken);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] userIds){
        appTokenService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}

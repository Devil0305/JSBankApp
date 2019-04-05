package com.pukanghealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pukanghealth.annotation.Login;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppRecipientAddressEntity;
import com.pukanghealth.service.AppRecipientAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * 保存地址
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/apprecipientaddress")
@Api(tags="个人中心-->保存地址")
public class AppRecipientAddressController {
    private Logger logger = LoggerFactory.getLogger(AppRecipientAddressController.class);
    @Autowired
    private AppRecipientAddressService appRecipientAddressService;

    /**
     * 列表
     */
    @Login
    @GetMapping("/list")
    @ApiOperation("查询用户地址列表")
    public R list(@ApiIgnore @RequestAttribute("userId") long userId){
        logger.info("Query user address list ,userId = "+userId);
        List<AppRecipientAddressEntity> list = appRecipientAddressService.list(new QueryWrapper<AppRecipientAddressEntity>().eq("recipient_deleted",0).eq("recipient_user_id",userId));

        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @Login
    @PostMapping("/save")
    @ApiOperation("保存用户地址")
    public R saveOrUpdate(@RequestBody AppRecipientAddressEntity appRecipientAddress,@ApiIgnore @RequestAttribute("userId")Long userId){
        logger.info("save user address ,appRecipientAddress = ".concat(appRecipientAddress.toString()));
        ValidatorUtils.validateEntity(appRecipientAddress);
        appRecipientAddress.setRecipientUserId(userId.intValue());
        appRecipientAddressService.saveOrUpdate(appRecipientAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @PostMapping("/update")
//    @ApiOperation("修改用户地址")
    public R update(@RequestBody AppRecipientAddressEntity appRecipientAddress){
        ValidatorUtils.validateEntity(appRecipientAddress);
        appRecipientAddressService.updateById(appRecipientAddress);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @PostMapping("/delete")
    @ApiOperation("删除用户地址列表")
    public R delete(@RequestBody Integer[] recipientIds){
        appRecipientAddressService.updateRecipientDeletedById(recipientIds);

        return R.ok();
    }

}

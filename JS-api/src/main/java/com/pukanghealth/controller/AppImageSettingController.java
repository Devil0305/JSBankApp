package com.pukanghealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pukanghealth.annotation.Login;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.entity.AppImageSettingEntity;
import com.pukanghealth.service.AppImageSettingService;
import com.pukanghealth.vo.AppImageSettingVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 图片设置
 *
 * @author wangli
 * @email
 * @date 2019-03-27 15:25:41
 */
@RestController
@RequestMapping("api/")
@Api(tags="获取轮播图和限时抢购")
public class AppImageSettingController {
    @Autowired
    private AppImageSettingService appImageSettingService;

    /**
     * 轮播图列表
     */
    @Login
    @GetMapping("carouselImage/list")
    @ApiOperation("获取有效的轮播图列表")
    public R carouselImageList(){
        Instant instant = Instant.now();
        List<AppImageSettingEntity> page = appImageSettingService.list(new QueryWrapper<AppImageSettingEntity>().eq("image_type",0).eq("image_deleted",0).le("image_begin_time",instant).ge("image_end_time",instant).orderByDesc("image_sort"));

        return R.ok().put("list", page);
    }


    /**
     * 限时抢购列表
     */
    @Login
    @GetMapping("limitTimeShopping/list")
    @ApiOperation("获取有效的限时抢购列表")
    public R list(){
        Instant instant = Instant.now();
        List<AppImageSettingEntity> listBO = appImageSettingService.list(new QueryWrapper<AppImageSettingEntity>().eq("image_type",1).eq("image_deleted",0).le("image_begin_time",instant).ge("image_end_time",instant).orderByDesc("image_sort"));
        //获取还有多少时间结束
        List<AppImageSettingVO> listVO = new ArrayList<>(listBO.size());
        //计算离截止时间的差值（时间戳的形式）
        listVO = listBO.stream().map(appImageSettingEntity -> {
            AppImageSettingVO vo = new AppImageSettingVO();
            vo.setRemainingTime((appImageSettingEntity.getImageEndTime().getTime()-appImageSettingEntity.getImageBeginTime().getTime())/1000);
            vo.setImageEndTime(appImageSettingEntity.getImageEndTime());
            vo.setImageJumpUrl(appImageSettingEntity.getImageJumpUrl());
            vo.setImageUrl(appImageSettingEntity.getImageUrl());
            return vo;
        }).collect(Collectors.toList());

        return R.ok().put("list", listVO);
    }



}

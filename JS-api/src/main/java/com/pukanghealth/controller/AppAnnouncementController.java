package com.pukanghealth.controller;

import com.pukanghealth.annotation.Login;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppAnnouncementEntity;
import com.pukanghealth.service.AppAnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 社保一键通通知公告
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/appannouncement")
@Api(tags="社保一键通通知公告")
public class AppAnnouncementController {
    @Autowired
    private AppAnnouncementService appAnnouncementService;

    /**
     * 列表
     */
    @Login
    @PostMapping("/list")
    @ApiOperation("获取通知政策信息")
    public R list(@RequestBody Map<String, Object> params){
        PageUtils page = appAnnouncementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @Login
    @RequestMapping("/info/{announcementId}")
    @ApiOperation("通过id获取通知政策信息")
    public R info(@PathVariable("announcementId") Integer announcementId){
        AppAnnouncementEntity appAnnouncement = appAnnouncementService.getById(announcementId);

        return R.ok().put("appAnnouncement", appAnnouncement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ApiOperation("新增通知政策信息")
    public R save(@RequestBody AppAnnouncementEntity appAnnouncement){
        appAnnouncementService.save(appAnnouncement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ApiOperation("修改通知政策信息")
    public R update(@RequestBody AppAnnouncementEntity appAnnouncement){
        ValidatorUtils.validateEntity(appAnnouncement);
        appAnnouncementService.updateById(appAnnouncement);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ApiOperation("删除通知政策信息")
    public R delete(@RequestBody Integer[] announcementIds){
        appAnnouncementService.removeByIds(Arrays.asList(announcementIds));

        return R.ok();
    }

}

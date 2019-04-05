package com.pukanghealth.service.impl;

import com.pukanghealth.common.exception.RRException;
import com.pukanghealth.dao.AppAnnouncementDao;
import com.pukanghealth.entity.AppAnnouncementEntity;
import com.pukanghealth.service.AppAnnouncementService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appAnnouncementService")
public class AppAnnouncementServiceImpl extends ServiceImpl<AppAnnouncementDao, AppAnnouncementEntity> implements AppAnnouncementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AppAnnouncementEntity> queryWrapper = new QueryWrapper<>();
        //announcementType 0：通知公告 1：政策查询
        Object type = params.get("announcementType");
        if (null != type && !"".equals(type)){
            queryWrapper.eq("announcement_type", type);
        }else {
            throw new RRException(500, "announcementType不能为空");
        }
        queryWrapper.orderByDesc("create_time");
        IPage<AppAnnouncementEntity> page = this.page(
                new Query<AppAnnouncementEntity>().getPage(params),queryWrapper);

        return new PageUtils(page);
    }

}

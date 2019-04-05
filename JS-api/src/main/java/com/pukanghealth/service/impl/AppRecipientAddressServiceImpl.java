package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppRecipientAddressDao;
import com.pukanghealth.entity.AppRecipientAddressEntity;
import com.pukanghealth.service.AppRecipientAddressService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appRecipientAddressService")
public class AppRecipientAddressServiceImpl extends ServiceImpl<AppRecipientAddressDao, AppRecipientAddressEntity> implements AppRecipientAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppRecipientAddressEntity> page = this.page(
                new Query<AppRecipientAddressEntity>().getPage(params),
                new QueryWrapper<AppRecipientAddressEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean updateRecipientDeletedById(Integer[] ids) {
        return this.baseMapper.updateRecipientDeletedById(ids);
    }

}

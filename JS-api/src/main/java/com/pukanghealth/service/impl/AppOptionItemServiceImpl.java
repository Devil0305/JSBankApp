package com.pukanghealth.service.impl;

import com.pukanghealth.ao.OptionItemAO;
import com.pukanghealth.dao.AppOptionItemDao;
import com.pukanghealth.entity.AppOptionItemEntity;
import com.pukanghealth.service.AppOptionItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appOptionItemService")
public class AppOptionItemServiceImpl extends ServiceImpl<AppOptionItemDao, AppOptionItemEntity> implements AppOptionItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppOptionItemEntity> page = this.page(
                new Query<AppOptionItemEntity>().getPage(params),
                new QueryWrapper<AppOptionItemEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> selectByOptionTeamId(Integer optionTeamId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<AppOptionItemEntity> ls = this.list(new QueryWrapper<AppOptionItemEntity>().eq("option_team_id",optionTeamId));
        List<String> sls = new ArrayList<String>();
        for (AppOptionItemEntity poti : ls) {
            if (sls.contains(poti.getOptionGroupId())) {
                continue;
            } else {
                sls.add(poti.getOptionGroupId());
            }
        }

        int n = sls.size();
        ArrayList[] lists = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList();
        }
        int m = 0;
        for (String ogi : sls) {
            for (AppOptionItemEntity poti : ls) {
                if (poti.getOptionGroupId().equals(ogi)) {
                    lists[m].add(poti);
                }
            }
            map.put("optionTeamList" + m, lists[m]);
            m++;
        }
        return map;
    }

}

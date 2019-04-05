package com.pukanghealth.service.impl;



import com.pukanghealth.bo.MetroLineWithStationBO;
import com.pukanghealth.dao.AppMetroLineDao;
import com.pukanghealth.entity.AppMetroLineEntity;
import com.pukanghealth.entity.AppMetroStationEntity;
import com.pukanghealth.service.AppMetroLineService;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appMetroLineService")
public class AppMetroLineServiceImpl extends ServiceImpl<AppMetroLineDao, AppMetroLineEntity> implements AppMetroLineService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppMetroLineEntity> page = this.page(
                new Query<AppMetroLineEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> getMetroStationsByCity(int city) {
        Map<String, Object> map = new HashMap<>();
        List<MetroLineWithStationBO> lineWithStationList = this.baseMapper.getMetroStationsByCity(city);
        if (lineWithStationList.size() == 0) {
            map.put("latestTime", null);
            map.put("lineWithStationList", lineWithStationList);
        } else {
            List<Date> dateList = new ArrayList<>();
            List<MetroLineWithStationBO> lineWithStations = new ArrayList<>();
            for (MetroLineWithStationBO withStation : lineWithStationList) {
                if (null != withStation.getStations() && withStation.getStations().size() != 0) {
                    for (AppMetroStationEntity station : withStation.getStations()) {
                        if (null != station.getMetroStationUpdateTime()) {
                            dateList.add(station.getMetroStationUpdateTime());
                        } else {
                            dateList.add(station.getMetroStationCreateTime());
                        }
                    }
                    lineWithStations.add(withStation);
                }
            }
            Collections.sort(dateList, Collections.reverseOrder());
            map.put("latestTime", dateList.get(0));
            map.put("lineWithStationList", lineWithStations);
        }
        return map;
    }

}

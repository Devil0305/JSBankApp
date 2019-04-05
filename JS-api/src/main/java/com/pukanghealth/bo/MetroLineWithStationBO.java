package com.pukanghealth.bo;

import com.pukanghealth.entity.AppMetroLineEntity;
import com.pukanghealth.entity.AppMetroStationEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author:LiWang
 * @Description:
 * @Date:Created in 11:06 2019/4/2
 * @Modified By:
 */
@Data
public class MetroLineWithStationBO extends AppMetroLineEntity {
    private List<AppMetroStationEntity> stations;
}

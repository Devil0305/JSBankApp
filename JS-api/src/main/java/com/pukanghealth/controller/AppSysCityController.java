package com.pukanghealth.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pukanghealth.annotation.Login;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppSysCityEntity;
import com.pukanghealth.service.AppSysCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pukanghealth.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 城市原始表
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/appsyscity")
@Api(tags = "获取城市信息接口")
public class AppSysCityController {
    private static Logger logger = LoggerFactory.getLogger(AppSysCityController.class);
    @Autowired
    private AppSysCityService appSysCityService;

    /**
     * functionCode:03
     * 获取指定城市的区信息
     *
     * @param cityId,城市名称或id
     * @return 区信息
     */
    @Login
    @ApiOperation("通过城市id获取城市区县信息")
    @PostMapping(value = "/getDistrictListWithCity")
    @ResponseBody
    public R getDistrictListWithCity(@RequestParam("cityId") String cityId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == cityId || "".equals(cityId)) {
            String staticTerminal = request.getParameter("staticTerminal");
            String staticVersion = request.getParameter("staticVersion");
            String userId = (String) request.getAttribute("userId");
            logger.error("api/appsyscity/getDistrictListWithCity,cityId不能为空010903170,参数信息cityId=" + cityId + ",用户信息:userId=" + userId +
                    ",终端为" + staticTerminal + ",版本为" + staticVersion);
            map.put("errorCode", 17);
            map.put("errorMessage", "cityId不能为空(错误代码:010903170)");
            return R.error(map);
        }

        int city;
        try {
            city = Integer.valueOf(cityId);
        } catch (Exception e) {
            city = appSysCityService.getCityIdByCityName(cityId);
        }
        List<AppSysCityEntity> districtList = appSysCityService.list(new QueryWrapper<AppSysCityEntity>().eq("city_id",city));
        if (null != districtList) {
            map.put("districtList", districtList);
        } else {
            String staticTerminal = request.getParameter("staticTerminal");
            String staticVersion = request.getParameter("staticVersion");
            String userId = (String) request.getAttribute("userId");
            logger.error("api/appsyscity/getDistrictListWithCity,查询失败,参数信息:cityId=" + cityId + ",返回值districtList=null,用户信息:userId=" + userId +
                    ",终端为" + staticTerminal + ",版本为" + staticVersion);
            map.put("errorCode", 1);
            map.put("errorMessage", "查询失败");
        }
        return R.ok(map);
    }


}

package com.pukanghealth.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.pukanghealth.annotation.Login;
import com.pukanghealth.common.utils.JsonUtils;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppMetroLineEntity;
import com.pukanghealth.service.AppMetroLineService;
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
 * 
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/appmetroline")
@Api(tags = "地铁站信息")
public class AppMetroLineController {
    private static Logger logger = LoggerFactory.getLogger(AppMetroLineController.class);
    @Autowired
    private AppMetroLineService appMetroLineService;
    @Autowired
    private AppSysCityService appSysCityService;

    /**
     * functionCode:04
     * 查询指定城市所有地铁站
     *
     * @param cityId,城市代号或城市名称
     * @return 地铁信息
     */
    @Login
    @GetMapping(value = "/getMetroStationsWithCity")
    @ResponseBody
    @ApiOperation("查询指定城市所有地铁站")
    public R getMetroStationsWithCity(@RequestParam("cityId") String cityId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == cityId || "".equals(cityId) || "0".equals(cityId)) {
            String staticTerminal = request.getParameter("staticTerminal");
            String staticVersion = request.getParameter("staticVersion");
            String userId = (String) request.getAttribute("userId");
            logger.error("api/appmetroline/getMetroStationsWithCity,cityId不能为空010904170,参数信息cityId=" + cityId + ",用户信息:userId=" + userId +
                    ",终端为" + staticTerminal + ",版本为" + staticVersion);
            map.put("errorCode", 17);
            map.put("errorMessage", "cityId不能为空(错误代码:010904170)");
            return R.error(map);
        }

        int city;
        try {
            city = Integer.valueOf(cityId);
        } catch (Exception e) {
            city = appSysCityService.getCityIdByCityName(cityId);
        }
        Map<String, Object> retMap = appMetroLineService.getMetroStationsByCity(city);
        if (retMap.size() > 0) {
            map.put("latestTime", retMap.get("latestTime"));
            map.put("lineWithStationList", retMap.get("lineWithStationList"));
        } else {
            String staticTerminal = request.getParameter("staticTerminal");
            String staticVersion = request.getParameter("staticVersion");
            String userId = (String) request.getAttribute("userId");
            logger.error("api/appmetroline/getMetroStationsWithCity,查询失败,参数信息:cityId=" + cityId + ",返回值retMap=" + JsonUtils.toJson(retMap) + ",用户信息:userId=" + userId +
                    ",终端为" + staticTerminal + ",版本为" + staticVersion);
            map.put("errorCode", 1);
            map.put("errorMessage", "查询失败");
        }
        return R.ok(map);
    }


}

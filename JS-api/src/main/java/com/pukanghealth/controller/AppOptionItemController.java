package com.pukanghealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pukanghealth.annotation.Login;
import com.pukanghealth.ao.OptionItemAO;
import com.pukanghealth.common.utils.R;
import com.pukanghealth.common.validator.ValidatorUtils;
import com.pukanghealth.entity.AppOptionItemEntity;
import com.pukanghealth.service.AppOptionItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 所有下拉框原始表
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@RestController
@RequestMapping("api/appoptionitem")
@Api(tags = "下拉框字典表")
public class AppOptionItemController {
    private static Logger logger = LoggerFactory.getLogger(AppOptionItemController.class);
    @Autowired
    private AppOptionItemService appOptionItemService;

    /**
     * 列表
     */
    @Login
    @PostMapping("/list")
    @ApiOperation("获取类型列表")
    @ResponseBody
    public R list(@RequestParam("optionTeamId") Integer optionTeamId, HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == optionTeamId) {
            String staticTerminal = request.getParameter("staticTerminal");
            String staticVersion = request.getParameter("staticVersion");
            String userId = (String) request.getAttribute("userId");
            logger.error("api/appoptionitem/list,optionTeamId不能为空,参数信息optionTeamId=null,用户信息:userId=" + userId +
                    ",终端为" + staticTerminal + ",版本为" + staticVersion);
            map.put("errorCode", 17);
            map.put("errorMessage", "optionTeamId不能为空(错误代码:010901170)");
            return R.error(map);
        }
        return R.ok(appOptionItemService.selectByOptionTeamId(optionTeamId));
    }

    /**
     * 通过groupId和类型查询字典表信息
     *
     * @param
     * @return optionItems
     */
    @Login
    @PostMapping(value = "/getItemListByParams")
    @ResponseBody
    @ApiOperation("获取筛选列表")
    public R getItemListByParams(@RequestBody OptionItemAO optionItemAO, HttpServletRequest request) {
        ValidatorUtils.validateEntity(optionItemAO);
        Map<String, Object> map = new HashMap<>();
        List<AppOptionItemEntity> optionItems = appOptionItemService.list(new QueryWrapper<AppOptionItemEntity>().eq("option_module_id",optionItemAO.getOptionModuleId()).eq("option_group_id",optionItemAO.getGroupId()));
        if (null != optionItems) {
            map.put("optionItems", optionItems);
            map.put("errorCode", 0);
            map.put("errorMessage", "查询成功");
        } else {
            String staticTerminal = request.getParameter("staticTerminal");
            String staticVersion = request.getParameter("staticVersion");
            String userId = (String) request.getAttribute("userId");
            logger.error("system/getItemListByParams,查询失败,参数信息:groupId=" + optionItemAO.getGroupId() + ",moduleId=" + optionItemAO.getOptionModuleId() + ",返回值optionItems=null,用户信息:userId=" + userId +
                    ",终端为" + staticTerminal + ",版本为" + staticVersion);
            map.put("errorCode", 1);
            map.put("errorMessage", "查询失败");
        }
        return R.ok(map);
    }


}

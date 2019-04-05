package com.pukanghealth.controller;

import com.pukanghealth.common.utils.R;
import com.pukanghealth.service.AppMerchantImportService;
import com.pukanghealth.service.AppNetworkImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 导入Excel文件
 *
 * @author liuKang
 */
@RestController
@RequestMapping("/api")
@Api(tags = "导入Excel文件")
public class ApiImportController {

    @Autowired
    private AppNetworkImportService appNetworkImportService;

    @Autowired
    private AppMerchantImportService appMerchantImportService;

    @PostMapping("/uploadNetworkExcel")
    @ApiOperation("网点表格")
    public R uploadNetworkExcel(@RequestParam("file") MultipartFile file) {
        Boolean flag = appNetworkImportService.batchImportNetwork(file);
        if (flag) {
            return R.ok();
        } else {
            return R.error(500, "表格数据为空");
        }

    }

    @PostMapping("/uploadMerchantExcel")
    @ApiOperation("合作商户表格")
    public R uploadMerchantExcel(@RequestParam("file") MultipartFile file) {
        Boolean flag = appMerchantImportService.batchImportMerchant(file);
        if (flag){
            return R.ok();
        }else {
            return R.error(500,"表格数据为空");
        }
    }
}

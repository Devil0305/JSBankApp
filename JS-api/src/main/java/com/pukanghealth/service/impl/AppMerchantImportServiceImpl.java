package com.pukanghealth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.exception.RRException;
import com.pukanghealth.dao.AppMerchantDao;
import com.pukanghealth.entity.AppMerchantEntity;
import com.pukanghealth.service.AppMerchantImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppMerchantImportServiceImpl extends ServiceImpl<AppMerchantDao, AppMerchantEntity> implements AppMerchantImportService {
    private Logger logger = LoggerFactory.getLogger(AppNetworkImportServiceImpl.class);

    @Override
    public Boolean batchImportMerchant(MultipartFile file) {
        boolean notNull = false;
        String fileName = file.getOriginalFilename();
        List<AppMerchantEntity> appMerchantEntityList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new RRException(500, "上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        Sheet sheet = null;
        try {
            InputStream is = file.getInputStream();
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            sheet = wb.getSheetAt(0);
            if (sheet != null) {
                notNull = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppMerchantEntity appMerchantEntity;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            appMerchantEntity = new AppMerchantEntity();
            if (row.getCell(0).getCellTypeEnum() != CellType.NUMERIC) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,merchant_type_id格式不正确或未填写)");
            }
            Double merchantTypeIdValue = row.getCell(0).getNumericCellValue();
            Integer merchantTypeId = merchantTypeIdValue.intValue();

            if (row.getCell(1).getCellTypeEnum() != CellType.STRING) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,merchant_name格式不正确或未填写)");
            }
            String merchantName = row.getCell(1).getStringCellValue();
            if (null == merchantName || merchantName.isEmpty()) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,merchant_name不能为空)");
            }
            row.getCell(2).setCellType(CellType.STRING);
            String merchantCode = row.getCell(2).getStringCellValue();
            if (null == merchantCode || merchantCode.isEmpty()) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,merchant_code不能为空)");
            }

            String merchantDistance = "";
            if (row.getCell(3) != null) {
                row.getCell(3).setCellType(CellType.STRING);
                merchantDistance = row.getCell(3).getStringCellValue();
            }
            row.getCell(4).setCellType(CellType.STRING);
            String merchantSign = row.getCell(4).getStringCellValue();

            BigDecimal merchantLatitude = BigDecimal.valueOf(row.getCell(5).getNumericCellValue());

            BigDecimal merchantLongitude = BigDecimal.valueOf(row.getCell(6).getNumericCellValue());

            row.getCell(7).setCellType(CellType.STRING);
            String merchantPhone = row.getCell(7).getStringCellValue();

            row.getCell(8).setCellType(CellType.STRING);
            String merchantMobile = row.getCell(8).getStringCellValue();

            row.getCell(9).setCellType(CellType.STRING);
            String merchantLogo = row.getCell(9).getStringCellValue();

            row.getCell(10).setCellType(CellType.STRING);
            String merchantAddress = row.getCell(10).getStringCellValue();

            row.getCell(11).setCellType(CellType.STRING);
            String merchantShortName = row.getCell(11).getStringCellValue();

            Double merchantCityIdValue = row.getCell(12).getNumericCellValue();

            Integer merchantCityId = merchantCityIdValue.intValue();

            Double merchantProvinceIdValue = row.getCell(13).getNumericCellValue();

            Integer merchantProvinceId = merchantProvinceIdValue.intValue();

            Double merchantDistrictIdValue = row.getCell(14).getNumericCellValue();

            Integer merchantDistrictId = merchantDistrictIdValue.intValue();

            Double merchantDeletedValue = row.getCell(15).getNumericCellValue();

            Integer merchantDeleted = merchantDeletedValue.intValue();

            Double merchantShowValue = row.getCell(16).getNumericCellValue();

            Integer merchantShow = merchantShowValue.intValue();

            String merchantDesc = row.getCell(17).getStringCellValue();

            Date caratDate;
            if (row.getCell(18).getCellTypeEnum() != CellType.NUMERIC) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,创建日期格式不正确或未填写)");
            } else {
                caratDate = row.getCell(18).getDateCellValue();
            }

            Date update;
            if (row.getCell(19).getCellTypeEnum() != CellType.NUMERIC) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,更新日期格式不正确或未填写)");
            } else {
                update = row.getCell(19).getDateCellValue();
            }
            appMerchantEntity.setMerchantAddress(merchantAddress);
            appMerchantEntity.setMerchantCityId(merchantCityId);
            appMerchantEntity.setMerchantCode(merchantCode);
            appMerchantEntity.setMerchantCreateTime(caratDate);
            appMerchantEntity.setMerchantDeleted(merchantDeleted);
            appMerchantEntity.setMerchantDesc(merchantDesc);
            appMerchantEntity.setMerchantDistance(merchantDistance);
            appMerchantEntity.setMerchantDistrictId(merchantDistrictId);
            appMerchantEntity.setMerchantLatitude(merchantLatitude);
            appMerchantEntity.setMerchantLogo(merchantLogo);
            appMerchantEntity.setMerchantLongitude(merchantLongitude);
            appMerchantEntity.setMerchantMobile(merchantMobile);
            appMerchantEntity.setMerchantName(merchantName);
            appMerchantEntity.setMerchantPhone(merchantPhone);
            appMerchantEntity.setMerchantProvinceId(merchantProvinceId);
            appMerchantEntity.setMerchantShortName(merchantShortName);
            appMerchantEntity.setMerchantShow(merchantShow);
            appMerchantEntity.setMerchantSign(merchantSign);
            appMerchantEntity.setMerchantTypeId(merchantTypeId);
            appMerchantEntity.setMerchantUpdateTime(update);
            appMerchantEntityList.add(appMerchantEntity);
        }
        for (AppMerchantEntity appMerchantEntityResord : appMerchantEntityList) {
            String merchantCode = appMerchantEntityResord.getMerchantCode();

            Integer count = baseMapper.selectCount(new QueryWrapper<AppMerchantEntity>().eq("merchant_code", merchantCode));
            if (count == 0) {
                baseMapper.insert(appMerchantEntityResord);
                logger.info("插入" + appMerchantEntityResord);
            } else {
                baseMapper.update(appMerchantEntityResord, new QueryWrapper<AppMerchantEntity>().eq("merchant_code", merchantCode));
                logger.info("更新" + appMerchantEntityResord);
            }
        }
        return notNull;
    }
}

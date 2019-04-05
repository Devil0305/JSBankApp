package com.pukanghealth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.exception.RRException;
import com.pukanghealth.dao.AppNetworkDao;
import com.pukanghealth.entity.AppNetworkEntity;
import com.pukanghealth.service.AppNetworkImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
public class AppNetworkImportServiceImpl extends ServiceImpl<AppNetworkDao, AppNetworkEntity> implements AppNetworkImportService {

    private Logger logger = LoggerFactory.getLogger(AppNetworkImportServiceImpl.class);

    @Override
    public Boolean batchImportNetwork(MultipartFile file) {
        boolean notNull = false;
        String fileName = file.getOriginalFilename();
        List<AppNetworkEntity> appNetworkEntityList = new ArrayList<>();
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
        AppNetworkEntity appNetworkEntity;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            appNetworkEntity = new AppNetworkEntity();
            if (row.getCell(0).getCellTypeEnum() != CellType.NUMERIC) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,network_type_id格式不正确或未填写)");
            }
            Double networkTypeIdValue = row.getCell(0).getNumericCellValue();
            Integer networkTypeId = networkTypeIdValue.intValue();
            if (row.getCell(1).getCellTypeEnum() != CellType.NUMERIC) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,network_type格式不正确或未填写)");
            }
            Double networkTypeValue = row.getCell(1).getNumericCellValue();
            Integer networkType = networkTypeValue.intValue();
            if (row.getCell(2).getCellTypeEnum() != CellType.STRING) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,network_name格式不正确或未填写)");
            }
            String networkName = row.getCell(2).getStringCellValue();
            if (null == networkName || networkName.isEmpty()) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,network_name不能为空)");
            }
            row.getCell(3).setCellType(CellType.STRING);
            String networkCode = row.getCell(3).getStringCellValue();
            if (null == networkCode || networkCode.isEmpty()) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,network_code不能为空)");
            }
            String networkDistance = "";
            if (null != row.getCell(4)) {
                row.getCell(4).setCellType(CellType.STRING);
                networkDistance = row.getCell(4).getStringCellValue();
            }

            row.getCell(5).setCellType(CellType.STRING);
            String networkSign = row.getCell(5).getStringCellValue();

            BigDecimal networkLatitude = BigDecimal.valueOf(row.getCell(6).getNumericCellValue());

            BigDecimal networkLongitude = BigDecimal.valueOf(row.getCell(7).getNumericCellValue());

            row.getCell(8).setCellType(CellType.STRING);
            String networkPhone = row.getCell(8).getStringCellValue();

            row.getCell(9).setCellType(CellType.STRING);
            String networkMobile = row.getCell(9).getStringCellValue();

            row.getCell(10).setCellType(CellType.STRING);
            String networkLogo = row.getCell(10).getStringCellValue();

            row.getCell(11).setCellType(CellType.STRING);
            String networkAddress = row.getCell(11).getStringCellValue();

            row.getCell(12).setCellType(CellType.STRING);
            String networkShortName = row.getCell(12).getStringCellValue();

            Double networkCityIdValue = row.getCell(13).getNumericCellValue();

            Integer networkCityId = networkCityIdValue.intValue();

            Double networkProvinceIdValue = row.getCell(14).getNumericCellValue();

            Integer networkProvinceId = networkProvinceIdValue.intValue();

            Double networkDistrictIdValue = row.getCell(15).getNumericCellValue();

            Integer networkDistrictId = networkDistrictIdValue.intValue();

            Double networkDeletedValue = row.getCell(16).getNumericCellValue();

            Integer networkDeleted = networkDeletedValue.intValue();

            Double networkShowValue = row.getCell(17).getNumericCellValue();

            Integer networkShow = networkShowValue.intValue();

            String networkDesc = row.getCell(18).getStringCellValue();

            Date caratDate;
            if (row.getCell(19).getCellTypeEnum() != CellType.NUMERIC) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,创建日期格式不正确或未填写)");
            } else {
                caratDate = row.getCell(19).getDateCellValue();
            }

            Date update;
            if (row.getCell(20).getCellTypeEnum() != CellType.NUMERIC) {
                throw new RRException(500, "导入失败(第" + (r + 1) + "行,更新日期格式不正确或未填写)");
            } else {
                update = row.getCell(20).getDateCellValue();
            }
            appNetworkEntity.setNetworkTypeId(networkTypeId);
            appNetworkEntity.setNetworkAddress(networkAddress);
            appNetworkEntity.setNetworkCityId(networkCityId);
            appNetworkEntity.setNetworkCode(networkCode);
            appNetworkEntity.setNetworkCreateTime(caratDate);
            appNetworkEntity.setNetworkDeleted(networkDeleted);
            appNetworkEntity.setNetworkDesc(networkDesc);
            appNetworkEntity.setNetworkDistance(networkDistance);
            appNetworkEntity.setNetworkDistrictId(networkDistrictId);
            appNetworkEntity.setNetworkLatitude(networkLatitude);
            appNetworkEntity.setNetworkLogo(networkLogo);
            appNetworkEntity.setNetworkLongitude(networkLongitude);
            appNetworkEntity.setNetworkMobile(networkMobile);
            appNetworkEntity.setNetworkName(networkName);
            appNetworkEntity.setNetworkPhone(networkPhone);
            appNetworkEntity.setNetworkProvinceId(networkProvinceId);
            appNetworkEntity.setNetworkShortName(networkShortName);
            appNetworkEntity.setNetworkShow(networkShow);
            appNetworkEntity.setNetworkSign(networkSign);
            appNetworkEntity.setNetworkType(networkType);
            appNetworkEntity.setNetworkUpdateTime(update);
            appNetworkEntityList.add(appNetworkEntity);
        }
        for (AppNetworkEntity appNetworkEntityResord : appNetworkEntityList) {
            String networkCode = appNetworkEntityResord.getNetworkCode();
            Integer count = baseMapper.selectCount(new QueryWrapper<AppNetworkEntity>().eq("network_code", networkCode));
            if (count == 0) {
                baseMapper.insert(appNetworkEntityResord);
                logger.info("插入" + appNetworkEntityResord);
            } else {
                baseMapper.update(appNetworkEntityResord, new QueryWrapper<AppNetworkEntity>().eq("network_code", networkCode));
                logger.info("更新" + appNetworkEntityResord);
            }
        }
        return notNull;
    }

}

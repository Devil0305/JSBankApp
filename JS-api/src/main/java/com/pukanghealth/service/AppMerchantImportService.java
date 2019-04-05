package com.pukanghealth.service;

import org.springframework.web.multipart.MultipartFile;

public interface AppMerchantImportService {
    Boolean batchImportMerchant(MultipartFile file);
}

package com.pukanghealth.service;

import org.springframework.web.multipart.MultipartFile;

public interface AppNetworkImportService {
    Boolean batchImportNetwork(MultipartFile file);
}

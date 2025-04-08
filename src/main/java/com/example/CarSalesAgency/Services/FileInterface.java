package com.example.CarSalesAgency.Services;

import com.example.CarSalesAgency.Entities.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileInterface {

    ResponseEntity<?> uploadFile(MultipartFile fileToBeUploaded);
    ResponseEntity<?> downloadFile(String filename);
    File getFileById(Long fileId);
}

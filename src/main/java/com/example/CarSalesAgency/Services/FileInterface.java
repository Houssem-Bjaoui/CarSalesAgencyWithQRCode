package com.example.CarSalesAgency.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileInterface {

    ResponseEntity<?> uploadFile(MultipartFile fileToBeUploaded);
    ResponseEntity<?> downloadFile(String filename);
}

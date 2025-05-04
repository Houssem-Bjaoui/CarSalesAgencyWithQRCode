package com.example.CarSalesAgency.Controller;


import com.example.CarSalesAgency.Entities.File;
import com.example.CarSalesAgency.ServiceImplement.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File savedFile = fileService.saveUploadedFile(file);

            Map<String, Object> response = new HashMap<>();
            response.put("id", savedFile.getId());
            response.put("filename", savedFile.getFilename());
            response.put("message", "Upload successful");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IOException e) {
            // Log l'erreur
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        Optional<File> optionalFile = fileService.downloadFile(filename);
        if (optionalFile.isPresent()) {
            File file = optionalFile.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    // Pas besoin de "attachment" si tu veux l’afficher dans <img>
                    .body(file.getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }



    @PostMapping("/upload-multiple")
    public ResponseEntity<List<Map<String, Object>>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            List<Map<String, Object>> responses = new ArrayList<>();
            for (MultipartFile file : files) {
                File savedFile = fileService.saveUploadedFile(file);
                Map<String, Object> response = new HashMap<>();
                response.put("id", savedFile.getId());
                response.put("filename", savedFile.getFilename());
                responses.add(response);
            }
            return new ResponseEntity<>(responses, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



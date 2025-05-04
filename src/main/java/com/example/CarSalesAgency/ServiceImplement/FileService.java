package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.File;
import com.example.CarSalesAgency.Entities.User;
import com.example.CarSalesAgency.Repository.FileRepository;
import com.example.CarSalesAgency.Repository.UserRepository;
import com.example.CarSalesAgency.Services.FileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileService implements FileInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    @Autowired
    public FileService(FileRepository fileRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    // Ajoutez cette méthode dans la classe FileService
    public File saveUploadedFile(MultipartFile file) throws IOException {
        File newFile = new File();
        newFile.setFilename(file.getOriginalFilename());
        newFile.setContentType(file.getContentType());
        newFile.setSize(file.getSize());
        newFile.setData(file.getBytes());
        return fileRepository.save(newFile);
    }

    // Modifiez la méthode existante uploadFile
    @Override
    public ResponseEntity<?> uploadFile(MultipartFile fileToBeUploaded) {
        try {
            if (!this.fileExists(fileToBeUploaded.getOriginalFilename())) {
                File savedFile = this.saveUploadedFile(fileToBeUploaded); // Utilisation de la nouvelle méthode
                return new ResponseEntity<>(savedFile, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("File already exists", HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOGGER.error("Error saving file: " + e.getMessage(), e);
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<File> downloadFile(String filename) {
        return fileRepository.findFileByFilename(filename);
    }




    public List<File> getFilesByIds(List<Long> fileIds) {
        return fileRepository.findAllById(fileIds);
    }

    public boolean fileExists(String filename) {
        // Vérifie si un fichier avec le même nom existe déjà dans la base de données
        return fileRepository.findFileByFilename(filename).isPresent();
    }

    public File getFileById(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public List<File> saveMultipleFiles(MultipartFile[] files) throws IOException {
        List<File> savedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            savedFiles.add(saveUploadedFile(file));
        }
        return savedFiles;
    }




}




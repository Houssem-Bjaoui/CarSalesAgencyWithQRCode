package com.example.CarSalesAgency.ServiceImplement;

import com.example.CarSalesAgency.Entities.File;
import com.example.CarSalesAgency.Repository.FileRepository;
import com.example.CarSalesAgency.Services.FileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService implements FileInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
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
    public ResponseEntity<?> downloadFile(String filename) {
        Optional<File> optionalFile = this.fileRepository.findFileByFilename(filename);
        if(optionalFile.isPresent()) {
            File file = optionalFile.get();
            return new ResponseEntity<>(file, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public boolean fileExists(String filename) {
        // Vérifie si un fichier avec le même nom existe déjà dans la base de données
        return fileRepository.findFileByFilename(filename).isPresent();
    }

    public File getFileById(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

}




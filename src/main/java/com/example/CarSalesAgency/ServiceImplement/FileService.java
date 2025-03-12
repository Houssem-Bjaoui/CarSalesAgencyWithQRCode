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

    @Override
    public ResponseEntity<?> uploadFile(MultipartFile fileToBeUploaded) {
        try {
            // Vérifie si le fichier existe déjà dans la base de données
            if (!this.fileExists(fileToBeUploaded.getOriginalFilename())) {
                // Crée un nouvel objet File
                File file = new File();
                file.setFilename(fileToBeUploaded.getOriginalFilename());
                file.setContentType(fileToBeUploaded.getContentType());
                file.setSize(fileToBeUploaded.getSize());
                file.setData(fileToBeUploaded.getBytes());

                // Sauvegarde le fichier dans la base de données
                fileRepository.save(file);

                // Retourne une réponse de succès
                return new ResponseEntity<>("File Uploaded Successfully: " + file.getFilename(), HttpStatus.CREATED);
            } else {
                // Retourne une réponse de conflit si le fichier existe déjà
                return new ResponseEntity<>("File already exists", HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOGGER.error("Error getting data from file: " + e.getMessage(), e);
            // Retourne une réponse d'erreur si une exception est levée
            return new ResponseEntity<>("Error when getting data from file", HttpStatus.BAD_REQUEST);
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


    private boolean fileExists(String filename) {
        // Vérifie si un fichier avec le même nom existe déjà dans la base de données
        return fileRepository.findFileByFilename(filename).isPresent();
    }
}




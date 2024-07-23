package com.enviro.assessment.grad001.goodnesschauke.controller;


import com.enviro.assessment.grad001.goodnesschauke.entity.FileData;
import com.enviro.assessment.grad001.goodnesschauke.service.FileDataService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api")
public class FileDataController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FileDataController.class);

    @Autowired
    private FileDataService fileDataService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Received file upload request for file: {}", file.getOriginalFilename());
        try {
            FileData fileData = fileDataService.storeFile(file);
            logger.info("File uploaded successfully: {}", fileData.getFileName());
            return ResponseEntity.ok(new UploadFileResponse(fileData.getId(), "File uploaded successfully"));
        } catch (Exception e) {
            logger.error("Error uploading file: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
        }
    }

    @GetMapping("/results/{fileId}")
    public ResponseEntity<?> getFileData(@PathVariable Long fileId) {
        logger.info("Received request for file data with ID: {}", fileId);
        try {
            FileData fileData = fileDataService.getFileData(fileId);
            logger.info("Retrieved file data for file: {}", fileData.getFileName());
            return ResponseEntity.ok(fileData.getProcessedData());
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @Getter
    @Setter
    @AllArgsConstructor
    static class UploadFileResponse {
        private Long fileId;
        private String message;
    }

}

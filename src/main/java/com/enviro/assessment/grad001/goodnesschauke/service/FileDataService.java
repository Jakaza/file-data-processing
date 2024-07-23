package com.enviro.assessment.grad001.goodnesschauke.service;

import com.enviro.assessment.grad001.goodnesschauke.entity.FileData;
import com.enviro.assessment.grad001.goodnesschauke.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class FileDataService {

    @Autowired
    private FileDataRepository fileDataRepository;

    public FileData storeFile(MultipartFile file) throws IOException {
        FileData fileData = new FileData();
        fileData.setFileName(file.getOriginalFilename());
        String content;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            content = reader.lines().collect(Collectors.joining("\n"));
        }
        fileData.setFileContent(content);
        String processedData = processFileContent(content);
        fileData.setProcessedData(processedData);

        return fileDataRepository.save(fileData);
    }

    public FileData getFileData(Long id) throws FileNotFoundException {
        return fileDataRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File not found with id " + id));
    }

    private String processFileContent(String content) {
        return content;
    }
}

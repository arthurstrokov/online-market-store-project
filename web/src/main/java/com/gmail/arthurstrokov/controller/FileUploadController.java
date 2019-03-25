package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.service.UploadService;
import com.gmail.arthurstrokov.service.properties.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final PageProperties pageProperties;
    private final UploadService uploadService;
    private final FileProperties fileProperties;

    @Autowired
    public FileUploadController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("uploadService") UploadService uploadService,
            @Qualifier("fileProperties") FileProperties fileProperties
    ) {
        this.pageProperties = pageProperties;
        this.uploadService = uploadService;
        this.fileProperties = fileProperties;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS')")
    public String displayForm() {
        return pageProperties.getFileUploadPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS')")
    public String uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            ModelMap modelMap
    ) throws IOException {
        modelMap.addAttribute("file", file);
        file.transferTo(new File(fileProperties.getItemsFilePath()));
        uploadService.upload();
        return pageProperties.getFileUploadPagePath();
    }
}

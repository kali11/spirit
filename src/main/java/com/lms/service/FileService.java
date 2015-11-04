package com.lms.service;

import org.springframework.web.multipart.MultipartFile;

import com.lms.model.dict.FileType;
import com.lms.model.entity.File;

public interface FileService {

    public Long saveFile(MultipartFile file, FileType type);

    public void deleteFile(Long id);

    public File get(Long id);
}

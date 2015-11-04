package org.spirit.service;

import org.spirit.model.dict.FileType;
import org.springframework.web.multipart.MultipartFile;

import org.spirit.model.entity.File;

public interface FileService {

    public Long saveFile(MultipartFile file, FileType type);

    public void deleteFile(Long id);

    public File get(Long id);
}

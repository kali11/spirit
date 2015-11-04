package com.lms.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lms.model.dao.FileDao;
import com.lms.model.dict.FileType;
import com.lms.model.entity.File;
import com.lms.service.FileService;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private Environment env;

    @Autowired
    private FileDao fileDao;

    @Override
    public Long saveFile(MultipartFile file, FileType type) {
        String uuid = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String path = type + "/" + uuid;
        try {
            FileCopyUtils.copy(file.getBytes(), new FileOutputStream(env.getProperty("filesPath") + path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fileToSave = new File();
        fileToSave.setPath(path);
        fileToSave.setOriginalName(file.getOriginalFilename());
        fileToSave.setUuid(uuid);
        fileToSave.setSize(file.getSize());
        fileDao.save(fileToSave);
        return fileToSave.getId();
    }

    @Override
    public void deleteFile(Long id) {
        File file = fileDao.find(id);
        try {
            Files.deleteIfExists(Paths.get(env.getProperty("filesPath") + file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileDao.remove(file);
    }

    @Override
    public File get(Long id) {
        return fileDao.find(id);
    }

}

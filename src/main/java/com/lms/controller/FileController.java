package com.lms.controller;

import com.lms.model.dict.FileType;
import com.lms.model.entity.File;
import com.lms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<FileSystemResource> getFile(@PathVariable Long id) {
        File file = fileService.get(id);
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        respHeaders.setContentDispositionFormData("inline", file.getOriginalName());
        respHeaders.setContentLength(file.getSize());

        String location = env.getProperty("filesPath") + file.getPath();
        FileSystemResource resource = new FileSystemResource(new java.io.File(location));
        return new ResponseEntity<>(resource, respHeaders, HttpStatus.OK);
    }

    // TODO: ograniczyć wielkosc przesłanych plików oraz rozszerzenia
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ajaxUploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "oldFileId", required = false) Long oldFileId, HttpServletRequest request) {
        Long fileId = fileService.saveFile(file, FileType.IMAGE);
        Map<String, Object> jsonResult = new HashMap<>();
        jsonResult.put("link", request.getContextPath() + "/files/" + fileId);
        jsonResult.put("fileName", file.getOriginalFilename());
        jsonResult.put("fileId", fileId);
        return jsonResult;
    }

    @RequestMapping(value = "/delete/image", method = RequestMethod.POST)
    public ModelAndView ajaxDeleteImage(@RequestParam("src") String fileUrl) {
        Long id = Long.parseLong(fileUrl.substring(fileUrl.indexOf("files") + 6));
        fileService.deleteFile(id);
        return null;
    }

    @RequestMapping(value = "/upload/audio", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ajaxUploadAudio(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "oldFileId", required = false) Long oldFileId) {
        Map<String, Object> jsonResult = new HashMap<>();
        jsonResult.put("fileName", file.getOriginalFilename());
        jsonResult.put("fileId", fileService.saveFile(file, FileType.AUDIO));
        if (oldFileId != null) {
            fileService.deleteFile(oldFileId);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/upload/other", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> ajaxUploadOther(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        Long fileId = fileService.saveFile(file, FileType.OTHER);
        Map<String, Object> jsonResult = new HashMap<>();
        jsonResult.put("link", request.getContextPath() + "/files/" + fileId);
        return jsonResult;
    }
}

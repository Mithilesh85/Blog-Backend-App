package com.mithilesh.blog.service.impl;

import com.mithilesh.blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //file name
        String name= file.getOriginalFilename();

        String randomId= UUID.randomUUID().toString();
        String randomFileName=randomId.concat(name.substring(name.lastIndexOf(".")));

        //full path
        String filePath=path+ File.separator+randomFileName;

        // create folder if not created
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        // file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return randomFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws IOException {

        String fullPath=path+ File.separator+fileName;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}

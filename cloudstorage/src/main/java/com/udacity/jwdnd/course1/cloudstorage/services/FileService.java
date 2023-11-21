package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    public final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final UserService userService;

    private final FileMapper fileMapper;

    public FileService(UserService userService, FileMapper fileMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
    }


    public int createFile(File file) {

        return fileMapper.insert(new File(null, file.getFileName(), file.getFileType(),
                file.getFileSize(), file.getUserId(), file.getFileData()));
    }

    public File getFileByUserId(Integer userId) {
        return fileMapper.getFileById(userId);
    }


    public File getFileById(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    public void updateFile(File file){fileMapper.update(file);}

    public void deleteFile(Integer fileId) {
        fileMapper.delete(fileId);
    }


}

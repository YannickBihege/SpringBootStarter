package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM files WHERE userId = #{userId}")
    List<File> getFilesByUserId(Integer userId);

    @Select("SELECT * FROM files WHERE fileId = #{fileId}")
    File getFileById(Integer fileId);

    @Insert("INSERT INTO files (fileName, fileType, fileSize, userId, fileData) " +
            "VALUES (#{fileName}, #{fileType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    void insert(File file);

    @Update("UPDATE files SET fileName = #{fileName}, fileType = #{fileType}, " +
            "fileSize = #{fileSize}, userId = #{userId}, fileData = #{fileData} " +
            "WHERE fileId = #{fileId}")
    void update(File file);

    @Delete("DELETE FROM files WHERE fileId = #{fileId}")
    void delete(Integer fileId);
}

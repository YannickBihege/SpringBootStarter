package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM credentials WHERE credentialId = #{credentialId}")
    Credential getCredentialById(Integer credentialId);

    @Select("SELECT * FROM credentials WHERE username = #{username}")
    Credential getCredentialByUsername(String username);

    @Select("SELECT * FROM credentials WHERE username = #{username}")
    List<Credential> getCredentialsList(String username);

    @Insert("INSERT INTO credentials (url, userName, key, password, userId) " +
            "VALUES (#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer insert(Credential credential);

    @Update("UPDATE credentials SET url = #{url}, userName = #{userName}, " +
            "key = #{key}, password = #{password} WHERE credentialId = #{credentialId}")
    void update(Credential credential);

    @Delete("DELETE FROM credentials WHERE credentialId = #{credentialId}")
    void delete(Integer credentialId);


}

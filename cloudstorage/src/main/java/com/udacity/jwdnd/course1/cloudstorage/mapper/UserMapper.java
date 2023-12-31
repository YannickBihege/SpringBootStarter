package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Update("UPDATE users SET username = #{username}, salt = #{salt}, password = #{password}, " +
            "firstName = #{firstName}, lastName = #{lastName} WHERE userId = #{userId}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE userId = #{userId}")
    void deleteUser(Integer userId);

}

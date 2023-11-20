package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

 @Select("SELECT * FROM USERS WHERE username = #{username}")
 User getUser(String username);

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);


    @Update("UPDATE users SET username = #{username}, salt = #{salt}, password = #{password}, " +
            "firstName = #{firstName}, lastName = #{lastName} WHERE userId = #{userId}")
    void update(User user);

    @Delete("DELETE FROM users WHERE userId = #{userId}")
    void delete(Integer userId);

}

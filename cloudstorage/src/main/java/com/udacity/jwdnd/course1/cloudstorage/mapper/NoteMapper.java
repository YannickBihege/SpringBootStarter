package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM notes WHERE userId = #{userId}")
    List<Note> getNotesByUserId(Integer userId);

    @Select("SELECT * FROM notes WHERE noteId = #{noteId}")
    Note getNoteById(Integer noteId);

    @Insert("INSERT INTO notes (noteTitle, noteDescription, userId) " +
            "VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    void insert(Note note);

    @Update("UPDATE notes SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} " +
            "WHERE noteId = #{noteId}")
    void update(Note note);

    @Delete("DELETE FROM notes WHERE noteId = #{noteId}")
    void delete(Integer noteId);
}

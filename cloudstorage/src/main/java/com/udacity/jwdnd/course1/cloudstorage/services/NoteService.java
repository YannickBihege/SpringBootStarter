package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    public final Logger logger = LoggerFactory.getLogger(NoteService.class);

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotesByUserId(Integer userId) {
        return noteMapper.getNotesByUserId(userId);
    }

    public Note getNoteById(Integer noteId) {
        return noteMapper.getNoteById(noteId);
    }

    public void createOrUpdate(Note note) {
        if (note.getNoteId() == null) {
            noteMapper.insert(note);
        } else {
            noteMapper.update(note);
        }
    }

    public void delete(Integer noteId) {
        noteMapper.delete(noteId);
    }
}

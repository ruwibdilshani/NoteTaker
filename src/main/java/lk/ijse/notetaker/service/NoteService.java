package lk.ijse.notetaker.service;

import lk.ijse.notetaker.dto.Note;

import java.util.List;

public  interface NoteService  {
    String saveData(Note note);

//    boolean updateNote(String noteId, Note note);
 void updateNote(String noteId,Note note);


    boolean deleteNote(String noteId);

    Note getSelectedNote(String noteId);

    List<Note> getAllNotes();

}


package lk.ijse.notetaker.bo;

import lk.ijse.notetaker.dto.Note;

import java.util.List;

public sealed interface NoteBo permits NoteBOIMPL{
    String saveData(Note note);

    boolean updateNote(String noteId, Note note);

    boolean deleteNote(String noteId);

    Note getSelectedNote(String noteId);

    List<Note> getAllNotes();
}


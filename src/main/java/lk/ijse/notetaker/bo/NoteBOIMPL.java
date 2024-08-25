package lk.ijse.notetaker.bo;

import lk.ijse.notetaker.dto.Note;
import lk.ijse.notetaker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public final class NoteBOIMPL implements NoteBo {



    @Override
    public String saveData(Note note) {
        note.setNoteId(AppUtil.createNoteId());
        System.out.println(note);
        return "Saved successfully in BO layer";
    }

    @Override
    public boolean updateNote(String noteId, Note note) {
        return false;
    }

    @Override
    public boolean deleteNote(String noteId) {
        return false;
    }

    @Override
    public Note getSelectedNote(String noteId) {
        return null;
    }

    @Override
    public List<Note> getAllNotes() {
        return null;
    }
}
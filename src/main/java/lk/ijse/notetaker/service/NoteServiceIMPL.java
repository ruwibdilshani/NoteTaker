package lk.ijse.notetaker.service;

import lk.ijse.notetaker.dto.Note;
import lk.ijse.notetaker.util.AppUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public final class NoteServiceIMPL implements NoteService {

    List<Note> SaveNoteTmp = new ArrayList<>();
    public NoteServiceIMPL(){
        SaveNoteTmp.add(new Note("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of SE","SE Desc","P1","20240825"));
        SaveNoteTmp.add(new Note("NOTE 4f8a0a68-3ccc-41b2-9de6-4e9bcdba65bb","Priciples of CS","CS Desc","P2","20240825"));
        SaveNoteTmp.add(new Note("NOTE 4f8a069-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of NW","NW Desc","P1","20240825"));
        SaveNoteTmp.add(new Note("NOTE 4f8a0a70-2ebc-41b2-9de6-4e9ddbbba65b","Priciples of UI","UI Desc","P2","20240825"));
        System.out.println(SaveNoteTmp);
    }

    @Override
    public String saveData(Note note) {
        note.setNoteId(AppUtil.createNoteId());
        SaveNoteTmp.add(note);
        return "Saved successfully in Service layer";
    }

    @Override
    public void updateNote(String noteId, Note incomeNote) {
//        for (Note updateNote : SaveNoteTmp) {
//            if (updateNote.getNoteId().equals(noteId)) {
//                updateNote.setNoteDesc(incomeNote.getNoteDesc());
//                updateNote.setNoteTitle(incomeNote.getNoteTitle());
//                updateNote.setPriorityLevel(incomeNote.getPriorityLevel());
//                updateNote.setCreateDate(incomeNote.getCreateDate());

        ListIterator<Note> updatedList = SaveNoteTmp.listIterator();
        while (updatedList.hasNext()) {
            Note note = updatedList.next();
            if (noteId.equals(note.getNoteId())) {
                incomeNote.setNoteId(note.getNoteId());
                updatedList.set(incomeNote);
                break;
            }
            }


    }

    @Override
    public boolean deleteNote(String noteId) {
        return false;
    }

    @Override
    public Note getSelectedNote(String noteId) {
//        return null;
        for (Note note : SaveNoteTmp) {
            if (note.getNoteId().equals(noteId)) {
                return note;
            }
        }
        return null;
    }

    @Override
    public List<Note> getAllNotes() {
        return SaveNoteTmp;
    }
}
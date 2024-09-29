package lk.ijse.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.notetaker.Dao.NoteDao;
import lk.ijse.notetaker.Expection.DataPersistFailedException;
import lk.ijse.notetaker.Expection.NoteNotFound;
import lk.ijse.notetaker.customObj.NoteErrorResponse;
import lk.ijse.notetaker.customObj.NoteResponse;
import lk.ijse.notetaker.dto.Note;
import lk.ijse.notetaker.entity.NoteEntity;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceIMPL implements NoteService {
    @Autowired
    private NoteDao noteDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void  saveData(Note note) {
        note.setNoteId(AppUtil.createNoteId());
        var noteEntity = mapping.convertToEntity(note);
        var savedNoted = noteDao.save(noteEntity);
        if (savedNoted == null) {
            throw new DataPersistFailedException("Cannot save note");
        }
    }

    @Override
    public void updateNote(String noteId, Note incomeNote) {
      Optional<NoteEntity> tmpNoteEntity= noteDao.findById(noteId);
      if  (!tmpNoteEntity.isPresent()){
          throw new NoteNotFound("Note not found");      }else {
          tmpNoteEntity.get().setNoteDesc(incomeNote.getNoteDesc());
          tmpNoteEntity.get().setNoteTitle(incomeNote.getNoteTitle());
          tmpNoteEntity.get().setCreateDate(incomeNote.getCreateDate());
          tmpNoteEntity.get().setPriorityLevel(incomeNote.getPriorityLevel());
      }

    }

    @Override
    public void deleteNote(String noteId) {
       Optional<NoteEntity>findId = noteDao.findById(noteId);
      if(findId.isPresent()){
         throw new NoteNotFound("Note not found");
      }else {
          noteDao.deleteById(noteId);
      }
    }

    @Override
    public NoteResponse getSelectedNote(String noteId) {

        if (noteDao.existsById(noteId)) {
            return mapping.convertToDTO(noteDao.getReferenceById(noteId));
        }else {
            return new NoteErrorResponse(0, "Note not found");
        }

    }

    @Override
    public List<Note> getAllNotes() {
//        List<NoteEntity> getAllNotes = noteDao.findAll();
//        List<Note> note = mapping.convertToDTO(getAllNotes);
//        return note;

        //-------------------------------------------------------------------------------------------------simplify code
         return mapping.convertToDTO(noteDao.findAll());
    }


//    List<Note> SaveNoteTmp = new ArrayList<>();
//    public NoteServiceIMPL(){
//        SaveNoteTmp.add(new Note("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of SE","SE Desc","P1","20240825"));
//        SaveNoteTmp.add(new Note("NOTE 4f8a0a68-3ccc-41b2-9de6-4e9bcdba65bb","Priciples of CS","CS Desc","P2","20240825"));
//        SaveNoteTmp.add(new Note("NOTE 4f8a069-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of NW","NW Desc","P1","20240825"));
//        SaveNoteTmp.add(new Note("NOTE 4f8a0a70-2ebc-41b2-9de6-4e9ddbbba65b","Priciples of UI","UI Desc","P2","20240825"));
//        System.out.println(SaveNoteTmp);
//    }
//
//    @Override
//    public String saveData(Note note) {
//        note.setNoteId(AppUtil.createNoteId());
//        SaveNoteTmp.add(note);
//        return "Saved successfully in Service layer";
//    }
//
//    @Override
//    public void updateNote(String noteId, Note incomeNote) {
//        for (Note updateNote : SaveNoteTmp) {
//            if (updateNote.getNoteId().equals(noteId)) {
//                updateNote.setNoteDesc(incomeNote.getNoteDesc());
//                updateNote.setNoteTitle(incomeNote.getNoteTitle());
//                updateNote.setPriorityLevel(incomeNote.getPriorityLevel());
//                updateNote.setCreateDate(incomeNote.getCreateDate());
//
//        ListIterator<Note> updatedList = SaveNoteTmp.listIterator();
//        while (updatedList.hasNext()) {
//            Note note = updatedList.next();
//            if (noteId.equals(note.getNoteId())) {
//                incomeNote.setNoteId(note.getNoteId());
//                updatedList.set(incomeNote);
//                break;
//            }
//            }
//    }
//
//    @Override
//    public boolean deleteNote(String noteId) {
//        ListIterator<Note> tmpList = SaveNoteTmp.listIterator();
//        while (tmpList.hasNext()) {
//            Note note = tmpList.next();
//            if (noteId.equals(note.getNoteId())) {
//                tmpList.remove();
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public Note getSelectedNote(String noteId) {
////        return null;
//        for (Note note : SaveNoteTmp) {
//            if (note.getNoteId().equals(noteId)) {
//                return note;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<Note> getAllNotes() {
//        return SaveNoteTmp;
//    }
}
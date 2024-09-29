package lk.ijse.notetaker.controller;

import lk.ijse.notetaker.Expection.DataPersistFailedException;
import lk.ijse.notetaker.Expection.NoteNotFound;
import lk.ijse.notetaker.service.NoteService;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.dto.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/note")
public class NoteController {
    @Autowired
    private final NoteService noteService;


    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNote(@RequestBody Note note){

    try{
        noteService.saveData(note);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }catch (DataPersistFailedException e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @GetMapping(value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> getAllNotes(){
//        System.out.println("Get all notes");
//        return null;
        return noteService.getAllNotes();
    }


    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Note getNote(@PathVariable ("noteId") String noteId)  {
//        System.out.println(noteId);
//        return new Note(
//                "NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb",
//                "REST services",
//                "Explain the REST",
//                "P1",
//                "20240818"
//        );
        return noteService.getSelectedNote(noteId);
    }


    @PatchMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateNote(@PathVariable ("noteId") String noteId, @RequestBody Note note) {
        try {
            noteService.updateNote(noteId, note);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoteNotFound e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{noteId}")
  //  public void deleteNote(@PathVariable ("noteId") String noteId) {
    public ResponseEntity<String> deleteNote(@PathVariable ("noteId") String noteId) {

//        System.out.println(noteId + " Deleted");
//        noteService.deleteNote(noteId);
        return noteService.deleteNote(noteId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}

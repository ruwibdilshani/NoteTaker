package lk.ijse.notetaker.controller;

import lk.ijse.notetaker.service.NoteService;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.dto.Note;
import org.springframework.beans.factory.annotation.Autowired;
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


//Todo: CRUD of the note

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody Note note){

//       //Todo:Handle with Bo
//        note.setNoteId(AppUtil.createNoteId());
//        System.out.println(note);
//        return  ResponseEntity.ok("note created Succesfully.");
        //Todo: Handle with Service
        var saveData = noteService.saveData(note);
        return ResponseEntity.ok(saveData);
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
    public void updateNote(@PathVariable ("noteId") String noteId, @RequestBody Note note) {
        System.out.println(noteId);
        System.out.println(note+ " Updated");
    }


    @DeleteMapping(value = "/{noteId}")
    public void deleteNote(@PathVariable ("noteId") String noteId) {
        System.out.println(noteId + " Deleted");
}


}

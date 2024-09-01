package lk.ijse.notetaker.util;

import lk.ijse.notetaker.dto.Note;
import lk.ijse.notetaker.entity.NoteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    // Motters of NoteEntity and Dto

    public Note convertToDTO(NoteEntity note) {
        return modelMapper.map(note, Note.class);
    }

    public NoteEntity convertToEntity(Note dto) {
        return modelMapper.map(dto, NoteEntity.class);
    }

    public List<Note> convertToDTO(List<NoteEntity> notes) {
        return modelMapper.map(notes, List.class);
    }
}
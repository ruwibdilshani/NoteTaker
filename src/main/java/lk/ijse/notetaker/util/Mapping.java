package lk.ijse.notetaker.util;

import lk.ijse.notetaker.dto.Note;
import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.entity.NoteEntity;
import lk.ijse.notetaker.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
        return modelMapper.map(notes, new TypeToken<List<Note>>(){}.getType());
    }




    //User matters mapping

    public UserEntity convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDTO convertToUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public List<UserDTO> convertUserToDTOList(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, new TypeToken<List<UserDTO>>() {}.getType());
    }

}
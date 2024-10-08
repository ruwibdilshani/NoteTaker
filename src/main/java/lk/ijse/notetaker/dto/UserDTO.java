package lk.ijse.notetaker.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lk.ijse.notetaker.customObj.UserResponse;
import lk.ijse.notetaker.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePic;
    //private List<Note> notes;
    private List<Note> notes = new ArrayList<>();

}
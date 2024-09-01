package lk.ijse.notetaker.service;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lk.ijse.notetaker.dto.Note;
import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.entity.NoteEntity;

import java.util.List;

public interface UserService {
  String saveUser(UserDTO userDTO);
  boolean updateUser(String userId,UserDTO userDTO);
  boolean deleteUser(String userId);
  UserDTO getSelectedUser(String userId);
  List<UserDTO> getAllUsers();
}

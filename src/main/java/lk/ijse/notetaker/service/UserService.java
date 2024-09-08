package lk.ijse.notetaker.service;

import lk.ijse.notetaker.customObj.UserResponse;
import lk.ijse.notetaker.dto.UserDTO;

import java.util.List;

public interface UserService {
  void saveUser(UserDTO userDTO);
  //boolean updateUser(UserDTO userDTO);
  void updateUser(UserDTO userDTO);
 // boolean deleteUser(String userId);
 void deleteUser(String userId);

    //  UserDTO getSelectedUser(String userId);
 UserResponse getSelectedUser(String userId);
  List<UserDTO> getAllUsers();
}

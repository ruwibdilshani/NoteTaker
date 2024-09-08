package lk.ijse.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.notetaker.Dao.UserDao;
import lk.ijse.notetaker.Expection.UserNotFoundException;
import lk.ijse.notetaker.customObj.UserErrorResponse;
import lk.ijse.notetaker.customObj.UserResponse;
import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.entity.UserEntity;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService{

    @Autowired
    private  final UserDao userDao;
    @Autowired
    private final Mapping mapping;

    @Override
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        UserEntity saveUser = userDao.save(mapping.convertToUserEntity(userDTO));
        if (saveUser!=null && saveUser.getUserId() != null){
            return "User Saved Successfully..!";
        }else {
            return "Save Failed ...!";
        }
    }


    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDao.findById(userDTO.getUserId());
        if(!tmpUser.isPresent()){
          //  return false;
            throw new UserNotFoundException("User not found");
        }else {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
        }

    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> selectedUserId = userDao.findById(userId);
        if(!selectedUserId.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
//    public UserDTO getSelectedUser(String userId) {
//       UserEntity userEntityByUserId = userDao.getUserEntitiesByUserId(userId);
//       return mapping.convertToUserDTO(userEntityByUserId);
//    }

    public UserResponse getSelectedUser(String userId) {
        if (userDao.existsById(userId)) {
            UserEntity userEntityByUserId = userDao.getUserEntitiesByUserId(userId);
            return mapping.convertToUserDTO(userEntityByUserId);
        } else {
            return new UserErrorResponse(0, "User not found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
       List<UserEntity>getAllUsers= userDao.findAll();
      return mapping.convertUserToDTOList(getAllUsers);
    }
}

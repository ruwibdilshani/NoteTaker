package lk.ijse.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.notetaker.Dao.UserDao;
import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.entity.UserEntity;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;

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
        userDao.save(mapping.convertToUserEntity(userDTO));
        return "User Saved Successfully..!";

    }

    public boolean updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDao.findById(userDTO.getUserId());
        if(!tmpUser.isPresent()){
            return false;
        }else {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
        }
        return true;
    }

    @Override
    public boolean deleteUser(String userId) {
        if (userDao.existsById(userId)){
            userDao.deleteById(userId);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
       UserEntity userEntityByUserId = userDao.getUserEntitiesByUserId(userId);
       return mapping.convertToUserDTO(userEntityByUserId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
       List<UserEntity>getAllUsers= userDao.findAll();
      return mapping.convertUserToDTOList(getAllUsers);
    }
}

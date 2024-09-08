package lk.ijse.notetaker.service;

import jakarta.transaction.Transactional;
import lk.ijse.notetaker.Dao.UserDao;
import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.util.AppUtil;
import lk.ijse.notetaker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
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
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }
}

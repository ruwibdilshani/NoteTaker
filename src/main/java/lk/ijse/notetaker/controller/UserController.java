package lk.ijse.notetaker.controller;


import lk.ijse.notetaker.Dao.UserDao;
import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.entity.UserEntity;
import lk.ijse.notetaker.service.UserService;
import lk.ijse.notetaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private UserDao userDao;

    //SAVE USER
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser(
     @RequestPart("firstName") String firstName,
     @RequestPart("lastName") String lastName,
     @RequestPart("email") String email,
     @RequestPart("password") String password,
     @RequestPart("profilePic") String profilePic) {

        //Handle Profile Picture
        String base64ProfilePic =AppUtil.toBase64ProfilePic(profilePic);

        //build the user object
        UserDTO buildUserDTO = new UserDTO();
        buildUserDTO.setFirstName(firstName);
        buildUserDTO.setLastName(lastName);
        buildUserDTO.setEmail(email);
        buildUserDTO.setPassword(password);
        buildUserDTO.setProfilePic(base64ProfilePic);

        //send to the service layer
        var saveStatus = userService.saveUser(buildUserDTO);
        if (saveStatus.contains("User Saved Successfully...")){
            return new ResponseEntity<>(userService.saveUser(buildUserDTO), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteUser(@PathVariable ("id") String userId){
     return userService.deleteUser(userId)? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //GET SELECTED
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  UserDTO getSelectedUser(@PathVariable ("id") String userId){
        return userService.getSelectedUser(userId);
    }


    //GET ALL
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }


    //UPDATE
    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUser(
            @PathVariable ("id") String id,
            @RequestPart("updateFirstName") String updateFirstName,
            @RequestPart ("updateLastName") String updateLastName,
            @RequestPart ("updateEmail") String updateEmail,
            @RequestPart ("updatePassword") String updatePassword,
            @RequestPart ("updateProfilePic") String updateProfilePic
    ){
        String updateBase64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic);
        var updateUser = new UserDTO();
        updateUser.setUserId(id);
        updateUser.setFirstName(updateFirstName);
        updateUser.setLastName(updateLastName);
        updateUser.setPassword(updatePassword);
        updateUser.setEmail(updateEmail);
        updateUser.setProfilePic(updateBase64ProfilePic);
        return userService.updateUser(updateUser)? new ResponseEntity<>(HttpStatus.NO_CONTENT): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
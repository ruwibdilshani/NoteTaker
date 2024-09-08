package lk.ijse.notetaker.controller;


import lk.ijse.notetaker.dto.UserDTO;
import lk.ijse.notetaker.service.UserService;
import lk.ijse.notetaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

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
        return new ResponseEntity<>(userService.saveUser(buildUserDTO), HttpStatus.CREATED);
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
}
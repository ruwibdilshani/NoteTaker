package lk.ijse.notetaker.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {

    public static String createNoteId() {
        return "NOTE"+ UUID.randomUUID().toString();
    }

    public static String createUserId(){
        return "USER-"+UUID.randomUUID();
    }

    public static String toBase64ProfilePic(String profilePic){//profile pic eka convert karanna tamai me code set eken wenne
        return Base64.getEncoder().encodeToString(profilePic.getBytes());
    }

}

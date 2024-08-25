package lk.ijse.notetaker.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AppUtil {

    public static String createNoteId() {
        return "NOTE"+ UUID.randomUUID().toString();
    }

}

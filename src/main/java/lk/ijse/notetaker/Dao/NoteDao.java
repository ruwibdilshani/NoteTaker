package lk.ijse.notetaker.Dao;

import lk.ijse.notetaker.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//database handle krn eka tamai meken karanne.
public interface NoteDao extends JpaRepository<NoteEntity,String> {

}

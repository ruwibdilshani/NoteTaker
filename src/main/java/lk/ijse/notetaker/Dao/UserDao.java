package lk.ijse.notetaker.Dao;

import lk.ijse.notetaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {
   //@Query()pahatha eka wenuwt metn quary ekk type krnnt plwn

    UserEntity getUserEntitiesByUserId(String userId);

}

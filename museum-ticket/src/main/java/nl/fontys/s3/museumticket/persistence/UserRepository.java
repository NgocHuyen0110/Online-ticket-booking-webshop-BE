package nl.fontys.s3.museumticket.persistence;

import nl.fontys.s3.museumticket.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    @Query("select u from UserEntity as u where u.email =?1")
//    UserEntity findUserByEmail(String email);
    UserEntity findByEmail(String email);
}

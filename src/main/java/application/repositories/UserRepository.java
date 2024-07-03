package application.repositories;

import application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    List<User> findByEmail(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String username);
}

package pl.biernacki.notesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.biernacki.notesapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}

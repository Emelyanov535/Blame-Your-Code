package ru.codingbros.blameyourcode.Repository;

import ru.codingbros.blameyourcode.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
}

package ru.codingbros.blameyourcode.Repository;

import ru.codingbros.blameyourcode.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

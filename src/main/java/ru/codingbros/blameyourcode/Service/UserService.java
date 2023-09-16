package ru.codingbros.blameyourcode.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.codingbros.blameyourcode.Model.Role;
import ru.codingbros.blameyourcode.Model.User;
import ru.codingbros.blameyourcode.Repository.IRoleRepository;
import ru.codingbros.blameyourcode.Repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(String username, String email, String password, String confirmPassword){
        if(Objects.equals(password, confirmPassword)){
            final User user = new User(username, email, passwordEncoder.encode(password), (byte) 0x64);
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRoles(Collections.singleton(userRole));
            return userRepository.save(user);
        }else{
            return null; //Обработка ошибки
        }

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
}

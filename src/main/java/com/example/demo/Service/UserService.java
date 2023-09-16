package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.IRoleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.NotFoundException.UserNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demo.Configuration.PasswordEncoderConfiguration.*;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UserService(UserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

/*    @Transactional
    public User createUser(String name, String email, String password, Byte photo){
        final User user = new User(name, email, hashPassword(password), photo);
        return userRepository.save(user);
    }*/

/*    @Transactional(readOnly = true)
    public User findUser(Long id){
        final Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User authorizeUser(String email, String password){
        User user = userRepository.findByEmail(email);
        if (user != null && checkPassword(password, user.getPassword())) {
            return user;
        } else {
            // Обработка случая, когда пользователь не найден
            return null;
        }
    }*/

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

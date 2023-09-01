package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.IUserRepository;
import com.example.demo.Service.NotFoundException.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import static com.example.demo.Configuration.PasswordEncoderConfiguration.*;

@Service
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String username, String login, String password, Byte photo){
        final User user = new User(username, login, hashPassword(password), photo);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(Long id){
        final Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User authorizeUser(String login, String password){
        User user = userRepository.findByLogin(login);
        if (user != null && checkPassword(password, user.getPassword())) {
            return user;
        } else {
            // Обработка случая, когда пользователь не найден
            return null;
        }
    }
}

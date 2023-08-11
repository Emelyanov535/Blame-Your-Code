package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(String username, String login, String password, Byte photo){
        final User user = new User(username, login, password, photo);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findUser(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public String findUserByLoginAndGetPassword(String login){
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return user.getPassword();
        } else {
            // Обработка случая, когда пользователь не найден
            return null;
        }
    }
}

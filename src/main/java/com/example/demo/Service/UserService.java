package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.NotFoundException.UserNotFoundException;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String username, String login, String password, Byte photo){
        final User user = new User(username, login, password, photo);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(Long id){
        final Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User checkUserByLoginAndPassword(String login, String password){
        User user = userRepository.findByLogin(login);
        if (user != null && Objects.equals(user.getPassword(), password)) {
            return user;
        } else {
            // Обработка случая, когда пользователь не найден
            return null;
        }
    }
}

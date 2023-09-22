package ru.codingbros.blameyourcode.Service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.codingbros.blameyourcode.Controller.DTO.JwtRequest;
import ru.codingbros.blameyourcode.Controller.DTO.JwtResponse;
import ru.codingbros.blameyourcode.Model.CustomUserDetails;
import ru.codingbros.blameyourcode.Model.Role;
import ru.codingbros.blameyourcode.Model.User;
import ru.codingbros.blameyourcode.Repository.RoleRepository;
import ru.codingbros.blameyourcode.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.codingbros.blameyourcode.Service.NotFoundException.UserNotFoundException;
import ru.codingbros.blameyourcode.Utils.JwtTokenUtils;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final Map<String, String> refreshStorage = new HashMap<>();

    @Transactional
    public User createUser(String username, String email, String password, String confirmPassword){
        if(Objects.equals(password, confirmPassword)){
            final User user = new User(username, email, passwordEncoder.encode(password), (byte) 0x64); // Костыль с фото(надо будет допилить, когда клиент пришлет фото)
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRoles(Collections.singleton(userRole));
            return userRepository.save(user);
        }else{
            return null; //Обработка ошибки
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new CustomUserDetails(user);
    }

    @Transactional(readOnly = true)
    public User findUser(Long id){
        final Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public JwtResponse getAuthToken(JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return null;
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(userRepository.findByEmail(authRequest.getEmail()));
        String AccessToken = jwtTokenUtils.generateAccessToken(customUserDetails);
        String RefreshToken = jwtTokenUtils.generateRefreshToken(customUserDetails);
        refreshStorage.put(customUserDetails.getEmail(), RefreshToken);
        return new JwtResponse(AccessToken, RefreshToken, customUserDetails.getEmail(), customUserDetails.getUsername());
    }
}

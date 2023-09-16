package ru.codingbros.blameyourcode.Utils;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class JwtTokenUtils {
    private String secret = "xyipizda";

    private Duration lifetime = Duration.ofMinutes(30);

/*    public String generateToken(UserDetails userDetails){

    }*/
}

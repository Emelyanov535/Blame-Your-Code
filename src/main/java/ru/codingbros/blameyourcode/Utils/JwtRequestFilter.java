package ru.codingbros.blameyourcode.Utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.codingbros.blameyourcode.Model.CustomUserDetails;
import ru.codingbros.blameyourcode.Model.User;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        User user = new User();
        Collection<? extends GrantedAuthority> authorities = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            try {
                String username = jwtTokenUtils.getUsername(jwt);
                String email = jwtTokenUtils.getEmail(jwt);
                Long id = jwtTokenUtils.getUserId(jwt);
                user.setId(id);
                user.setUsername(username);
                user.setEmail(email);
                authorities = jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).toList();
            }catch (Exception ex){
                //Ошибка
            }
        }
        if(user.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}

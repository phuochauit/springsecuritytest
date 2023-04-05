package com.example.demo.services;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dtos.BearerToken;
import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.RegisterDto;
import com.example.demo.enums.RoleName;
import com.example.demo.infrastructure.IRoleDao;
import com.example.demo.infrastructure.IUserDao;
import com.example.demo.security.JwtUtilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final AuthenticationManager authenticationManager;
    private final IUserDao iUserDao;
    private final IRoleDao iRoleDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;

    @Override
    public String authenticate(LoginDto loginDto) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = iUserDao.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r-> rolesNames.add(r.getRoleName()));
        String token = jwtUtilities.generateToken(user.getUsername(),rolesNames);
        return token;
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if(iUserDao.existsByUsername(registerDto.getUsername())){
            return  new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER);
        }
        else {
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            //By Default , he/she is a simple user
            Role role = iRoleDao.findByRoleName(RoleName.USER);
            user.setRoles(Collections.singletonList(role));
            iUserDao.save(user);
            String token = jwtUtilities.generateToken(registerDto.getUsername(),Collections.singletonList(role.getRoleName()));
            return new ResponseEntity<>(new BearerToken(token , "Bearer "),HttpStatus.OK);
        }
    }

    @Override
    public Role saveRole(Role role) {
        return iRoleDao.save(role);
    }

    @Override
    public User saveUser(User user) {
        return iUserDao.save(user);
    }
}

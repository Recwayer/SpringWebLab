package com.example.springtest.services.sec;

import com.example.springtest.dtos.UserRegistrationDto;
import com.example.springtest.dtos.web.AddUserDto;
import com.example.springtest.exceptions.ClientSecurityException;
import com.example.springtest.models.User;
import com.example.springtest.models.enums.Role;
import com.example.springtest.repositories.UserRepository;
import com.example.springtest.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationDto registrationDTO) throws ClientSecurityException {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new ClientSecurityException("passwords.match");
        }

        var userRole = userRoleRepository.
                findUserRoleByRole(Role.USER).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                true
        );
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setRole(userRole);

        this.userRepository.save(user);
    }
    public void register(AddUserDto registrationDTO) throws ClientSecurityException {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new ClientSecurityException("passwords.match");
        }

        var userRole = userRoleRepository.
                findUserRoleByRole(Role.USER).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                true
        );
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setRole(userRole);

        this.userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}

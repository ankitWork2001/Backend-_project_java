package com.vaishalitech.jrsnacks.serviceimpl;

import com.vaishalitech.jrsnacks.dto.UserDTO;
import com.vaishalitech.jrsnacks.entity.User;
import com.vaishalitech.jrsnacks.repository.UserRepository;
import com.vaishalitech.jrsnacks.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Registers a new user.
     * Returns an Optional of UserDTO if registration is successful, or Optional.empty() if the email already exists.
     */
    public Optional<UserDTO> registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return Optional.empty();
        }

        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhoneNumber(),
                savedUser.getRole().toString(),  // Converts enum to String if UserDTO.role is a String
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
        return Optional.of(userDTO);

    /**
     * Authenticates a user and generates a JWT token.
     * Returns an Optional containing the token if authentication is successful, or Optional.empty() if it fails.
     */
    public Optional<String> loginUser(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception ex) {
            // Authentication failed
            return Optional.empty();
        }

        // If authentication is successful, look up the user
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            String token = jwtUtil.generateToken(userOptional.get().getEmail());
            return Optional.of(token);
        }

        return Optional.empty();
    }
}

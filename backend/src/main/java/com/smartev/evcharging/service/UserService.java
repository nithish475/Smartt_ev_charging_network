package com.smartev.evcharging.service;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.smartev.evcharging.dto.LoginRequest;
import com.smartev.evcharging.dto.RegisterRequest;
import com.smartev.evcharging.entity.User;
import com.smartev.evcharging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartev.evcharging.dto.LoginResponse;
import com.smartev.evcharging.jwt.JwtUtil;
import com.smartev.evcharging.entity.Role;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getRole() == null) {
            user.setRole(Role.USER);
        } else {
            user.setRole(Role.USER);
        }

        return userRepository.save(user);
    }

    public LoginResponse loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user != null &&
                passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            String token = jwtUtil.generateToken(
                    user.getEmail(),
                    user.getRole().name()
            );

            return new LoginResponse(token, "Login Successful");
        }

        return new LoginResponse(null, "Invalid Email or Password");
    }
    // View All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    // Total Users
    public long getUserCount() {
        return userRepository.count();
    }
}
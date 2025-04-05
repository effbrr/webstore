package com.fb.webstore.api.security;

import com.fb.webstore.api.security.util.JwtUtil;
import com.fb.webstore.api.user.User;
import com.fb.webstore.api.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserService userService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        try {
            User registeredUser = userService.registerUser(user);
            response.put("message", "User registered successfully");
            response.put("userId", registeredUser.getId().toString());
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}

@Setter
@Getter
class AuthRequest {
    private String email;
    private String password;
}

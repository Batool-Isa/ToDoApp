package com.ga.ToDoApp.service;

import com.ga.ToDoApp.exception.InformationExistException;
import com.ga.ToDoApp.model.User;
import com.ga.ToDoApp.model.UserProfile;
import com.ga.ToDoApp.model.request.LoginRequest;
import com.ga.ToDoApp.model.response.LoginResponse;
import com.ga.ToDoApp.repository.UserRepository;
import com.ga.ToDoApp.security.JWTUtils;
import com.ga.ToDoApp.security.JwtRequestFilter;
import com.ga.ToDoApp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils ,
                       @Lazy AuthenticationManager authenticationManager,
                       @Lazy MyUserDetails myUserDetails){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.myUserDetails = myUserDetails;
    }
    public User createUser(User userObj){
        System.out.println("Service calling create User!");
        if(!userRepository.existsByEmailAddress(userObj.getEmailAddress())){
            userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));
            return userRepository.save(userObj);
        }else {
            throw  new InformationExistException("User with email address "+userObj.getEmailAddress()+ " already exist");
        }
    }

    public User findUserByEmailAddress(String email){
        return userRepository.findUserByEmailAddress(email);
    }

    public User login(String email){
        return userRepository.findUserByEmailAddress(email);
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();
            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        }    catch (Exception e){
            return ResponseEntity.ok(new LoginResponse("ERROR: username or email is incorrect"));

        }
    }
}

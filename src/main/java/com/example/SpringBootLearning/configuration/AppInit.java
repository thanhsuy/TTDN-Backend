package com.example.SpringBootLearning.configuration;

import com.example.SpringBootLearning.dto.request.UserCreationRequest;
import com.example.SpringBootLearning.entity.User;
import com.example.SpringBootLearning.enums.Roles;
import com.example.SpringBootLearning.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class AppInit {
    private static final Logger log = LoggerFactory.getLogger(AppInit.class);
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                HashSet<String> roles = new HashSet<>();
                roles.add(Roles.ADMIN.name());
                User user = User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin"))
                            .roles(roles)
                            .build();
                userRepository.save(user);
                log.warn("admin has create");
            }
        };
    }
}

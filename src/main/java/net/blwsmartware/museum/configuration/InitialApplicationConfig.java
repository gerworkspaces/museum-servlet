package net.blwsmartware.museum.configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

import net.blwsmartware.museum.constant.PredefinedRole;
import net.blwsmartware.museum.entity.Role;
import net.blwsmartware.museum.entity.User;
import net.blwsmartware.museum.repository.RoleRepository;
import net.blwsmartware.museum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InitialApplicationConfig {
    @Value("${config.admin.name}")
    @NonFinal
    String adminName;

    @Value("${config.admin.password}")
    @NonFinal
    String adminPassword;

    @Value("${config.admin.email}")
    @NonFinal
    String adminEmail;

    @Value("${config.admin.username}")
    @NonFinal
    String adminUsername;


    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository , RoleRepository roleRepository){
        log.info("********** Initializing application...");
        return args -> {
            if (userRepository.findByUsername(adminUsername).isEmpty()) {
                Role admin = roleRepository.save(Role.builder()
                                .name(PredefinedRole.ADMIN_ROLE)
                                .description("Admin")
                        .build());
                Role user = roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("User")
                        .build());
                Set<Role> roles = new HashSet<>();
                roles.add(admin);
                roles.add(user);
                User userAdmin = User.builder()
                        .email(adminEmail)
                        .name(adminName)
                        .username(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .roles(roles)
                        .build();
                userRepository.save(userAdmin);
                log.info("********** Application init successfully for admin...");
            }
            log.info("********** Application initialization completed before...");
        };
    }


}

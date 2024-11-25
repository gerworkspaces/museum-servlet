package net.blwsmartware.museum.service.impl;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.entity.User;
import net.blwsmartware.museum.enums.ErrorResponse;
import net.blwsmartware.museum.exception.AppRuntimeException;
import net.blwsmartware.museum.repository.UserRepository;
import net.blwsmartware.museum.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.USER_NOT_FOUND));
        return new CustomUserDetails(user);
    }
    public UserDetails loadUserByID(long id)  {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppRuntimeException(ErrorResponse.USER_NOT_FOUND));
        return new CustomUserDetails(user);
    }
}


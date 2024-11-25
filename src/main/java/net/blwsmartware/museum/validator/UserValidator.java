package net.blwsmartware.museum.validator;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("userValidator")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserValidator {

    public boolean isCurrentUser(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserID = authentication.getName();
        return userId.equals(currentUserID);
    }
}

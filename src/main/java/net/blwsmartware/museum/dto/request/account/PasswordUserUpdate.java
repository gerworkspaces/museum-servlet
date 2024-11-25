package net.blwsmartware.museum.dto.request.account;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordUserUpdate {

    @Size(min = 6, message = "PASSWORD_MUST_6_DIGITS")
    String password;
    String old_password;
}

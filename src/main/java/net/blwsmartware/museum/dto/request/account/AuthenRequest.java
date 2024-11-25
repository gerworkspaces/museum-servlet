package net.blwsmartware.museum.dto.request.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class AuthenRequest {

    @NotNull(message = "EMAIL_NOT_NULL")
    @Email(message = "EMAIL_INVALID")
    String username;

    @Size(min = 8,message = "PASSWORD_MUST_8_DIGITS")
    @NotNull(message = "PASSWORD_NOT_NULL")
    String password;
}

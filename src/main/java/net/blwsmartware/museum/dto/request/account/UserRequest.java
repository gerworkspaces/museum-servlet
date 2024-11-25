package net.blwsmartware.museum.dto.request.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserRequest {
    @Size(min = 6,message = "PASSWORD_MUST_6_DIGITS")
    @NotNull(message = "PASSWORD_NOT_NULL")
    String password;

    @NotNull(message = "NAME_NOT_NULL")
    String name;

    @NotNull(message = "USERNAME_NOT_NULL")
    String username;

    @Email(message = "EMAIL_INVALID")
    String email;

    String avatar,thumbnail, title, description;

    String tel;

    LocalDate dob;
}

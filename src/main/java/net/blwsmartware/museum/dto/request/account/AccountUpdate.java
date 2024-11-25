package net.blwsmartware.museum.dto.request.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountUpdate {

    @Email(message = "EMAIL_INVALID")
    @NotNull(message = "EMAIL_NOT_NULL")
    String email;
    @NotNull(message = "USERNAME_NOT_NULL")
    String username ;
}

package net.blwsmartware.museum.dto.request.account;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordUpdate {

    @NotNull(message = "PASSWORD_NOT_NULL")
    String password ;
}

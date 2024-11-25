package net.blwsmartware.museum.dto.request.account;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class VerifyRequest {

    @NotNull(message = "JWT_NOT_NULL")
    String token;

}

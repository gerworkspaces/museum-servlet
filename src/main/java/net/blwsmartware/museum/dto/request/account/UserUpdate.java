package net.blwsmartware.museum.dto.request.account;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdate {

    @NotNull(message = "NAME_NOT_NULL")
    String name;

    String avatar, title, description;

    String tel;

    LocalDate dob;

}

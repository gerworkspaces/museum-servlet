package net.blwsmartware.museum.dto.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.entity.Role;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    Long id;
    String name;
    String username;
    String email;
    boolean isActive;
    boolean emailVerified;
    Instant createAt;
    Instant updateAt;
    String avatar, title, description;
    String tel;
    LocalDate dob;

    Set<Role> roles;
}

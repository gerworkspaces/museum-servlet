package net.blwsmartware.museum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Size(min = 8,message = "PASSWORD_MUST_8_DIGITS")
    String password;

    @NotNull(message = "NAME_NOT_NULL")
    String name;

    @Email(message = "EMAIL_INVALID")
    @Column(unique = true)
    String email;

    @Column(unique = true)
    @NotNull(message = "USERNAME_NOT_NULL")
    String username;


    @Builder.Default
    boolean isActive=true;

    @Builder.Default
    boolean emailVerified=false;

    @CreationTimestamp
    Instant createAt;

    @UpdateTimestamp
    Instant updateAt;

    String avatar, title, description,tel;

    LocalDate dob;

    @ManyToMany
    Set<Role> roles;


}

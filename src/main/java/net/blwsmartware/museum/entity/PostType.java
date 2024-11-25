package net.blwsmartware.museum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PostType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @NotNull(message = "NAME_NOT_NULL")
    String name;

    String description;

}

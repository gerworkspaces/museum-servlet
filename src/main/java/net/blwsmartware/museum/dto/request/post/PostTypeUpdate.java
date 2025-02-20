package net.blwsmartware.museum.dto.request.post;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostTypeUpdate {

    @NotNull(message = "NAME_NOT_NULL")
    String name;
    String description;
}

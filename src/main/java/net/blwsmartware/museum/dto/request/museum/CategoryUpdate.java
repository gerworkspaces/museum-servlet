package net.blwsmartware.museum.dto.request.museum;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdate {

    @NotNull(message = "NAME_NOT_NULL")
    String name;
    String description;
}

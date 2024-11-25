package net.blwsmartware.museum.dto.response.museum;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.entity.Category;

import java.time.Instant;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MuseumResponse {
    int id;
    String name;
    String description, location,thumbnail;
    Instant createAt;
    Instant updateAt;
    Category category;
}

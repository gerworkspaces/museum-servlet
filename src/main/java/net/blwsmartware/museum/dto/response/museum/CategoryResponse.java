package net.blwsmartware.museum.dto.response.museum;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.entity.Museum;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {
    int id;
    String name;
    String description;
    List<Museum> museums ;
}

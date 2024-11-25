package net.blwsmartware.museum.dto.response.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.entity.Post;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostTypeResponse {
    int id;
    String name;
    String description;
    List<Post> posts ;
}

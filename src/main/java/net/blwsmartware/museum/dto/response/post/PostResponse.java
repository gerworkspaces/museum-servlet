package net.blwsmartware.museum.dto.response.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.entity.PostType;

import java.time.Instant;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    Long id;
    String  created_by,
            thumbnail ,
            title ,
            content ;
    Instant createAt;

    Instant updateAt;

    Instant publishedAt;
    int status;

    PostType type;
}

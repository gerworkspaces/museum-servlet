package net.blwsmartware.museum.dto.request.post;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostUpdate {
    String  created_by,
            thumbnail ,
            title ,
            content ;
    int status;

    Instant publishedAt;
}

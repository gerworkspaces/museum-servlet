package net.blwsmartware.museum.dto.request.post;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {

    String  created_by,
            thumbnail ,
            title ,
            content ;
    int post_type;
}

package net.blwsmartware.museum.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MessageResponse<T> {
    @Builder.Default
    int code=5000;
    @Builder.Default
    boolean success=true;
    String message;
    T result;

}

package net.blwsmartware.museum.dto.request.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequest {
    String to;
    String name;
    String content;
}

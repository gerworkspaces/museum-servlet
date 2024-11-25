package net.blwsmartware.event.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WelcomeEmailNotification {
    String to;
    String name;
    String content;
    String subject;
}

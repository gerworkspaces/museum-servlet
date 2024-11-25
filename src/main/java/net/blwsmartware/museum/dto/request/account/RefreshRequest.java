package net.blwsmartware.museum.dto.request.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshRequest {
    private String refreshToken;
}

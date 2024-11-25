package net.blwsmartware.museum.service;

import net.blwsmartware.museum.dto.request.account.AuthenRequest;
import net.blwsmartware.museum.dto.request.account.LogoutRequest;
import net.blwsmartware.museum.dto.request.account.RefreshRequest;
import net.blwsmartware.museum.dto.request.account.VerifyRequest;
import net.blwsmartware.museum.dto.response.AuthenResponse;
import net.blwsmartware.museum.dto.response.user.VerifyResponse;

public interface AuthenticationService {
    VerifyResponse verify(VerifyRequest request);
    AuthenResponse authentication(AuthenRequest request);
    void logout(LogoutRequest request);
    AuthenResponse refreshToken(RefreshRequest request);
}

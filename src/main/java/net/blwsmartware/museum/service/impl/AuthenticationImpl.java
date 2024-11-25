package net.blwsmartware.museum.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.constant.TokenType;
import net.blwsmartware.museum.dto.request.account.AuthenRequest;
import net.blwsmartware.museum.dto.request.account.LogoutRequest;
import net.blwsmartware.museum.dto.request.account.RefreshRequest;
import net.blwsmartware.museum.dto.request.account.VerifyRequest;
import net.blwsmartware.museum.dto.response.AuthenResponse;
import net.blwsmartware.museum.dto.response.user.VerifyResponse;
import net.blwsmartware.museum.entity.InvalidToken;
import net.blwsmartware.museum.enums.ErrorResponse;
import net.blwsmartware.museum.exception.AppRuntimeException;
import net.blwsmartware.museum.repository.InvalidTokenRepository;
import net.blwsmartware.museum.security.JwtTokenProvider;
import net.blwsmartware.museum.service.AuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationImpl implements AuthenticationService {

    JwtTokenProvider jwtTokenProvider;
    InvalidTokenRepository invalidTokenRepository;
    PasswordEncoder passwordEncoder;
    UserDetailsService userDetailsService;

    @Override
    public VerifyResponse verify(VerifyRequest request) {

        String token = request.getToken();

        boolean rs = jwtTokenProvider.verify(token);
        if (!rs) {
            throw new AppRuntimeException(ErrorResponse.JWT_INVALID);
        }

        String type = jwtTokenProvider.getTokenType(token);
        if(!type.equals(TokenType.access)) {
            throw new AppRuntimeException(ErrorResponse.JWT_ACCESS_INVALID);
        }
        String username = jwtTokenProvider.getUsername(token);

        Instant expr = jwtTokenProvider.getExpireDate(token);

        return VerifyResponse.builder()
                    .isValid(true)
                    .username(username)
                    .expiration(expr)
                    .build();
    }

    @Override
    public AuthenResponse authentication(AuthenRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        var matched = passwordEncoder.matches(request.getPassword(), userDetails.getPassword());

        if (!matched) throw new AppRuntimeException(ErrorResponse.USER_NOT_FOUND);

        String token = jwtTokenProvider.createAccessToken(userDetails);
        String refreshToken = jwtTokenProvider.createRefreshToken(userDetails);
        Instant expr = jwtTokenProvider.getExpireDate(token);

        return AuthenResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .expiresIn(expr)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) {

        String token = request.getRefreshToken();

        boolean isRefreshToken = jwtTokenProvider.getTokenType(token)
                .equals(TokenType.refresh);
        if(!isRefreshToken ) {
            throw new AppRuntimeException(ErrorResponse.JWT_REFRESH_INVALID);
        }

        Instant expiryTime = jwtTokenProvider.getExpireDate(token);
        if(expiryTime.isBefore(Instant.now())){
            throw new AppRuntimeException(ErrorResponse.JWT_EXPIRED);
        }

        String username = jwtTokenProvider.getUsername(token);
        UUID jwtID = jwtTokenProvider.getJwtID(token);
        InvalidToken jwtInvalid = InvalidToken.builder()
                .expr(expiryTime)
                .id(jwtID)
                .username(username)
                .build();

        invalidTokenRepository.save(jwtInvalid);
    }

    @Override
    public AuthenResponse refreshToken(RefreshRequest request) {
        String token = request.getRefreshToken();
        UUID jwtID = jwtTokenProvider.getJwtID(token);
        if(!jwtTokenProvider.verify(token) || invalidTokenRepository.existsById(jwtID) ) {
            throw new AppRuntimeException(ErrorResponse.JWT_INVALID);
        }
        boolean isRefreshToken = jwtTokenProvider.getTokenType(token)
                .equals(TokenType.refresh);
        if(!isRefreshToken) {
            throw new AppRuntimeException(ErrorResponse.JWT_REFRESH_INVALID);
        }
        String username = jwtTokenProvider.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String newToken = jwtTokenProvider.createAccessToken(userDetails);
        Instant expr = jwtTokenProvider.getExpireDate(newToken);

        return AuthenResponse.builder()
                .token(token)
                .expiresIn(expr)
                .build();
    }

}

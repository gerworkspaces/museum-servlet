package net.blwsmartware.museum.security;

import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.exception.JwtAuthException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@Slf4j
public class JwtCustomDecoder implements JwtDecoder {


    @Override
    public Jwt decode(String token) throws JwtException {

        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return new Jwt(token,signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
                    signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(),
                    signedJWT.getHeader().toJSONObject(),
                    signedJWT.getJWTClaimsSet().getClaims()
                    );
        } catch (ParseException e) {
            throw new JwtAuthException("JwtCustomDecoder decode failed",new Exception("JWT_INVALID"));
        }

    }
}

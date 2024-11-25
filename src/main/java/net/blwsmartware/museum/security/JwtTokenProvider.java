package net.blwsmartware.museum.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.constant.TokenType;
import net.blwsmartware.museum.enums.ErrorResponse;
import net.blwsmartware.museum.exception.AppRuntimeException;
import net.blwsmartware.museum.exception.JwtAuthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.function.Function;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@AllArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    String secretKey;

    @Value("${jwt.expiration-minutes}")
    long accessTokenExpr;

    @Value("${jwt.expiration-day}")
    long refreshTokenExpr;

    public String createRefreshToken(UserDetails userDetails) {
        try {
            return createJWT(userDetails, new Date(System.currentTimeMillis() + refreshTokenExpr * 60 * 60 * 24 * 1000), TokenType.refresh);
        } catch (JOSEException e) {
            log.error("createRefreshToken error");
            throw new AppRuntimeException(ErrorResponse.JWT_INVALID);
        }
    }

    private String createJWT(UserDetails userDetails, Date expr, String type) throws JOSEException {

        CustomUserDetails userCustom = (CustomUserDetails) userDetails;
        String jwtId = UUID.randomUUID().toString();
        String scope = "REFRESH_TOKEN";
        if(type.equals(TokenType.access)) {
            scope = buildRoleScope(userCustom);
        }
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer("Blw")
                .jwtID(jwtId)
                .issueTime(new Date())
                .expirationTime(expr)
                .subject(String.valueOf(userCustom.getID()))
                .claim("usn", userCustom.getUsername())
                .claim("type", type)
                .claim("scope", scope)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSHeader header2load = new JWSHeader.Builder(JWSAlgorithm.HS256).type(new JOSEObjectType("JWT")).build();
        JWSObject object = new JWSObject(header2load, payload);

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        object.sign(new MACSigner(secretKeySpec));
        return object.serialize();
    }

    public String createAccessToken(UserDetails userDetails) {
        try {
            return createJWT(userDetails, new Date(System.currentTimeMillis() + accessTokenExpr * 60 * 1000), TokenType.access);
        } catch (JOSEException e) {
            log.error("createAccessToken error");
            throw new AppRuntimeException(ErrorResponse.JWT_INVALID);
        }
    }


    public Boolean verify(String token) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            JWSVerifier jwsVerifier = new MACVerifier(secretKeySpec);
            SignedJWT signedJWT = SignedJWT.parse(token);

            Instant exp = signedJWT.getJWTClaimsSet().getExpirationTime().toInstant();
            try {
                if (exp.isBefore(Instant.now())) {
                    throw new JwtException("JWT_EXPIRED");
                }
            }catch (JwtException e) {
                throw new JwtAuthException("Token has expired! ", e);
            }
            return signedJWT.verify(jwsVerifier);

        } catch (ParseException | JOSEException e) {
            log.info("Exception parse: {}", e.getMessage());
            throw new AppRuntimeException(ErrorResponse.JWT_INVALID);
        }
    }

    private String buildRoleScope(UserDetails user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!user.getAuthorities().isEmpty()) {
            user.getAuthorities().forEach(role -> {
                stringJoiner.add(role.getAuthority());
            });
        }
        return stringJoiner.toString();
    }

    public UUID getJwtID(String token) {
        return UUID.fromString(getClaimFromToken(token, JWTClaimsSet::getJWTID));
    }

    public String getUID(String token) {
        return getClaimFromToken(token, JWTClaimsSet::getSubject);
    }
    public String getTokenType(String token) {
        return getClaimFromToken(token, jwtClaimsSet -> jwtClaimsSet.getClaim("type") ).toString();
    }
    public String getUsername(String token) {
        return getClaimFromToken(token, jwtClaimsSet -> jwtClaimsSet.getClaim("usn") ).toString();
    }

    public Instant getExpireDate(String token) {
        return getClaimFromToken(token, JWTClaimsSet::getExpirationTime).toInstant();
    }

    public <T> T getClaimFromToken(String token, Function<JWTClaimsSet, T> claimsResolver) {

        try {
            JWTClaimsSet claimsSet = SignedJWT.parse(token).getJWTClaimsSet();
            return claimsResolver.apply(claimsSet);
        } catch (ParseException e) {
            throw new AppRuntimeException(ErrorResponse.JWT_INVALID);
        }


    }
}
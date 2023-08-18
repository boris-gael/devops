package com.test.devops.service.impl;

import com.test.devops.exception.DevopsExeption;
import com.test.devops.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final int ZERO = 0;
    private final int ONE = 1;

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> authenticate(String basicAuth) throws DevopsExeption {
        String[] credentials = new String(Base64.decodeBase64(basicAuth.substring(6))).split(":");
        return getIdToken(credentials[ZERO], credentials[ONE]);
    }

    @Override
    public Map<String, String> refreshToken(String tokenRefresh) throws DevopsExeption {
        Jwt jwt = jwtDecoder.decode(tokenRefresh.substring(7));
        return getIdToken(jwt.getSubject());
    }

    private Map<String, String> getIdToken(String... credentials) throws DevopsExeption {
        if (credentials.length == ZERO) {
            throw new DevopsExeption("User credentials is empty.");
        }
        String username = credentials[ZERO];
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String password = credentials.length > ONE ? credentials[ONE] : userDetails.getPassword();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities())
        );
        String accessToken = jwtEncoder.encode(getJwtEncoderParams(authentication, 5)).getTokenValue();
        String refreshToken = jwtEncoder.encode(getJwtEncoderParams(authentication, 30)).getTokenValue();
        return Map.of("refreshToken", refreshToken, "accessToken", accessToken);
    }

    private JwtEncoderParameters getJwtEncoderParams(Authentication authentication, long expiredAmount) {
        String scope = authentication.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(" "));
        Instant now = Instant.now();
        return JwtEncoderParameters.from(
                JwtClaimsSet.builder()
                        .subject(authentication.getName())
                        .issuedAt(now)
                        .expiresAt(now.plus(expiredAmount, ChronoUnit.MINUTES))
                        .claim("scope", scope)
                        .build()
        );
    }

}

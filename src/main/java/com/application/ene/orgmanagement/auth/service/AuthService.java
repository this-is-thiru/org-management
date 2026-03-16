package com.application.ene.orgmanagement.auth.service;


import com.application.ene.orgmanagement.auth.dto.AuthHelper;
import com.application.ene.orgmanagement.auth.dto.LoginResponse;
import com.application.ene.orgmanagement.auth.dto.RegistrationRequest;
import com.application.ene.orgmanagement.auth.entity.UserDetail;
import com.application.ene.orgmanagement.auth.repository.UserDetailsRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsRepository userDetailsRepo;

    public String addUser(RegistrationRequest request) {

        Optional<UserDetail> optionalUserDetails = userDetailsRepo.findByClientIdAndEmail(request.getClientId(), request.getEmail());
        if (optionalUserDetails.isPresent()) {
            throw new IllegalArgumentException("User with email " + request.getEmail() + " already exists");
        }

        String userId = createClientId(request.getClientId());
        UserDetail userEntity = new UserDetail();
        userEntity.setClientId(request.getClientId());
        userEntity.setUserId(userId);
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setRoles(request.getRole().name());
        userDetailsRepo.save(userEntity);
        return userId;
    }

    private String createClientId(String clientId) {
        String userId = LocalDate.now().getYear() + "";
        userId += clientId.substring(0, 4);
        String upperCase = UUID.randomUUID().toString().substring(0, 18).toUpperCase().replaceAll("-", "");
        String tempUserId = userId + upperCase;

        Optional<UserDetail> userDetails = userDetailsRepo.findByUserId(tempUserId);
        if (userDetails.isPresent()) {
            return createClientId(clientId);
        }
        return tempUserId;
    }


    /**
     * This needs to be re-visited
     * This method upgrades the role of a user in the system
     *
     * @param request The object containing the email and the role to be upgraded
     * @return a success message
     * @throws IllegalArgumentException if the user already exists
     */
    public String upgradeRole(String userId, RegistrationRequest request) {
        Optional<UserDetail> optionalUserDetails = userDetailsRepo.findByUserId(userId);
        if (optionalUserDetails.isEmpty()) {
            throw new IllegalArgumentException("User with userId " + userId + " not exists");
        }

        Optional<UserDetail> optionalNewUserDetails = userDetailsRepo.findByUserId(request.getNewUserId());
        if (optionalNewUserDetails.isEmpty()) {
            throw new IllegalArgumentException("User with userId " + request.getNewUserId() + " not exists");
        }

        UserDetail userEntity = optionalNewUserDetails.get();
        boolean canUpgradeRole = AuthHelper.canUpgradeRole(userEntity.getRoles(), request.getRole());
        if (canUpgradeRole) {
            String oldRoles = userEntity.getRoles();
            String roles = oldRoles + "," + request.getRole().name();
            userEntity.setRoles(roles);
            userDetailsRepo.save(userEntity);
            return "Role: " + request.getRole() + "added for the user: " + userId;
        }
        return "Not eligible for the role upgrade.";
    }

    /**
     * <a href="https://www.grc.com/passwords.htm">Token Website</a>
     */
    final private static String SECRET = "80DC002A54B59ACF5F198D0A8D644EEE992C04FFBCF947DAAA90AA7DFDDA2A05";

    public Claims extractAllClaims(String authToken) {
        try {
            return Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(authToken)
                    .getPayload();
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Invalid JWT token");
        }

        return null;
    }

    public Boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public Boolean validateToken(Claims claims) {
        return !isTokenExpired(claims);
    }

    public String changePassword(String email, RegistrationRequest request) {

        if (!Objects.equals(email, request.getEmail())) {
            throw new IllegalArgumentException("Email mismatch");
        }

        if (Objects.equals(request.getPassword(), request.getNewPassword())) {
            throw new IllegalArgumentException("Old password cannot be same as new password");
        }

        Optional<UserDetail> optionalUserDetails = userDetailsRepo.findByEmail(email);
        if (optionalUserDetails.isEmpty()) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }

        UserDetail userEntity = optionalUserDetails.get();
        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("Invalid old password");
        }
        userEntity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userDetailsRepo.save(userEntity);
        return email + "'s password changed successfully";
    }

    public static LoginResponse generateToken(String username, Authentication authentication) {
        return createToken(username, authentication);
    }

    private static LoginResponse createToken(String username, Authentication authentication) {
        int expirationTime = 60 * 30;
        Map<String, Object> claims = new HashMap<>();
        AuthHelper.setRoles(claims, authentication.getAuthorities());
        Date expiration = new Date(System.currentTimeMillis() + 1000 * expirationTime);

        String token = Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiration)
                .signWith(getSignInKey())
                .compact();

        return LoginResponse.from(token, username, expirationTime);
    }

    private static SecretKey getSignInKey() {
        byte[] bytes = Base64.getDecoder().decode(SECRET);
        return new SecretKeySpec(bytes, "HmacSHA256");
    }
}
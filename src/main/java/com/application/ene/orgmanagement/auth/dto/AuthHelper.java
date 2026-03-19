package com.application.ene.orgmanagement.auth.dto;

import com.application.ene.orgmanagement.common.util.TJsonMapper;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthHelper {
    private static final String AUTHORITIES_KEY = "authorities";

    public enum Role {
        SUPER_USER,
        ADMIN,
        MANAGER,
        EDITOR,
        AUTHOR,
        MODERATOR,
        USER,
        GUEST,
        TEST_USER
    }

    private static final Map<Role, List<Role>> roleHierarchy = new HashMap<>();

    static {
        roleHierarchy.put(Role.SUPER_USER, List.of(Role.ADMIN, Role.MANAGER, Role.EDITOR, Role.AUTHOR, Role.MODERATOR, Role.USER, Role.GUEST));
        roleHierarchy.put(Role.ADMIN, List.of(Role.MANAGER, Role.EDITOR, Role.AUTHOR, Role.MODERATOR, Role.USER, Role.GUEST));
        roleHierarchy.put(Role.MANAGER, List.of(Role.EDITOR, Role.AUTHOR, Role.MODERATOR, Role.USER, Role.GUEST));
        roleHierarchy.put(Role.EDITOR, List.of(Role.AUTHOR, Role.MODERATOR, Role.USER, Role.GUEST));
    }

    public static boolean canUpgradeRole(String currentPersonRoles, Role newPersonRole) {
        var currentPersonRolesTemp = currentPersonRoles.split(",");
        for (String role: currentPersonRolesTemp) {
            boolean canUpgrade = canUpgradeRole(Role.valueOf(role), newPersonRole);
            if (canUpgrade) {
                return true;
            }
        }
        return false;
    }

    private static boolean canUpgradeRole(Role currentPersonRole, Role newPersonRole) {
        List<Role> hierarchy = roleHierarchy.getOrDefault(currentPersonRole, List.of());
        return hierarchy.contains(newPersonRole);
    }

    public static String getRole(Role role) {
        return role.name();
    }

    public static List<GrantedAuthority> getAuthorities(Claims claims) {

        List<String> roles = TJsonMapper.readAsList(claims.get(AUTHORITIES_KEY), String.class);
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public static void setRoles(Map<String, Object> claims, Collection<? extends GrantedAuthority> authorities) {

        var userAuthorities = authorities.stream().map(GrantedAuthority::getAuthority).toList();
        claims.put(AUTHORITIES_KEY, userAuthorities);
    }
}

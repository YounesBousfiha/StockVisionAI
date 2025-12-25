package com.jartiste.stockvisionai.infrastructure.security.json;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class AdminAccessFilter {

    @Override
    public boolean equals(Object value) {

        if(null == value) return true;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        return !isAdmin;
    }

    @Override
    public int hashCode() {
        return AdminAccessFilter.class.hashCode();
    }

}
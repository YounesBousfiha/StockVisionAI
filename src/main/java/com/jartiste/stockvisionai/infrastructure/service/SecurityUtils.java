package com.jartiste.stockvisionai.infrastructure.service;

import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.domain.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AutheticatedUser) {
            return ((AutheticatedUser) authentication.getPrincipal()).getUser();
        }
        return null;
    }

    public String getCurrentUserId() {
        User user = getCurrentUser();
        return user != null ? user.getId() : null;
    }

    public String getCurrentUserEntrepotId() {
        User user = getCurrentUser();
        return user != null ? user.getEntrepotId() : null;
    }

    public boolean isAdmin() {
        User user = getCurrentUser();
        return user != null && user.getRole() == Role.ADMIN;
    }

    public boolean isGestionnaire() {
        User user = getCurrentUser();
        return user != null && user.getRole() == Role.GESTIONNAIRE;
    }

    public boolean hasAccessToEntrepot(String entrepotId) {
        if (isAdmin()) {
            return true; // Admin has access to all entrepots
        }
        if (isGestionnaire()) {
            String userEntrepotId = getCurrentUserEntrepotId();
            return userEntrepotId != null && userEntrepotId.equals(entrepotId);
        }
        return false;
    }
}

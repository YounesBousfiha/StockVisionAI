package com.jartiste.stockvisionai.infrastructure.security.guard;

import com.jartiste.stockvisionai.domain.repository.StockRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("stockGuard")
public class StockSecurity {

/*    private final StockRepository stockRepository;

    public StockSecurity(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public boolean isOwner(Authentication authentication, String stockId) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin) return true;

    }*/
}

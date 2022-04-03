package app.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountService {
    void withdrawMoney(BigDecimal moneyAmount, Long id);
    void transferMoney(BigDecimal moneyAmount, Long id);
}

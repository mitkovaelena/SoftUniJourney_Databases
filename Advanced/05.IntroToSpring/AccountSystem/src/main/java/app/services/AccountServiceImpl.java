package app.services;

import app.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import app.repositories.AccountRepository;

import java.math.BigDecimal;

@Service
@Primary  //?
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;


    @Autowired //valid for all fields in the constructor
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal moneyAmount, Long id) {
        if (!accountRepository.exists(id)) {
                throw new IllegalArgumentException("There is no account with the provided id");
        }
        if (moneyAmount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Cannot transfer negative amount of money");
        }

        Account acc = accountRepository.findOne(id);
        acc.setBalance(acc.getBalance().subtract(moneyAmount));
        accountRepository.save(acc);
    }

    @Override
    public void transferMoney(BigDecimal moneyAmount, Long id) {
        if (!accountRepository.exists(id)) {
            throw new IllegalArgumentException("There is no account with the provided id");
        }

        if (moneyAmount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Cannot transfer negative amount of money");
        }

        Account acc = accountRepository.findOne(id);
        acc.setBalance(acc.getBalance().add(moneyAmount));
        accountRepository.save(acc);
    }
}

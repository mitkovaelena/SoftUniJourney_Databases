package app;

import app.models.Account;
import app.models.User;
import app.repositories.AccountRepository;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import app.services.AccountServiceImpl;
import app.services.UserServiceImpl;

import java.math.BigDecimal;
import java.util.Collections;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserServiceImpl userService;
    private AccountServiceImpl accountService;
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService, UserRepository userRepository, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        User example = new User();
        example.setUsername("example");
        example.setAge(20);

        Account acc1 = new Account();
        acc1.setBalance(new BigDecimal(2000));
        acc1.setUser(example);


        example.setAccounts(Collections.singletonList(acc1));
        userService.registerUser(example);

        System.out.println(userRepository.findOne(1L).getUsername());
        System.out.println(accountRepository.findOne(1L).getUser().getUsername());

        accountService.withdrawMoney(new BigDecimal(20), 1L);
        accountService.transferMoney(new BigDecimal(100), 1L);

        accountService.withdrawMoney(new BigDecimal(-5000), 1L);

    }
}

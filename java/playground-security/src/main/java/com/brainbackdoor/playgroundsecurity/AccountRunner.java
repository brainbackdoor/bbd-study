package com.brainbackdoor.playgroundsecurity;

import com.brainbackdoor.playgroundsecurity.account.Account;
import com.brainbackdoor.playgroundsecurity.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account bbd = accountService.createAccount("bbd", "1234");
        System.out.println(bbd.getUsername() + " password: " + bbd.getPassword());
    }
}

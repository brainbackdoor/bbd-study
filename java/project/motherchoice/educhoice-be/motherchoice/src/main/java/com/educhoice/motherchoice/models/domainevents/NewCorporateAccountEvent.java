package com.educhoice.motherchoice.models.domainevents;

import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewCorporateAccountEvent extends ApplicationEvent {

    private long accountId;
    private String originalName;

    public NewCorporateAccountEvent(CorporateAccount account) {
        super(account);
        this.accountId = account.getAccountId();
        this.originalName = account.getAccountName();
    }
}

package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.notifications.NewQuestionStore;
import com.educhoice.motherchoice.models.persistent.qna.Question;
import com.educhoice.motherchoice.models.persistent.repositories.notifications.NewQuestionStoreRepository;
import com.educhoice.motherchoice.utils.exceptions.entity.NoAcademyIdException;
import com.educhoice.motherchoice.utils.exceptions.security.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewQuestionStoreService {

    @Autowired
    private NewQuestionStoreRepository repository;

    public void saveNewQuestionStore(long accountId) {
        NewQuestionStore store = new NewQuestionStore();
        store.setCorporateAccountId(accountId);

        repository.save(store);
    }

    public double getAverageInquiryResponseRate(Academy academy) {
        return findQuestionStore(academy.getCorporateAccount()).averageInquiryResponseRate();
    }

    public boolean hasNewQuestions(CorporateAccount account) {
        return findQuestionStore(account).hasNewQuestion();
    }

    public void readQuestion(CorporateAccount account, Question question) {
        NewQuestionStore questionStore = findQuestionStore(account);
        questionStore.checkReadQuestion(question);

        this.repository.save(questionStore);
    }

    private NewQuestionStore findQuestionStore(CorporateAccount account) {
        return repository.findByCorporateAccountId(account.getAccountId()).orElseThrow(() -> new UsernameNotFoundException("회원 정보가 검색되지 않았습니다."));
    }
}

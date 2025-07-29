package com.paw.fund.core.service.domain.form;

import com.paw.fund.core.service.domain.form.question.IQuestionUseCase;
import com.paw.fund.core.service.domain.form.question.Question;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import com.paw.fund.core.service.domain.login.info.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class FormPrivateService {
    @Autowired
    IQuestionUseCase questionUseCase;

    @Autowired
    ILoginInfoUseCase loginInfoUseCase;

    protected void saveQuestions(Long formId, List<Question> questions) {
        questionUseCase.save(formId, questions);
    }

    protected List<Question> questions(Long formId) {
        return questionUseCase.findAllByFormId(formId);
    }

    protected void updateQuestions(Long formId, List<Question> questions) {
        questionUseCase.update(formId, questions);
    }

    protected LoginAccount loginAccount() {
        return loginInfoUseCase.getCurrentAccountLogin()
                .orElse(LoginAccount.empty());
    }

}

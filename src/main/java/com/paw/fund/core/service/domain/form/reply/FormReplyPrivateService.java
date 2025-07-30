package com.paw.fund.core.service.domain.form.reply;

import com.paw.fund.core.service.domain.form.answer.Answer;
import com.paw.fund.core.service.domain.form.answer.IAnswerUseCase;
import com.paw.fund.core.service.domain.login.info.ILoginInfoUseCase;
import com.paw.fund.core.service.domain.login.info.LoginAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class FormReplyPrivateService {
    @Autowired
    IAnswerUseCase answerUseCase;

    @Autowired
    ILoginInfoUseCase loginInfoUseCase;

    protected void saveAnswers(Long formReplyId, List<Answer> answers) {
        answerUseCase.save(formReplyId, answers);
    }

    protected List<Answer> answers(Long formReplyId) {
        return answerUseCase.findAllByFormId(formReplyId);
    }

    protected void updateAnswers(Long formReplyId, List<Answer> answers) {
        answerUseCase.update(formReplyId, answers);
    }

    protected LoginAccount loginAccount() {
        return loginInfoUseCase.getCurrentAccountLogin()
                .orElse(LoginAccount.empty());
    }
}

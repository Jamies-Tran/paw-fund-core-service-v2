package com.paw.fund.core.service.domain.form.reply;

import com.paw.fund.core.service.domain.form.answer.Answer;
import lombok.Builder;

import java.util.List;

@Builder
public record FormReplyDetail(
        Long formReplyId,
        String title,
        String description,
        List<AnswerDetail> answers
) {
    @Builder
    public record AnswerDetail(
            String questionText,
            String answerText
    ) {
        public static List<AnswerDetail> of(List<Answer> answers) {
            return answers.stream()
                    .map(answer -> AnswerDetail.builder()
                            .questionText(answer.answer().questionText())
                            .answerText(answer.answer().answerText())
                            .build())
                    .toList();
        }
    }

    public static FormReplyDetail of(FormReply formReply) {
        return FormReplyDetail.builder()
                .formReplyId(formReply.formReplyId())
                .title(formReply.title())
                .description(formReply.description())
                .answers(AnswerDetail.of(formReply.answers()))
                .build();
    }
}

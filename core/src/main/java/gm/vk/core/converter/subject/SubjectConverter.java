package gm.vk.core.converter.subject;

import gm.vk.core.converter.subject.examination.ExaminationConverter;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.dto.subject.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("subjectConverter")
public class SubjectConverter implements Function<Subject, SubjectDto> {

    @Autowired
    private ExaminationConverter examinationConverter;

    @Override
    public SubjectDto apply(final Subject subject) {
        return new SubjectDto(subject.getId(), subject.getName(), examinationConverter.apply(subject.getExamination()));
    }
}
package gm.vk.core.converter.subject;

import gm.vk.core.converter.subject.examination.ExaminationDtoConverter;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.dto.subject.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("subjectDtoConverter")
public class SubjectDtoConverter implements Function<SubjectDto, Subject> {

    @Autowired
    private ExaminationDtoConverter examinationDtoConverter;

    @Override
    public Subject apply(final SubjectDto subjectDto) {
        return new Subject(subjectDto.getId(), subjectDto.getName(), examinationDtoConverter.apply(subjectDto.getExamination()));
    }
}
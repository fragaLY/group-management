package gm.vk.core.converter.subject;

import gm.vk.core.converter.group.GroupConverter;
import gm.vk.core.converter.person.PersonConverter;
import gm.vk.core.converter.subject.examination.ExaminationConverter;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.dto.subject.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("subjectConverter")
public class SubjectConverter implements Function<Subject, SubjectDto> {

    @Autowired
    private ExaminationConverter examinationConverter;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private GroupConverter groupConverter;

    @Override
    public SubjectDto apply(@NotNull final Subject subject) {
        return new SubjectDto(subject.getId(), subject.getName(), examinationConverter.apply(subject.getExamination()), subject.getPersons().stream().map(personConverter).collect(Collectors.toSet()), subject.getGroups().stream().map(groupConverter).collect(Collectors.toSet()));
    }
}
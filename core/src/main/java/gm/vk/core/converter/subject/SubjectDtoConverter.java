package gm.vk.core.converter.subject;

import gm.vk.core.converter.group.GroupDtoConverter;
import gm.vk.core.converter.person.PersonDtoConverter;
import gm.vk.core.converter.subject.examination.ExaminationDtoConverter;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.dto.subject.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("subjectDtoConverter")
public class SubjectDtoConverter implements Function<SubjectDto, Subject> {

    @Autowired
    private ExaminationDtoConverter examinationDtoConverter;

    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Autowired
    private GroupDtoConverter groupDtoConverter;

    @Override
    public Subject apply(@NotNull final SubjectDto subjectDto) {
        return new Subject(subjectDto.getId(), subjectDto.getName(), examinationDtoConverter.apply(subjectDto.getExamination()), subjectDto.getPersons().stream().map(personDtoConverter).collect(Collectors.toSet()), subjectDto.getGroups().stream().map(groupDtoConverter).collect(Collectors.toSet()));
    }
}
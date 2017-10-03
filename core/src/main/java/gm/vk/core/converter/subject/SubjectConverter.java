package gm.vk.core.converter.subject;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.subject.SubjectDto;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("subjectConverter")
public class SubjectConverter implements Function<Subject, SubjectDto> {

  @Override
  public SubjectDto apply(@NotNull final Subject subject) {
    final CustomPersonConverter customPersonConverter = new CustomPersonConverter();
    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();

    return new SubjectDto(
        subject.getId(),
        subject.getName(),
        new CustomExaminationConverter().apply(subject.getExamination()),
        subject.getPersons().stream().map(customPersonConverter).collect(Collectors.toSet()),
        subject.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<Examination, ExaminationDto> {

    @Override
    public ExaminationDto apply(Examination examination) {
      return new ExaminationDto(
          examination.getId(),
          new ExaminationTypeDto(examination.getType().getId(), examination.getType().getType()),
          new GradeDto(examination.getGrade().getId(), examination.getGrade().getGrade()));
    }
  }

  private class CustomPersonConverter implements Function<Person, PersonDto> {

    @Override
    public PersonDto apply(Person person) {
      return new PersonDto.Builder().setId(person.getId()).build();
    }
  }

  private class CustomGroupConverter implements Function<Group, GroupDto> {

    @Override
    public GroupDto apply(Group group) {
      return new GroupDto.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}

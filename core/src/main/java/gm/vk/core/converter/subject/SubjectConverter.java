package gm.vk.core.converter.subject;

import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.group.Group;
import gm.vk.core.domain.person.Person;
import gm.vk.core.domain.subject.Subject;
import gm.vk.core.domain.subject.examination.Examination;
import gm.vk.core.domain.subject.examination.grade.Grade;
import gm.vk.core.domain.subject.examination.type.ExaminationType;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.core.dto.person.PersonDto;
import gm.vk.core.dto.subject.SubjectDto;
import gm.vk.core.dto.subject.examination.ExaminationDto;
import gm.vk.core.dto.subject.examination.grade.GradeDto;
import gm.vk.core.dto.subject.examination.type.ExaminationTypeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("subjectConverter")
public class SubjectConverter implements Function<Subject, SubjectDto> {

  private static final Logger LOG = LoggerFactory.getLogger(SubjectConverter.class);

  @Override
  public SubjectDto apply(@NotNull final Subject subject) {

    LOG.info("Converts Subject [{}] to SubjectDto", subject);

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

      final ExaminationType type = examination.getType();
      final Grade grade = examination.getGrade();

      Integer gradeId = null;
      Integer gradeValue = null;

      if (grade != null) {
        gradeId = grade.getId();
        gradeValue = grade.getGrade();
      }

      return new ExaminationDto(
          examination.getId(),
              new ExaminationTypeDto(type.getId(), type.getType()),
              new GradeDto(gradeId, gradeValue));
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

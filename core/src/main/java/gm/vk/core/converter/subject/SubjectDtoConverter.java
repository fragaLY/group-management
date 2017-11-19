package gm.vk.core.converter.subject;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("subjectDtoConverter")
public class SubjectDtoConverter implements Function<SubjectDto, Subject> {

  private static final Logger LOG = LoggerFactory.getLogger(SubjectDtoConverter.class);

  /**
   * Converts {@link SubjectDto} to {@link Subject}
   *
   * @param subjectDto - the {@link SubjectDto}
   * @return {@link Subject}
   */
  @Override
  public Subject apply(@NotNull final SubjectDto subjectDto) {

    LOG.info("Converts SubjectDto [{}] to Subject", subjectDto);

    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();
    final CustomPersonConverter customPersonConverter = new CustomPersonConverter();

    return new Subject(
        subjectDto.getSubjectId(),
        subjectDto.getName(),
        new CustomExaminationConverter().apply(subjectDto.getExamination()),
        subjectDto.getPersons().stream().map(customPersonConverter).collect(Collectors.toSet()),
        subjectDto.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<ExaminationDto, Examination> {

      @Override
      public Examination apply(ExaminationDto examination) {
      return new Examination(
          examination.getExaminationId(),
              new ExaminationType(
                      examination.getType().getExaminationTypeId(), examination.getType().getType()),
          new Grade(examination.getGrade().getGradeId(), examination.getGrade().getGrade()));
    }
  }

  private class CustomPersonConverter implements Function<PersonDto, Person> {

      @Override
      public Person apply(PersonDto person) {
      return new Person.Builder().setId(person.getPersonId()).build();
    }
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

      @Override
      public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getGroupId()).setName(group.getName()).build();
    }
  }
}

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("subjectDtoConverter")
public class SubjectDtoConverter implements Function<SubjectDto, Subject> {

  private static final Logger LOG = LoggerFactory.getLogger(SubjectDtoConverter.class);

  @Override
  public Subject apply(@NotNull final SubjectDto subjectDto) {

    LOG.info("Converts SubjectDto [{}] to Subject", subjectDto);

    final CustomGroupConverter customGroupConverter = new CustomGroupConverter();
    final CustomPersonConverter customPersonConverter = new CustomPersonConverter();

    return new Subject(
        subjectDto.getId(),
        subjectDto.getName(),
        new CustomExaminationConverter().apply(subjectDto.getExamination()),
        subjectDto.getPersons().stream().map(customPersonConverter).collect(Collectors.toSet()),
        subjectDto.getGroups().stream().map(customGroupConverter).collect(Collectors.toSet()));
  }

  private class CustomExaminationConverter implements Function<ExaminationDto, Examination> {

    @Override
    public Examination apply(ExaminationDto examination) {
      return new Examination(
          examination.getId(),
          new ExaminationType(examination.getType().getId(), examination.getType().getType()),
          new Grade(examination.getGrade().getId(), examination.getGrade().getGrade()));
    }
  }

  private class CustomPersonConverter implements Function<PersonDto, Person> {

    @Override
    public Person apply(PersonDto person) {
      return new Person.Builder().setId(person.getId()).build();
    }
  }

  private class CustomGroupConverter implements Function<GroupDto, Group> {

    @Override
    public Group apply(GroupDto group) {
      return new Group.Builder().setId(group.getId()).setName(group.getName()).build();
    }
  }
}

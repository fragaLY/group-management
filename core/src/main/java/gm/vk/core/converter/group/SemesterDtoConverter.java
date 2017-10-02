package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Semester;
import gm.vk.core.dto.group.SemesterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("semesterDtoConverter")
public class SemesterDtoConverter implements Function<SemesterDto, Semester> {

    @Autowired
    private GroupDtoConverter groupDtoConverter;

    @Override
    public Semester apply(SemesterDto semesterDto) {
        return new Semester(semesterDto.getId(), semesterDto.getSemester(), semesterDto.getGroups().stream().map(groupDtoConverter).collect(Collectors.toSet()));
    }
}
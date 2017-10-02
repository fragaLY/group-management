package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Semester;
import gm.vk.core.dto.group.SemesterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("semesterConverter")
public class SemesterConverter implements Function<Semester, SemesterDto> {

    @Autowired
    private GroupConverter groupConverter;

    @Override
    public SemesterDto apply(Semester semester) {
        return new SemesterDto(semester.getId(), semester.getSemester(), semester.getGroups().stream().map(groupConverter).collect(Collectors.toSet()));
    }
}
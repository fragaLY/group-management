package gm.vk.core.converter.group.faculty;

import gm.vk.core.converter.group.GroupConverter;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.faculty.FacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("facultyConverter")
public class FacultyConverter implements Function<Faculty, FacultyDto>{

    @Autowired
    private GroupConverter groupConverter;

    @Override
    public FacultyDto apply(Faculty faculty) {
        return new FacultyDto(faculty.getId(), faculty.getFaculty(), faculty.getGroups().stream().map(groupConverter).collect(Collectors.toSet()));
    }
}
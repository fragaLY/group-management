package gm.vk.core.converter.group.faculty;

import gm.vk.core.converter.group.GroupDtoConverter;
import gm.vk.core.domain.group.faculty.Faculty;
import gm.vk.core.dto.group.faculty.FacultyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component("facultyDtoConverter")
public class FacultyDtoConverter implements Function<FacultyDto, Faculty> {

    @Autowired
    private GroupDtoConverter groupDtoConverter;

    @Override
    public Faculty apply(FacultyDto facultyDto) {
        return new Faculty(facultyDto.getId(), facultyDto.getFaculty(), facultyDto.getGroups().stream().map(groupDtoConverter).collect(Collectors.toSet()));
    }
}
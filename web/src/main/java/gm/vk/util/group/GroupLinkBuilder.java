package gm.vk.util.group;

import gm.vk.controllers.group.GroupController;
import gm.vk.controllers.group.SemesterController;
import gm.vk.controllers.group.course.CourseController;
import gm.vk.controllers.group.faculty.FacultyController;
import gm.vk.core.dto.group.GroupDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class GroupLinkBuilder implements Consumer<GroupDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param group the input argument
     */
    @Override
    public void accept(@NotNull final GroupDto group) {

        group.add(
                linkTo(methodOn(GroupController.class).getGroups())
                        .slash(group.getGroupId())
                        .withSelfRel());

        group.add(
                linkTo(methodOn(CourseController.class).getCourses())
                        .slash(group.getCourse().getCourseId())
                        .withRel("course"));

        group.add(
                linkTo(methodOn(SemesterController.class).getSemesters())
                        .slash(group.getSemester().getSemesterId())
                        .withRel("semester"));

        group.add(
                linkTo(methodOn(FacultyController.class).getFaculties())
                        .slash(group.getFaculty().getFacultyId())
                        .withRel("faculty"));
    }
}

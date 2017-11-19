package gm.vk.util.group.course;

import gm.vk.controllers.group.course.CourseController;
import gm.vk.core.dto.group.course.CourseDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CourseLinkBuilder implements Consumer<CourseDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param course the input argument
     */
    @Override
    public void accept(@NotNull final CourseDto course) {

        course.add(
                linkTo(methodOn(CourseController.class).getCourses())
                        .slash(course.getCourseId())
                        .withSelfRel());
    }
}

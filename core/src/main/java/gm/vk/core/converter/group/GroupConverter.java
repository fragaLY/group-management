package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Group;
import gm.vk.core.dto.group.GroupDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("groupConverter")
public class GroupConverter implements Function<Group, GroupDto> {

    @Override
    public GroupDto apply(Group group) {
        return new GroupDto(); //todo vk: complete injection
    }
}
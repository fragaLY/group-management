package gm.vk.core.converter.group;

import gm.vk.core.domain.group.Group;
import gm.vk.core.dto.group.GroupDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("groupDtoConverter")
public class GroupDtoConverter implements Function<GroupDto, Group> {

    @Override
    public Group apply(GroupDto groupDto) {
        return new Group();//todo vk: complete injection
    }
}
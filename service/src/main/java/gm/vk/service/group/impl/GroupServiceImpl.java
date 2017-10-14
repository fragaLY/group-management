package gm.vk.service.group.impl;

import gm.vk.core.converter.group.GroupConverter;
import gm.vk.core.converter.group.GroupDtoConverter;
import gm.vk.core.dao.group.GroupDao;
import gm.vk.core.domain.group.Group;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.exceptions.group.GroupNotFoundException;
import gm.vk.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupConverter groupConverter;

    @Autowired
    private GroupDtoConverter groupDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<GroupDto> findAll() {
        return groupDao
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(groupConverter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public GroupDto findOne(@NotNull final Integer id) {
        final Optional<Group> group = Optional.ofNullable(groupDao.findOne(id));

        if (group.isPresent()) {
            return groupConverter.apply(group.get());
        } else {
            throw new GroupNotFoundException("Group was not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public GroupDto save(@NotNull final GroupDto group) {
        final Group savedGroup = groupDao.save(groupDtoConverter.apply(group));
        return groupConverter.apply(savedGroup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final GroupDto group) {
        groupDao.delete(groupDtoConverter.apply(group));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        groupDao.delete(id);
    }
}

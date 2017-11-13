package gm.vk.service.group.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.converter.group.GroupConverter;
import gm.vk.core.converter.group.GroupDtoConverter;
import gm.vk.core.dao.group.GroupDao;
import gm.vk.core.domain.group.Group;
import gm.vk.core.dto.group.GroupDto;
import gm.vk.exceptions.group.GroupNotFoundException;
import gm.vk.service.group.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GroupConverter groupConverter;

    @Autowired
    private GroupDtoConverter groupDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<GroupDto> findAll() {

        LOG.info("Gets all Groups");

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

        LOG.info("Gets Group by id [{}]", id);

        final Optional<Group> group = Optional.ofNullable(groupDao.findOne(id));

        if (group.isPresent()) {
            return groupConverter.apply(group.get());
        } else {
            LOG.warn("Group with id [{}] was not found", id);
            throw new GroupNotFoundException("Group was not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public GroupDto save(@NotNull final GroupDto group) {
        final Group savedGroup = groupDao.save(groupDtoConverter.apply(group));
        LOG.info("Group [{}] had been saved", group);
        return groupConverter.apply(savedGroup);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final GroupDto group) {
        groupDao.delete(groupDtoConverter.apply(group));
        LOG.info("Group [{}] had been deleted", group);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        groupDao.delete(id);
        LOG.info("Group with id [{}] had been deleted", id);
    }
}

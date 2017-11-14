package gm.vk.service.group;

import java.util.List;

import gm.vk.core.dto.group.GroupDto;

public interface GroupService {

  List<GroupDto> findAll();

  GroupDto findOne(final Integer id);

  GroupDto save(final GroupDto group);

  void delete(final GroupDto group);

  void delete(final Integer id);
}

package gm.vk.service.group;

import gm.vk.core.dto.group.GroupDto;

import java.util.List;

public interface GroupService {

  List<GroupDto> findAll();

  GroupDto findOne(final Integer id);

  GroupDto save(final GroupDto group);

  void delete(final GroupDto group);

  void delete(final Integer id);
}

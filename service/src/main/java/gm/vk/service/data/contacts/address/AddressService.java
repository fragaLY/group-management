package gm.vk.service.data.contacts.address;

import gm.vk.core.dto.data.contacts.address.AddressDto;

import java.util.List;

public interface AddressService {

  List<AddressDto> findAll();

  AddressDto findOne(final Integer id);

  AddressDto save(final AddressDto address);

  void delete(final AddressDto address);

  void delete(final Integer id);
}

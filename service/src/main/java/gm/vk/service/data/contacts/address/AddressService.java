package gm.vk.service.data.contacts.address;

import java.util.List;

import gm.vk.core.dto.data.contacts.address.AddressDto;

public interface AddressService {

  List<AddressDto> findAll();

  AddressDto findOne(final Integer id);

  AddressDto save(final AddressDto address);

  void delete(final AddressDto address);

  void delete(final Integer id);
}

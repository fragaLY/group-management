package gm.vk.service.data.contacts.address.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import gm.vk.core.converter.data.contacts.address.AddressConverter;
import gm.vk.core.converter.data.contacts.address.AddressDtoConverter;
import gm.vk.core.dao.data.contacts.address.AddressDao;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import gm.vk.exceptions.data.contacts.address.AddressNotFoundException;
import gm.vk.service.data.contacts.address.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("addressService") public class AddressServiceImpl implements AddressService {

  private static final Logger LOG = LoggerFactory.getLogger(AddressServiceImpl.class);

  @Autowired private AddressDao addressDao;

  @Autowired private AddressConverter addressConverter;

  @Autowired private AddressDtoConverter addressDtoConverter;

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public List<AddressDto> findAll() {

    LOG.info("Gets all addresses");

    return addressDao.findAll().stream().filter(Objects::nonNull).map(addressConverter).collect(Collectors.toList());
  }

  @Override @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public AddressDto findOne(
      @NotNull final Integer id) {

    LOG.info("Gets address by id [{}]", id);

    final Optional<Address> address = Optional.ofNullable(addressDao.findOne(id));

    if (address.isPresent()) {
      return addressConverter.apply(address.get());
    } else {
      LOG.warn("Address with id [{}] was not found.", id);
      throw new AddressNotFoundException("Address was not found");
    }
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public AddressDto save(
      @NotNull final AddressDto address) {
    final Address savedAddress = addressDao.save(addressDtoConverter.apply(address));

    LOG.info("Address [{}] had been saved", address);

    return addressConverter.apply(savedAddress);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final AddressDto address) {
    addressDao.delete(addressDtoConverter.apply(address));
    LOG.info("Address [{}] had been deleted", address);
  }

  @Override @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class) public void delete(
      @NotNull final Integer id) {
    addressDao.delete(id);
    LOG.info("Address with id [{}] had been deleted", id);
  }

}

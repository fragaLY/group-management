package gm.vk.service.data.contacts.address.impl;

import gm.vk.core.converter.data.contacts.address.AddressConverter;
import gm.vk.core.converter.data.contacts.address.AddressDtoConverter;
import gm.vk.core.dao.data.contacts.address.AddressDao;
import gm.vk.core.domain.data.contacts.address.Address;
import gm.vk.core.dto.data.contacts.address.AddressDto;
import gm.vk.exceptions.data.contacts.address.AddressNotFoundException;
import gm.vk.service.data.contacts.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private AddressDtoConverter addressDtoConverter;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<AddressDto> findAll() {
        return addressDao.findAll().stream().filter(Objects::nonNull).map(addressConverter).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public AddressDto findOne(@NotNull final Integer id) {
        final Optional<Address> address = Optional.ofNullable(addressDao.findOne(id));

        if (address.isPresent()) {
            return addressConverter.apply(address.get());
        } else {
            throw new AddressNotFoundException("Address was not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public AddressDto save(@NotNull final AddressDto address) {
        addressDao.save(addressDtoConverter.apply(address));
        return address;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final AddressDto address) {
        addressDao.delete(addressDtoConverter.apply(address));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(@NotNull final Integer id) {
        addressDao.delete(id);
    }
}
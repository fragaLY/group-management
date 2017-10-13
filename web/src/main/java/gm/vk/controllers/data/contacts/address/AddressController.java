package gm.vk.controllers.data.contacts.address;

import gm.vk.core.dto.data.contacts.address.AddressDto;
import gm.vk.service.data.contacts.address.AddressService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/address")
@Validated
public class AddressController {

  @Autowired private AddressService addressService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<List<AddressDto>> getAddresss() {
    final List<AddressDto> address = addressService.findAll();
    return new ResponseEntity<>(address, new HttpHeaders(), HttpStatus.FOUND);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<AddressDto> getAddress(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    final AddressDto address = addressService.findOne(id);
    return new ResponseEntity<>(address, new HttpHeaders(), HttpStatus.FOUND);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createAddress(@Valid @RequestBody final AddressDto address) {

    final AddressDto createdAddress = addressService.save(address);

    final URI createdAddressUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdAddress.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(createdAddressUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> editAddress(@Valid @RequestBody final AddressDto address) {

    final AddressDto savedAddress = addressService.save(address);

    final URI editedAddressUri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedAddress.getId())
            .toUri();

    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(editedAddressUri);

    return new ResponseEntity<>(null, responseHeaders, HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<AddressDto> deleteAddress(@Valid @RequestBody final AddressDto address) {
    addressService.delete(address);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<AddressDto> deleteAddress(
      @Range(min = 1) @PathVariable("id") final Integer id) {
    addressService.delete(id);
    return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
  }
}

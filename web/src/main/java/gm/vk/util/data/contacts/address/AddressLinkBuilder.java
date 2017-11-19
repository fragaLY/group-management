package gm.vk.util.data.contacts.address;

import gm.vk.controllers.data.contacts.address.AddressController;
import gm.vk.core.dto.data.contacts.address.AddressDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AddressLinkBuilder implements Consumer<AddressDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param address the input argument
     */
    @Override
    public void accept(@NotNull final AddressDto address) {
        address.add(
                linkTo(methodOn(AddressController.class).getAddresses())
                        .slash(address.getAddressId())
                        .withSelfRel());
    }
}

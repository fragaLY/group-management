package gm.vk.util.data.contacts;

import gm.vk.controllers.data.contacts.ContactsController;
import gm.vk.controllers.data.contacts.address.AddressController;
import gm.vk.core.dto.data.contacts.ContactsDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ContactsLinkBuilder implements Consumer<ContactsDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param contacts the input argument
     */
    @Override
    public void accept(@NotNull final ContactsDto contacts) {
        contacts.add(
                linkTo(methodOn(ContactsController.class).getContacts())
                        .slash(contacts.getContactsId())
                        .withSelfRel());

        contacts.add(
                linkTo(methodOn(AddressController.class).getAddresses())
                        .slash(contacts.getAddress().getAddressId())
                        .withSelfRel());
    }
}

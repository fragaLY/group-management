package gm.vk.util.person;

import gm.vk.controllers.data.contacts.ContactsController;
import gm.vk.controllers.data.personal.PersonalDataController;
import gm.vk.controllers.group.GroupController;
import gm.vk.controllers.person.PersonController;
import gm.vk.controllers.person.role.PersonRoleController;
import gm.vk.core.dto.person.PersonDto;

import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PersonLinkBuilder implements Consumer<PersonDto> {

    /**
     * Performs this operation on the given argument.
     *
     * @param person the input argument
     */
    @Override
    public void accept(@NotNull final PersonDto person) {

        person.add(
                linkTo(methodOn(PersonController.class).getPersons())
                        .slash(person.getPersonId())
                        .withSelfRel());

        person.add(
                linkTo(methodOn(ContactsController.class).getContacts())
                        .slash(person.getContacts().getContactsId())
                        .withRel("contacts"));

        person.add(
                linkTo(methodOn(PersonalDataController.class).getAllPersonalData())
                        .slash(person.getPersonalData().getPersonalDataId())
                        .withRel("personaldata"));

        person.add(
                linkTo(methodOn(PersonRoleController.class).getPersonRoles())
                        .slash(person.getRole().getPersonRoleId())
                        .withRel("role"));

        person.add(
                linkTo(methodOn(GroupController.class).getGroups())
                        .slash(person.getGroup().getGroupId())
                        .withRel("group"));
    }
}

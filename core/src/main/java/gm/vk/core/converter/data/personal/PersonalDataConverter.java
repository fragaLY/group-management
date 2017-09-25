package gm.vk.core.converter.data.personal;

import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("personalDataConverter")
public class PersonalDataConverter implements Function<PersonalData, PersonalDataDto> {

    @Override
    public PersonalDataDto apply(final PersonalData personalData) {
        return new PersonalDataDto(personalData.getId(), personalData.getFirstName(), personalData.getSecondName());
    }

}
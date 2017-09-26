package gm.vk.core.converter.data.personal;

import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("personalDataDtoConverter")
public class PersonalDataDtoConverter implements Function<PersonalDataDto, PersonalData> {

    @Override
    public PersonalData apply(@NotNull final PersonalDataDto personalDataDto) {
        return new PersonalData(personalDataDto.getId(), personalDataDto.getFirstName(), personalDataDto.getSecondName());
    }
}
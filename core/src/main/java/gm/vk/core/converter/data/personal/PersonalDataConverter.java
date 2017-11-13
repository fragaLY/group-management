package gm.vk.core.converter.data.personal;

import java.util.function.Function;
import javax.validation.constraints.NotNull;

import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("personalDataConverter")
public class PersonalDataConverter implements Function<PersonalData, PersonalDataDto> {

  private static final Logger LOG = LoggerFactory.getLogger(PersonalDataConverter.class);

  @Override
  public PersonalDataDto apply(@NotNull final PersonalData personalData) {

    LOG.info("Converts PersonalData [{}] to PersonalDataDto", personalData);

    return new PersonalDataDto(
        personalData.getId(), personalData.getFirstName(), personalData.getSecondName());
  }
}

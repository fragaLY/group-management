package gm.vk.core.converter.data.personal;

import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("personalDataDtoConverter")
public class PersonalDataDtoConverter implements Function<PersonalDataDto, PersonalData> {

  private static final Logger LOG = LoggerFactory.getLogger(PersonalDataDtoConverter.class);

  /**
   * Converts {@link PersonalDataDto} to {@link PersonalData}
   *
   * @param personalDataDto - the {@link PersonalDataDto}
   * @return {@link PersonalData}
   */
  @Override
  public PersonalData apply(@NotNull final PersonalDataDto personalDataDto) {

    LOG.info("Converts PersonalDataDto [{}] to PersonalData", personalDataDto);

      return new PersonalData(
              personalDataDto.getPersonalDataId(),
        personalDataDto.getFirstName(),
        personalDataDto.getSecondName());
  }
}

package gm.vk.core.converter.data.personal;

import gm.vk.core.domain.data.personal.PersonalData;
import gm.vk.core.dto.data.personal.PersonalDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Component("personalDataConverter")
public class PersonalDataConverter implements Function<PersonalData, PersonalDataDto> {

  private static final Logger LOG = LoggerFactory.getLogger(PersonalDataConverter.class);

  /**
   * Converts {@link PersonalData} to {@link PersonalDataDto}
   *
   * @param personalData - the {@link PersonalData}
   * @return {@link PersonalDataDto}
   */
  @Override
  public PersonalDataDto apply(@NotNull final PersonalData personalData) {

    LOG.info("Converts PersonalData [{}] to PersonalDataDto", personalData);

      return new PersonalDataDto(
              personalData.getId(), personalData.getFirstName(), personalData.getSecondName());
  }
}

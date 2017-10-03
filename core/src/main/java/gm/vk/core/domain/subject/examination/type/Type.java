package gm.vk.core.domain.subject.examination.type;

import java.util.Arrays;
import java.util.Optional;

public enum Type {
  EXAM("EXAM"),
  CREDIT("CREDIT"),
  NOTHING("NOTHING");

  private String type;

  Type(final String type) {
    this.type = type;
  }

  public static Optional<Type> getExaminationTypeByType(final String _type) {
    return Arrays.stream(values())
        .parallel()
        .filter(type -> type.getType().equals(_type))
        .findFirst();
  }

  public String getType() {
    return type;
  }
}

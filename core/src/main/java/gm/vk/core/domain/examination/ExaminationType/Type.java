package gm.vk.core.domain.examination.ExaminationType;

import java.util.Arrays;
import java.util.Optional;

enum Type {

    EXAM("EXAM"), CREDIT("CREDIT"), NOTHING("NOTHING");

    Type(final String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public static Optional<Type> getExaminationTypeByType(final String _type) {
        return Arrays.stream(values()).parallel().filter(type -> type.getType().equals(_type)).findFirst();
    }

}

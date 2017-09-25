package gm.vk.core.domain.subject.examination.type;

import javax.persistence.*;

@Entity
@Table(name = "examinationType")
public class ExaminationType {

    public ExaminationType() {
    }

    public ExaminationType(final Integer id, final Type type) {
        this.id = id;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private Type type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
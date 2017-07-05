package common.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;
import tr.com.innova.sample.common.events.DomainEvents;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by herdemir on 02.02.2015.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @Column(name = "ID")
    protected UUID id;

    @Audited
    @ColumnDefault(value = "FALSE")
    @Column(name = "DELETED")
    protected boolean deleted;

    protected AbstractEntity() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        deleted = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof AbstractEntity)) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    protected void publishEvent(Object event) {
        DomainEvents.post(event);
    }
}

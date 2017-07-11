package com.github.cangoksel.common;

import com.github.cangoksel.common.entity.SampleRevisionEntity;
import com.google.common.base.MoreObjects;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by herdemir on 09.04.2015.
 */
@Entity
@RevisionEntity(SampleRevisionListener.class)
@Table(name = "REVINFO")
public class SampleRevisionEntityImpl implements SampleRevisionEntity, Serializable {
    @Id
    @GeneratedValue
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    private long timestamp;

    private String username;

    @Column(name = "SUPPORTINGUSERNAME")
    private String supportingUsername;

    @Column(name = "REVISIONDATE")
    private LocalDateTime revisionDate;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    @Override
    public String getSupportingUsername() {
        return supportingUsername;
    }

    public void setSupportingUsername(String supportingUser) {
        this.supportingUsername = supportingUser;
    }

    public void setRevisionDate(LocalDateTime revisionDate) {
        this.revisionDate = revisionDate;
    }

    @Override
    public LocalDateTime getRevisionDate() {
        return revisionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SampleRevisionEntityImpl that = (SampleRevisionEntityImpl) o;
        return id == that.id && timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("timestamp", timestamp)
                          .toString();
    }
}

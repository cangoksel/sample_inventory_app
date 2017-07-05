package common.entity;

import java.time.LocalDateTime;

/**
 * Created by herdemir on 01.12.2015.
 */
public interface SampleRevisionEntity {
    int getId();

    long getTimestamp();

    String getUsername();

    String getSupportingUsername();

    LocalDateTime getRevisionDate();
}

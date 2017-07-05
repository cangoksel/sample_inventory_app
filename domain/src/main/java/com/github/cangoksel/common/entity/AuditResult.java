package com.github.cangoksel.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.envers.RevisionType;

/**
 * Created by herdemir on 01.12.2015.
 */
@Getter
@AllArgsConstructor
public class AuditResult<T> {
    private final T entity;
    private final SampleRevisionEntity revisionEntity;
    private final RevisionType revisionType;
}

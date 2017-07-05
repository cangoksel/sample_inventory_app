package com.github.cangoksel.common.utils;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by herdemir on 03.04.2015.
 */
public interface PredicateSupportsPagination extends SupportsPagination {
    Page findAll(Predicate predicate, Pageable pageable);
}

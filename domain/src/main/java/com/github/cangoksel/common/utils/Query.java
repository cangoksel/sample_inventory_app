package com.github.cangoksel.common.utils;

import com.querydsl.core.types.Predicate;

/**
 * Created by herdemir on 01.04.2015.
 */
public interface Query {
    Predicate getQuery();
}

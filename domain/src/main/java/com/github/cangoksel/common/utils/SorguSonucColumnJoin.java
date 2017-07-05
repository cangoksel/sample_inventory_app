package com.github.cangoksel.common.utils;

import com.querydsl.core.JoinType;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by tozyurek on 11.10.2016.
 */
@AllArgsConstructor
@Getter
public class SorguSonucColumnJoin {
    private JoinType joinType;
    private EntityPath leftPath;
    private Predicate onCondition;
    private Path alias;

    public SorguSonucColumnJoin(JoinType joinType, EntityPath leftPath, Path alias) {
        this.joinType = joinType;
        this.leftPath = leftPath;
        this.alias = alias;
    }
}

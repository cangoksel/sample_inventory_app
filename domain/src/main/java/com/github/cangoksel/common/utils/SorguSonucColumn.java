package com.github.cangoksel.common.utils;


import com.querydsl.core.types.Expression;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by tozyurek on 10.10.2016.
 */
public interface SorguSonucColumn {
    String getHeader();
    Expression getExpression();
    SorguSonucColumnJoin[] getJoin();
    Predicate getFilterFunction();
    Function getMappingFunction();
    Object getReduceStarter();
    BinaryOperator getReduceOperator();
    Expression getSortKey();
    String name();
    boolean isMoneyMi();
}

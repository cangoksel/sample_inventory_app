package com.github.cangoksel.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.javamoney.moneta.Money;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by tozyurek on 11.10.2016.
 */
@AllArgsConstructor
@Getter
public class SorguSonucItem {
    private Class aClass;
    private Object data;
    private Predicate filterFunction;
    private Function mappingFunction;
    private Object reduceStarter;
    private BinaryOperator reduceOperator;

    public SorguSonucItem(Class aClass, Object data) {
        this.aClass = aClass;
        this.data = data;
    }

    public boolean isMoneyMi(){
        if(aClass.isAssignableFrom(Money.class)){
            return true;
        }
        return false;
    }
}

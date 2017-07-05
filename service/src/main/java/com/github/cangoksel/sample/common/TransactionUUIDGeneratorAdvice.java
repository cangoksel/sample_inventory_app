package com.github.cangoksel.sample.common;

import com.github.cangoksel.common.utils.TransactionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by herdemir on 09.09.2015.
 */
@Component
@Aspect
public class TransactionUUIDGeneratorAdvice implements Ordered {
    @Value("1")
    private int order;

    @Override
    public int getOrder() {
        return order;
    }

    @Around("@within(org.springframework.transaction.annotation.Transactional)")
    public Object execute(final ProceedingJoinPoint call) throws Throwable { //NOSONAR
        final Object returnValue;

        try {
            TransactionContext.INSTANCE.create(call.toLongString());
            returnValue = call.proceed();
        } finally {
            TransactionContext.INSTANCE.clear(call.toLongString());
        }

        return returnValue;
    }
}

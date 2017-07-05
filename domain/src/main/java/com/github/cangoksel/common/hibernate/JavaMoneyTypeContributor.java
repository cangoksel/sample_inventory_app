package com.github.cangoksel.common.hibernate;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by herdemir on 08.07.2015.
 */
public class JavaMoneyTypeContributor implements TypeContributor {
    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        typeContributions.contributeType(JavaMoneyType.INSTANCE, "money", "javax.money.MonetaryAmount", "org.javamoney.moneta.Money");
    }
}

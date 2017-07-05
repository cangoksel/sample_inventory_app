package com.github.cangoksel.common.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by herdemir on 08.07.2015.
 */
public class JavaMoneyType extends ImmutableType<MonetaryAmount> {
    public static final JavaMoneyType INSTANCE = new JavaMoneyType();

    private static final String AMOUNT = "amount";
    private static final String CURRENCY = "currency";

    private static final String[] PROPERTY_NAMES = new String[]{AMOUNT, CURRENCY};
    private static final Type[] PROPERTY_TYPES = new Type[]{BigDecimalType.INSTANCE, StringType.INSTANCE};

    public JavaMoneyType() {
        super(MonetaryAmount.class);
    }

    @Override
    public String[] getPropertyNames() {
        return PROPERTY_NAMES;
    }

    @Override
    public Type[] getPropertyTypes() {
        return PROPERTY_TYPES;
    }

    @Override
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        MonetaryAmount amount = (MonetaryAmount) component;
        switch (property) {
            case 0:
                return amount.getNumber();
            case 1:
                return amount.getCurrency();
            default:
                throw new IllegalArgumentException(property + " is not a valid property index");
        }
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        if (names.length != PROPERTY_NAMES.length) {
            throw new HibernateException("Expected " + PROPERTY_NAMES.length + " column names but got " + names.length);
        }
        final BigDecimal number = rs.getBigDecimal(names[0]);
        final String currencyCode = rs.getString(names[1]);

        final MonetaryAmount amount;
        if (number == null ^ currencyCode == null) {
            throw new HibernateException("both the value and the currency must be set, or both must be null");
        } else if (number == null) {
            amount = null;
        } else {
            amount = Monetary.getDefaultAmountFactory()
                    .setNumber(number)
                    .setCurrency(currencyCode)
                    .create();
        }
        return amount;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        final BigDecimal amount;
        final String currencyCode;
        if (value == null) {
            amount = null;
            currencyCode = null;
        } else {
            final MonetaryAmount monetaryAmount = (MonetaryAmount) value;
            amount = monetaryAmount.getNumber().numberValue(BigDecimal.class);
            currencyCode = monetaryAmount.getCurrency().getCurrencyCode();
        }
        st.setBigDecimal(index, amount);
        st.setString(index + 1, currencyCode);
    }

    @Override
    public Serializable disassemble(Object value, SharedSessionContractImplementor session) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return original;
    }

   /* @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
        throws HibernateException, SQLException {
        if (names.length != PROPERTY_NAMES.length) {
            throw new HibernateException("Expected " + PROPERTY_NAMES.length + " column names but got " + names.length);
        }
        final BigDecimal number = rs.getBigDecimal(names[0]);
        final String currencyCode = rs.getString(names[1]);

        final MonetaryAmount amount;
        if (number == null ^ currencyCode == null) {
            throw new HibernateException("both the value and the currency must be set, or both must be null");
        } else if (number == null) {
            amount = null;
        } else {
            amount = Monetary.getDefaultAmountFactory()
                             .setNumber(number)
                             .setCurrency(currencyCode)
                             .create();
        }
        return amount;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
        throws HibernateException, SQLException {
        final BigDecimal amount;
        final String currencyCode;
        if (value == null) {
            amount = null;
            currencyCode = null;
        } else {
            final MonetaryAmount monetaryAmount = (MonetaryAmount) value;
            amount = monetaryAmount.getNumber().numberValue(BigDecimal.class);
            currencyCode = monetaryAmount.getCurrency().getCurrencyCode();
        }
        st.setBigDecimal(index, amount);
        st.setString(index + 1, currencyCode);
    }*/
}

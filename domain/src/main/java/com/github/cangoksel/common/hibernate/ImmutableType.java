package com.github.cangoksel.common.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by herdemir on 08.07.2015.
 */
public abstract class ImmutableType<T> implements CompositeUserType {
    protected final Class<T> clazz;

    protected ImmutableType(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Class returnedClass() {
        return clazz;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException { //NOSONAR
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return Objects.hashCode(x);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
        throw new UnsupportedOperationException(String.format("%s is immutable", clazz.getCanonicalName()));
    }

    public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
        return cached;
    }

    public Object replace(Object original, Object target, SessionImplementor session, Object owner)
        throws HibernateException {
        return original;
    }
}

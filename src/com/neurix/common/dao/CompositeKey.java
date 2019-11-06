package com.neurix.common.dao;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 2:38
 * To change this template use File | Settings | File Templates.
 */
public abstract class CompositeKey<T> implements Serializable {
    protected T id;

    protected abstract T getId() ;

    protected abstract void setId(T id);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeKey)) return false;

        CompositeKey that = (CompositeKey) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
        return "CompositeKey{" +
                "id=" + id +
                '}';
    }
}

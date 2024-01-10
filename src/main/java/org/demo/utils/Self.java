package org.demo.utils;

public interface Self<T extends Self<T>> {

    @SuppressWarnings("unchecked")
    default T self() {
        return (T) this;
    }
}

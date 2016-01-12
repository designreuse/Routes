package com.t28.routes.util;

public class Pair<T1, T2> {
    private final T1 value1;
    private final T2 value2;

    private Pair(T1 value1, T2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T1 getValue1() {
        return value1;
    }

    public T2 getValue2() {
        return value2;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 value1, T2 value2) {
        return new Pair<T1, T2>(value1, value2);
    }
}

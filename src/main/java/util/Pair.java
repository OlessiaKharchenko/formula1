package util;

import java.util.Objects;

public class Pair<F, S> {
    private  F first;
    private  S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (first == pair.first || (first != null && first.equals(pair.first))) return true;
        return second == pair.second || (second != null && second.equals(pair.second));
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
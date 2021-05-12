package util;

import java.util.Iterator;

public class IndexedIterator<T> implements Iterator<Pair<Integer, T>> {
    private Iterator<T> iterator;
    private Integer index;

    public IndexedIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Pair<Integer, T> next() {
        return new Pair<>(++index, iterator.next());
    }
}
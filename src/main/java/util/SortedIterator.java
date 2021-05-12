package util;

import java.util.Iterator;
import java.util.List;

public class SortedIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;

    public SortedIterator(List<T> list) {
        this.iterator = list.stream().sorted().iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}
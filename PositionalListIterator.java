import java.util.Iterator;
import positionallist.Position;
import positionallist.PositionalList;

/**
 * An iterator for traversing a {@link PositionalList}.
 *
 * @param <E> the type of elements stored in the list
 */
public class PositionalListIterator<E> implements Iterator<E> {
    private Position<E> current;
    private PositionalList<E> list;

    /**
     * Constructs a new iterator for the specified {@link PositionalList}.
     *
     * @param list the positional list to iterate over
     */
    public PositionalListIterator(PositionalList<E> list) {
        this.list = list;
        current = list.first();
    }

    /**
     * Checks if there are more elements to be iterated.
     *
     * @return {@code true} if there are more elements, {@code false} otherwise
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Retrieves the next element in the iteration and moves the iterator forward.
     *
     * @return the next element in the iteration
     * @throws IllegalStateException if there are no more elements to iterate
     */
    public E next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        E element = current.getElement();
        current = list.after(current);
        return element;
    }
}

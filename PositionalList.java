package positionallist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An interface representing a positional list.
 *
 * @param <E> The type of elements stored in the positional list.
 */
public interface PositionalList<E> extends Iterable<E> {

    /**
     * Returns the number of elements in the positional list.
     *
     * @return The size of the positional list.
     */
    int size();

    /**
     * Checks if the positional list is empty.
     *
     * @return true if the positional list is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the position of the first element in the positional list.
     *
     * @return The position of the first element.
     */
    Position<E> first();

    /**
     * Returns the position of the last element in the positional list.
     *
     * @return The position of the last element.
     */
    Position<E> last();

    /**
     * Returns the position of the element before the given position.
     *
     * @param p The position to find the element before.
     * @return The position of the element before the given position.
     * @throws IllegalArgumentException if the given position is invalid.
     */
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the position of the element after the given position.
     *
     * @param p The position to find the element after.
     * @return The position of the element after the given position.
     * @throws IllegalArgumentException if the given position is invalid.
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**
     * Adds an element to the beginning of the positional list and returns its
     * position.
     *
     * @param e The element to add.
     * @return The position of the added element.
     */
    Position<E> addFirst(E e);

    /**
     * Adds an element to the end of the positional list and returns its position.
     *
     * @param e The element to add.
     * @return The position of the added element.
     */
    Position<E> addLast(E e);

    /**
     * Adds an element before the given position and returns its position.
     *
     * @param p The position after which to add the element.
     * @param e The element to add.
     * @return The position of the added element.
     * @throws IllegalArgumentException if the given position is invalid.
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Adds an element after the given position and returns its position.
     *
     * @param p The position before which to add the element.
     * @param e The element to add.
     * @return The position of the added element.
     * @throws IllegalArgumentException if the given position is invalid.
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element at the given position with a new element and returns the
     * old element.
     *
     * @param p The position to set the element.
     * @param e The new element to set.
     * @return The old element at the position.
     * @throws IllegalArgumentException if the given position is invalid.
     */
    E set(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Removes the element at the given position and returns it.
     *
     * @param p The position to remove the element.
     * @return The removed element.
     * @throws IllegalArgumentException if the given position is invalid.
     */
    E remove(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns an iterator for the positional list.
     *
     * @return An iterator for the positional list elements.
     */
    @Override
    default Iterator<E> iterator() {
        return new Iterator<E>() {
            private Position<E> current = first();

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements to iterate");
                }
                E element = current.getElement();
                current = after(current);
                return element;
            }
        };
    }
}

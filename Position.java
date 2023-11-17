package positionallist;

/**
 * An interface representing a position in a positional list.
 *
 * @param <E> The type of element stored in the position.
 */
public interface Position<E> {

    /**
     * Returns the element stored at this position.
     *
     * @return The element stored in this position.
     * @throws IllegalStateException if the position is no longer valid.
     */
    E getElement() throws IllegalStateException;
}

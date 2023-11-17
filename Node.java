package positionallist;

/**
 * A class representing a node in a positional list.
 *
 * @param <E> The type of element stored in the node.
 */
public class Node<E> implements Position<E> {
    E element;
    Node<E> prev;
    Node<E> next;
    private boolean valid;

    /**
     * Constructs a new node with the given element, previous node, and next node.
     *
     * @param element The element to store in the node.
     * @param prev    The previous node in the list.
     * @param next    The next node in the list.
     */
    public Node(E element, Node<E> prev, Node<E> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
        this.valid = true;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return The element stored in this node.
     * @throws IllegalStateException if the node is no longer valid.
     */
    public E getElement() {
        return element;
    }

    /**
     * Invalidates the node, marking it as no longer valid and disconnecting it from
     * the list.
     */
    public void invalidate() {
        valid = false;
        prev = null;
        next = null;
    }

    /**
     * Checks if the node is valid.
     *
     * @return true if the node is valid, false otherwise.
     */
    public boolean isValid() {
        return valid;
    }
}

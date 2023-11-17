package positionallist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A linked list-based implementation of the PositionalList interface.
 *
 * @param <E> The type of elements stored in the list.
 */
public class LinkedListPositionalList<E> implements PositionalList<E> {
    private Node<E> header;
    private Node<E> trailer;
    private int size;

    /**
     * Constructs an empty linked list-based positional list with sentinel nodes.
     */
    public LinkedListPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the first position in the list.
     *
     * @return The first position in the list, or null if the list is empty.
     */
    public Position<E> first() {
        return makePosition(header.next);
    }

    /**
     * Returns the last position in the list.
     *
     * @return The last position in the list, or null if the list is empty.
     */
    public Position<E> last() {
        return makePosition(trailer.prev);
    }

    /**
     * Returns the position before a given position.
     *
     * @param p The reference position.
     * @return The position before the given position, or null if the given position
     *         is the first position.
     * @throws IllegalArgumentException if the position is no longer valid.
     */
    public Position<E> before(Position<E> p) {
        Node<E> node = validate(p);
        return makePosition(node.prev);
    }

    /**
     * Returns the position after a given position.
     *
     * @param p The reference position.
     * @return The position after the given position, or null if the given position
     *         is the last position.
     * @throws IllegalArgumentException if the position is no longer valid.
     */
    public Position<E> after(Position<E> p) {
        Node<E> node = validate(p);
        return makePosition(node.next);
    }

    /**
     * Adds a new element to the beginning of the list.
     *
     * @param e The element to add.
     * @return The position where the new element is added.
     */
    public Position<E> addFirst(E e) {
        return addAfter(header, e);
    }

    /**
     * Adds a new element to the end of the list.
     *
     * @param e The element to add.
     * @return The position where the new element is added.
     */
    public Position<E> addLast(E e) {
        return addAfter(trailer.prev, e);
    }

    /**
     * Adds a new element after a given position.
     *
     * @param p The reference position.
     * @param e The element to add.
     * @return The position where the new element is added.
     */
    public Position<E> addAfter(Position<E> p, E e) {
        Node<E> node = validate(p);
        Node<E> newNode = new Node<>(e, node, node.next);
        node.next.prev = newNode;
        node.next = newNode;
        size++;
        return makePosition(newNode);
    }

    /**
     * Replaces the element at a given position with a new element.
     *
     * @param p The position to set the new element.
     * @param e The new element to set.
     * @return The old element that was replaced.
     */
    public E set(Position<E> p, E e) {
        Node<E> node = validate(p);
        E oldElement = node.getElement();
        node.element = e;
        return oldElement;
    }

    /**
     * Removes the element at a given position.
     *
     * @param p The position to remove.
     * @return The removed element.
     */
    public E remove(Position<E> p) {
        Node<E> node = validate(p);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
        return node.element;
    }

    /**
     * Creates a position from a node.
     *
     * @param node The node to convert to a position.
     * @return The position corresponding to the given node.
     */
    private Position<E> makePosition(Node<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }

    /**
     * Validates a given position.
     *
     * @param p The position to validate.
     * @return The corresponding node if the position is valid.
     * @throws IllegalArgumentException if the position is not valid.
     */
    private Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid position");
        }
        Node<E> node = (Node<E>) p;
        if (node.next == null) {
            throw new IllegalArgumentException("Position is no longer valid");
        }
        return node;
    }

    /**
     * Returns an iterator for the positional list.
     *
     * @return An iterator over the elements of the list.
     */
    public Iterator<E> iterator() {
        return new PositionalListIterator(header.next);
    }

    /**
     * A private iterator class for the positional list.
     */
    private class PositionalListIterator implements Iterator<E> {
        private Position<E> current = first();

        public PositionalListIterator(Node<E> next) {
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate");
            }
            E element = current.getElement();
            current = after(current);
            return element;
        }
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Unimplemented method 'addBefore'");
    }
}

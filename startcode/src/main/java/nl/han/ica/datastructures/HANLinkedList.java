package nl.han.ica.datastructures;

public class HANLinkedList<T> implements IHANLinkedList<T> {
    private ListNode<T> frontOfList;
    private int size;

    public HANLinkedList() {
        this.frontOfList = null;
        this.size = 0;
    }

    /**
     * Adds value to the front of the list
     *
     * @param value generic value to be added
     */
    @Override
    public void addFirst(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        newNode.setNext(frontOfList);
        frontOfList = newNode;
        size++;
    }

    /**
     * Clears list. Size equals 0 afterwards
     */
    @Override
    public void clear() {
        frontOfList = null;
        size = 0;
    }

    /**
     * Adds value to index position
     *
     * @param index the position
     * @param value the value to add at index
     */
    @Override
    public void insert(int index, T value) {
        ListNode<T> tempNode = frontOfList;
        ListNode<T> newNode = new ListNode<>(value);

        for (int i = 0; i < index - 1; i++) {
            tempNode = tempNode.getNext();
        }

        newNode.setNext(tempNode.getNext());
        tempNode.setNext(newNode);
        size++;
    }

    /**
     * Deletes value at position
     *
     * @param pos position where value is deleted
     */
    @Override
    public void delete(int pos) {
        ListNode<T> tempNode = frontOfList;

        for (int i = 0; i < pos - 1; i++) {
            tempNode = tempNode.getNext();
        }

        tempNode.setNext(tempNode.getNext().getNext());
        size--;
    }

    /**
     * Returns generic value T at position
     *
     * @param pos position to look up value
     * @return value at position pos
     */
    @Override
    public T get(int pos) {
        ListNode<T> tempNode = frontOfList;

        for (int i = 0; i < pos; i++) {
            tempNode = tempNode.getNext();
        }

        return tempNode.getElement();
    }

    /**
     * Removes first element
     */
    @Override
    public void removeFirst() {
        frontOfList = frontOfList.getNext();
        size--;
    }

    /**
     * Returns first element in O(n) time
     *
     * @return first element
     */
    @Override
    public T getFirst() {
        return frontOfList.getElement();
    }

    /**
     * Determines size of the list, equals the number of stored items but not the header node
     *
     * @return number of items in list
     */
    @Override
    public int getSize() {
        return size;
    }
}

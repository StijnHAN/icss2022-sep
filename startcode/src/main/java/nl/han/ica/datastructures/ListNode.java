package nl.han.ica.datastructures;

public class ListNode<T> {
    private T element;
    private ListNode<T> next;

    public ListNode(T element) {
        this.element = element;
        this.next = null;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }

    public T getElement() {
        return element;
    }
}

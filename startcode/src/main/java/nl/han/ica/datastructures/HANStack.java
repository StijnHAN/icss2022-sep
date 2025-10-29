package nl.han.ica.datastructures;

public class HANStack<T> implements IHANStack<T> {
    public HANLinkedList<T> stack;

    public HANStack() {
        this.stack = new HANLinkedList<>();
    }

    /**
     * pushes value T to the top of the stack
     *
     * @param value value to push
     */
    @Override
    public void push(T value) {
        stack.addFirst(value);
    }

    /**
     * Pops (and removes) value at top of stack
     *
     * @return popped value
     */
    @Override
    public T pop() {
        T res = stack.getFirst();
        stack.removeFirst();
        return res;
    }

    /**
     * Peeks at the top of the stack. Does not remove anything
     *
     * @return value at the top of the stack
     */
    @Override
    public T peek() {
        return stack.getFirst();
    }
}

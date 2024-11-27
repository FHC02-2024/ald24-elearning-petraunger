package A01_Stack;
// Petra Unger
//27.11.2024

public class Node<T>
{
    private final T data;

    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

package A02_Queue; //Petra Unger

public class Queue<T> {
    private Node<T> first;

    private Node<T> last;
    int counter = 0;

    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zur�ckliefern.
     * Existiert kein Element, wird eine Exception ausgel�st.
     *
     * @throws QueueEmptyException
     */
    public T dequeue() throws QueueEmptyException {

        if (counter == 0) {
            throw new QueueEmptyException();
        } else {
            counter--;
            T data = first.getData();
            first = first.getNext();
            return data;

        }

    }


    /**
     * �bergebenen Integer am Ende der Queue anh�ngen.
     *
     * @param i Zahl
     */
    public void enqueue(T i) {
        Node<T> enqueuedNode = new Node<>(i);
        counter++;
        if (first == null) {
            first = enqueuedNode;
        } else {

            last.setNext(enqueuedNode);

        }
        last = enqueuedNode;
    }


    /**
     * Liefert die Anzahl der Elemente im Stack
     *
     * @return
     */
    public int getCount() {
        return counter;
    }
}

package A01_Stack; //Petra Unger


public class Stack<T>
{
	 private Node<T> first;
     int counter = 0;
    /**
     * Oberstes Element entfernen und zur�ckliefern.
     * Existiert kein Element, wird eine Exception ausgel�st.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException {

        if (counter==0){

            throw new StackEmptyException();
        }
        else {
            T data = first.getData();
            first = first.getNext();
            counter--;

            return data;
        }


    }
    
    /**
     * �bergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {
        Node <T> pushedNode = new Node<>(i);
        pushedNode.setNext(first);
        first = pushedNode;
        counter++;

    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
    	return counter;
    }
}

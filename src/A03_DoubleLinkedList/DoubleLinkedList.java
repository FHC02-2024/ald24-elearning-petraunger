package A03_DoubleLinkedList; //Petra Unger

public class DoubleLinkedList<T> {

    Node<T> first = null;
    Node<T> last = null;
    int counter = 0;
    Node<T> current = null;
    private int pointer = 0;

    /**
     * Einfügen einer neuen <T>
     *
     * @param a <T>
     */
    public void add(T a) {
        Node<T> addedNode = new Node<>(a);

        if (counter == 0) {
            first = addedNode;
        } else {
            last.setNext(addedNode);
            addedNode.setPrevious(last);
        }

        last = addedNode;
        counter++;

    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {

        pointer = 0;

        current = getFirst();

    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {

        pointer = counter;

        current = getLast();

    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getFirst() {

        return first;
    }

    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getLast() {

        return last;
    }

    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     *
     * @return <T>|null
     */
    public T next() {
        if (current == null) {
            return null;

        } else {
            T data = current.getData();
            moveNext();
            return data;
        }

    }


    /**
     * analog zur Funktion next()
     *
     * @return <T>|null
     */
    public T previous() {
        if (current == null) {
            return null;
        } else {
            T data = current.getData();
            movePrevious();
            return data;
        }

    }

    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if (current == null) {
        } else if (pointer < counter) {
            current = current.getNext();
        } else {
            current = null;
        }
        pointer++;

    }


    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {

        if (current == null) {
        } else if (pointer > 0) {
            current = current.getPrevious();
        } else {
            current = null;
        }

        pointer--;

    }

    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     *
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {
        if (current == null) {
            throw new CurrentNotSetException();
        }

        return current.getData();


    }

    /**
     * Gibt <T> an bestimmter Position zurück
     *
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {
        Node<T> helpingNode = first;

        for (int i = 0; i < pos - 1; i++) {
            helpingNode = helpingNode.getNext();

        }

        return helpingNode.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     *
     * @param pos
     */
    public void remove(int pos) throws CurrentNotSetException {
        Node<T> ToBeDeleted = first;

        for (int i = 0; i < pos - 1; i++) {
            if (ToBeDeleted == null) {
                return;
            }
            ToBeDeleted = ToBeDeleted.getNext();
        }

        if (ToBeDeleted == null) {
            return;
        }

        int pointerToSave = pointer;
        current = ToBeDeleted;

        removeCurrent();

        pointer = pointerToSave;

        current = first;
        for (int i = 0; i < pointer - 1; i++) {
            if (current == null) {
                return;
            }
            current = current.getNext();
        }
    }


    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element
     *
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {

        if (current == null) {
            throw new CurrentNotSetException();
        }

        Node<T> previous = current.getPrevious();
        Node<T> next = current.getNext();

        if (previous == null && next == null) {

            current = null;
            first = null;
            last = null;
            counter = 0;
            pointer = 0;

        } else if (previous == null) {
            first = first.getNext();
            first.setPrevious(null);
            moveNext();
        } else if (next == null) {
            last = last.getPrevious();
            last.setNext(null);
            movePrevious();
        } else {
            previous.setNext(next);
            next.setPrevious(previous);
            moveNext();
        }


        counter--;
        pointer--;


    }

    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     *
     * @throws CurrentNotSetException
     *
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

        if (current == null) {
            throw new CurrentNotSetException();
        }

        Node<T> helpingNode = current.getNext();
        Node<T> toBeInserted = new Node<>(a);

        if (helpingNode == null) {
            add(a);
        } else {

            current.setNext(toBeInserted);
            toBeInserted.setNext(helpingNode);
            toBeInserted.setPrevious(current);
            helpingNode.setPrevious(toBeInserted);

            counter++;
        }
        moveNext();
    }
}

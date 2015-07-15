import sun.plugin.dom.exception.InvalidStateException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LinkedList<T> {
    private int count;
    private LinkedListNode<T> first;
    private LinkedListNode<T> last;

    public LinkedListNode<T> getFirst() {
        return this.first;
    }

    public LinkedListNode<T> getLast() {
        return this.last;
    }

    // Създава елемент по стойност и го включва в началото на свързания списък
    public LinkedListNode<T> addFirst(T value) {
        LinkedListNode<T> node = new LinkedListNode<>(value);
        this.addFirst(node);
        return node;
    }

    // Включва елемент в началото на свързания списък
    public void addFirst(LinkedListNode<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Елементът за добавяне не може да бъде null.");
        }

        if (this.getFirst() == null) {
            this.first = node;
            this.last = node;
        } else {
            node.setNext(this.getFirst());
            this.getFirst().setPrevious(node);
            this.first = node;
        }

        node.setList(this);
        this.count++;
    }

    // Създава елемент по стойност и го включва в края на свързания списък
    public LinkedListNode<T> addLast(T value) {
        LinkedListNode<T> node = new LinkedListNode<>(value);
        this.addLast(node);
        return node;
    }

    // Включва елемент в края на свързания списък
    public void addLast(LinkedListNode<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Елементът за добавяне не може да бъде null.");
        }

        if (this.getLast() == null) {
            this.first = node;
            this.last = node;
        } else {
            node.setPrevious(this.getLast());
            this.getLast().setNext(node);
            this.last = node;
        }

        node.setList(this);
        this.count++;
    }

    // Създава елемент по стойност и го включва след даден елемент
    public LinkedListNode<T> addAfter(LinkedListNode<T> nodeToAddAfter, T value) {
        LinkedListNode<T> newNode = new LinkedListNode<>(value);
        this.addAfter(nodeToAddAfter, newNode);
        return newNode;
    }

    // Включва елемент след даден елемент
    public void addAfter(LinkedListNode<T> nodeToAddAfter, LinkedListNode<T> newNode) {
        if (nodeToAddAfter == null) {
            throw new IllegalArgumentException("Елементът, след който да се добавя, не може да бъде null.");
        }

        if (nodeToAddAfter.getList() != this) {
            throw new IllegalArgumentException("Елементът, след който да се добавя, не принадлежи на текущия списък.");
        }

        if (newNode == null) {
            throw new IllegalArgumentException("Елементът, след който да се добавя, не може да бъде null.");
        }

        if (nodeToAddAfter.getNext() == null) {
            this.addLast(newNode);
        } else {
            nodeToAddAfter.getNext().setPrevious(newNode);
            newNode.setNext(nodeToAddAfter.getNext());
            newNode.setPrevious(nodeToAddAfter);
            nodeToAddAfter.setNext(newNode);

            newNode.setList(this);
            this.count++;
        }
    }

    // Създава елемент по стойност и го включва преди даден елемент
    public LinkedListNode<T> addBefore(LinkedListNode<T> nodeToAddBefore, T value) {
        LinkedListNode<T> newNode = new LinkedListNode<>(value);
        this.addBefore(nodeToAddBefore, newNode);
        return newNode;
    }

    // Включва елемент преди даден елемент
    public void addBefore(LinkedListNode<T> nodeToAddBefore, LinkedListNode<T> newNode) {
        if (nodeToAddBefore == null) {
            throw new IllegalArgumentException("Елементът, преди който да се добавя, не може да бъде null.");
        }

        if (nodeToAddBefore.getList() == null) {
            throw new IllegalArgumentException("Елементът, преди който да се добавя, не принадлежи на текущия списък.");
        }

        if (newNode == null) {
            throw new IllegalArgumentException("Елементът за добавяне не може да бъде null.");
        }

        if (nodeToAddBefore.getPrevious() == null) {
            this.addFirst(newNode);
        } else {
            nodeToAddBefore.getPrevious().setNext(newNode);
            newNode.setPrevious(nodeToAddBefore.getPrevious());
            newNode.setNext(nodeToAddBefore);
            nodeToAddBefore.setPrevious(newNode);

            newNode.setList(this);
            this.count++;
        }
    }

    // Изтрива елемент от свързания списък
    public void removeNode(LinkedListNode<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Елементът за изтриване не може да бъде null.");
        }

        if (node.getList() != this) {
            throw new IllegalArgumentException("Елементът за изтриване не принадлежи на текущия списък.");
        }

        if (node.getPrevious() != null && node.getNext() != null) {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
            node.setPrevious(null);
            node.setPrevious(null);
        } else if (node.getPrevious() == null) {
            this.first = this.getFirst().getNext();
        } else if (node.getNext() == null) {
            this.last = this.getLast().getPrevious();
        }

        node.setList(null);
        this.count--;
    }

    // Изтрива първия срещнат елемент със стойност равна на подадената
    public void remove(T value) {
        LinkedListNode<T> node = this.find(value);

        if (node == null) {
            throw new InvalidStateException("Не е намерен елемент със стойност " + value + " за изтриване.");
        }

        this.removeNode(node);
    }

    public LinkedListNode<T> find(T value) {
        LinkedListNode<T> result = null;

        if (this.getFirst() != null) {
            LinkedListNode<T> currentNode = this.getFirst();

            if (currentNode.getValue().equals(value)) {
                result = currentNode;
            } else {
                while (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                    if (currentNode.getValue().equals(value)) {
                        result = currentNode;
                        break;
                    }
                }
            }
        }

        return result;
    }

    public int size() {
        return this.count;
    }

    public void clear() {
        this.first = null;
        this.last = null;
        this.count = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        LinkedListNode<T> currentNode = this.getFirst();
        while (currentNode != null) {
            result.append(currentNode.getValue());
            result.append(" ");
            currentNode = currentNode.getNext();
        }

        return result.toString();
    }
}

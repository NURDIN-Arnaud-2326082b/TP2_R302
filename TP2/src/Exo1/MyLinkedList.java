package Exo1;

import java.util.*;

public class MyLinkedList<T> implements List<T> {
    public class Node {
        T value;
        Node next;
        Node prev;

        Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;
    Node tail;
    public int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Node current = head; current != null; current = current.next) {
            if (Objects.equals(o, current.value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node current = head; current != null; current = current.next) {
            array[index++] = current.value;
        }
        return array;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int index = 0;
        for (Node current = head; current != null; current = current.next) {
            a[index++] = (E) current.value;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (Node current = head; current != null; current = current.next) {
            if (Objects.equals(o, current.value)) {
                removeNode(current);
                return true;
            }
        }
        return false;
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        size--;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T item : c) {
            if (add(item)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkPositionIndex(index);
        boolean modified = false;
        for (T item : c) {
            add(index++, item);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object item : c) {
            while (remove(item)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (Node current = head; current != null; ) {
            Node next = current.next;
            if (!c.contains(current.value)) {
                removeNode(current);
                modified = true;
            }
            current = next;
        }
        return modified;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        Node current = node(index);
        return current.value;
    }

    @Override
    public T set(int index, T element) {
        checkElementIndex(index);
        Node current = node(index);
        T oldValue = current.value;
        current.value = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (index == size) {
            add(element);
        } else {
            checkPositionIndex(index);
            Node newNode = new Node(element);
            if (index == 0) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                Node current = node(index);
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
            size++;
        }
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        Node nodeToRemove = node(index);
        T value = nodeToRemove.value;
        removeNode(nodeToRemove);
        return value;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node current = head; current != null; current = current.next) {
            if (Objects.equals(o, current.value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        for (Node current = tail; current != null; current = current.prev) {
            if (Objects.equals(o, current.value)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            private Node current = head;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                index++;
                return value;
            }

            @Override
            public boolean hasPrevious() {
                return current != null;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                current = (current == null) ? tail : current.prev;
                T value = current.value;
                index--;
                return value;
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        checkPositionIndex(index);
        return new ListIterator<T>() {
            private Node current = node(index);
            private int idx = index;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                idx++;
                return value;
            }

            @Override
            public boolean hasPrevious() {
                return current != null;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                current = (current == null) ? tail : current.prev;
                T value = current.value;
                idx--;
                return value;
            }

            @Override
            public int nextIndex() {
                return idx;
            }

            @Override
            public int previousIndex() {
                return idx - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private Node node(int index) {
        if (index < (size >> 1)) {
            Node x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

        @Override
        public List<T> subList(int fromIndex, int toIndex) {
            // Vérifier la validité des indices
            if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
            }

            return new SubList(fromIndex, toIndex);
        }

        private class SubList extends AbstractList<T> {
            private int fromIndex;
            private int toIndex;
            private int size;

            SubList(int fromIndex, int toIndex) {
                this.fromIndex = fromIndex;
                this.toIndex = toIndex;
                this.size = toIndex - fromIndex;
            }

            @Override
            public T get(int index) {
                checkIndex(index);
                return MyLinkedList.this.get(fromIndex + index);
            }

            @Override
            public int size() {
                return size;
            }

            @Override
            public boolean add(T t) {
                throw new UnsupportedOperationException("Add operation is not supported.");
            }

            @Override
            public T set(int index, T element) {
                checkIndex(index);
                return MyLinkedList.this.set(fromIndex + index, element);
            }

            @Override
            public void add(int index, T element) {
                throw new UnsupportedOperationException("Add operation is not supported.");
            }

            @Override
            public T remove(int index) {
                checkIndex(index);
                return MyLinkedList.this.remove(fromIndex + index);
            }

            private void checkIndex(int index) {
                if (index < 0 || index >= size) {
                    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
                }
            }
        }


    }



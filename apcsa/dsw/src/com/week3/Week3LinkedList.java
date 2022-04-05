package com.week3;

import java.util.Iterator;

import com.week3.Sorts;

// java linkedlist
public class Week3LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Week3LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = data;
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<T>(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T data = null;
        if (index == 0) {
            data = head.data;
            head = head.next;
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            data = temp.next.data;
            temp.next = temp.next.next;
        }
        size--;
        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(T data) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.data.equals(data)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int indexOf(T data) {
        Node<T> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(data)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.data);
            temp = temp.next;
        }
        return sb.toString();
    }

    // create a new iterator
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    // define LinkedListIterator
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }

        // public void remove() {
        //     throw new UnsupportedOperationException();
        // }
    }

    public T removeFirst() {
        return remove(0);
    }

    public T getFirst() {
        if (head == null) {
            throw new NullPointerException();
        }
        return head.data;
    }

    public void addFirst(T data) {
        add(0, data);
    }

    public void swap(int index1, int index2) {
        // for some reasons just putting "size" won't work
        // but calling the public method seems to fix it
        int size = this.size(); 
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> temp1 = head;

	int iF = Math.min(index1, index2);
        for (int i = 0; i < iF; i++) {
            temp1 = temp1.next;
        }

        Node<T> temp2 = temp1;
	int jF = Math.max(index1, index2);
        for (int i = iF; i < jF; i++) {
            temp2 = temp2.next;
        }
        T temp = temp1.data;
        temp1.data = temp2.data;
        temp2.data = temp;
    }

    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

    public static void main(String[] args) {
        Week3LinkedList<String> list = new Week3LinkedList<String>();
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("t");
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
    }
}
